package algorythm.perm_comb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation_bistmask {
	static int[] arr;
	static int[] result;
	static List<int[]> final_result;

	Permutation_bistmask(int[] arr, int r) {
		Permutation_bistmask.arr = arr;
		Permutation_bistmask.result = new int[r];
		final_result = new ArrayList<>();
	}

	List<int[]> get_perm() {
		make(0,0);
		return final_result;
	}

	void make(int depth, int visited) {
		if (depth == result.length) {
			final_result.add(result.clone());
			System.out.println(Arrays.toString(result));
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if ((1<<i & visited) == 0) {
				result[depth] = arr[i];
				make(depth + 1, 1<<i | visited);
			}
		}
	}
}

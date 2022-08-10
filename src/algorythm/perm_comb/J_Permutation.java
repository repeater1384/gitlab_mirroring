package algorythm.perm_comb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class J_Permutation {
	static int[] arr;
	static int[] result;
	static List<int[]> final_result;

	J_Permutation(int[] arr, int r) {
		J_Permutation.arr = arr;
		J_Permutation.result = new int[r];
		final_result = new ArrayList<>();
	}

	List<int[]> get_perm() {
		make(0);
		return final_result;
	}

	void make(int depth) {
		if (depth == result.length) {
			final_result.add(result.clone());
			System.out.println(Arrays.toString(result));
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			result[depth] = arr[i];
			make(depth + 1);
		}
	}
}

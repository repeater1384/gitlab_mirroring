package algorythm.perm_comb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class J_Combination {
	static int[] arr;
	static int[] result;
	static List<int[]> final_result;

	J_Combination(int[] arr, int r) {
		J_Combination.arr = arr;
		J_Combination.result = new int[r];
		final_result = new ArrayList<>();
	}

	List<int[]> get_comb() {
		make(0, 0);
		return final_result;
	}

	void make(int depth, int start) {
		if (depth == result.length) {
			final_result.add(result.clone());
			System.out.println(Arrays.toString(result));
			return;
		}
		for (int i = start; i < arr.length; i++) {
			result[depth] = arr[i];
			make(depth + 1, i);
		}
	}
}

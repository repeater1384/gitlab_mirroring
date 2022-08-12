/*
		 * 				a1
		 *			a5	a2  a6
		 * 				a3
		 * 				a4
		 * 
		 * a2가 바닥. a4가 윗면.
		 * 남쪽으로 굴리면 a1 -> a4 / a2 -> a1 / a3 -> a2 / a4 -> a3
		 * 북쪽으로 굴리면 a1 -> a2 / a2 -> a3 / a3 -> a4 / a4 -> a1
		 * 동쪽으로 굴리면 a2 -> a5 / a5 -> a4 / a4 -> a6 / a6 -> a2
		 * 서쪽으로 굴리면 a2 -> a6 / a6 -> a4 / a4 -> a5 / a5 -> a2
		 */

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

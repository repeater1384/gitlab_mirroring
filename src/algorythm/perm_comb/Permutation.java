package algorythm.perm_comb;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Permutation {
	static int[] arr;
	static int[] result;
	static boolean[] visited;
	static List<int[]> final_result;

	Permutation(int[] arr, int r) {
		Permutation.arr = arr;
		Permutation.result = new int[r];
		Permutation.visited = new boolean[arr.length];
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
			if (!visited[i]) {
				visited[i] = true;
				result[depth] = arr[i];
				make(depth + 1);
				result[depth] = 0;
				visited[i] = false;
			}
		}
	}
}

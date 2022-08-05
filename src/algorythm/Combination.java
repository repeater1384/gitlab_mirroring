package algorythm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Combination {
	static int[] arr;
	static int[] result;
	static boolean[] visited;
	static List<int[]> final_result;

	Combination(int[] arr, int r) {
		Combination.arr = arr;
		Combination.result = new int[r];
		Combination.visited = new boolean[arr.length];
		final_result = new ArrayList<>();
	}

	List<int[]> get_comb() {
		make(0,0);
		return final_result;
	}

	void make(int depth, int start) {
		if (depth == result.length) {
			final_result.add(result.clone());
			System.out.println(Arrays.toString(result));
			return;
		}
		for (int i = start; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[depth] = arr[i];
				make(depth + 1,start+1);
				result[depth] = 0;
				visited[i] = false;
			}
		}
	}
}

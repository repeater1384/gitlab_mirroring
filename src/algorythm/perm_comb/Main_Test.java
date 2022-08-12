package algorythm.perm_comb;

import java.util.Arrays;
import java.util.List;

public class Main_Test {

	public static void main(String[] args) {
//		PowerSet psmng = new PowerSet(new int[] { 1, 2, 3, 4, 5 });
//		List<List<Integer>> result = psmng.getPoserSet();
//		for (List<Integer> ele : result) {
//			System.out.println(ele);
//		Combination c = new Combination(new int[] {1,2,3,4},2);
//		
//		c.get_comb();
//		
//		int N = 5;
//		int R = 3;
//		int[] test = new int[N];
//		for (int i = 0; i < N; i++)
//			test[i] = i + 1;
//
//		Permutation_bistmask permmng = new Permutation_bistmask(test, 5);
//		List<int[]> result = permmng.get_perm();
//		for (int[] ele : result)
//			System.out.println(Arrays.toString(ele));
//		}
		int[] arr = { 1, 2, 3, 4, 5 };
		int r = 3;
		int[] cur = new int[arr.length];
		for (int i = 0; i < r; i++)
			cur[arr.length - 1 - i] = 1;
		
		for (; cur != null; cur = Next_Permutation.get_next_permutation(cur)) {
			int[] temp = new int[r];
			int idx = 0;
			for (int i = 0; i < arr.length; i++)
				if (cur[arr.length-1-i] == 1)
					temp[idx++] = arr[i];
			System.out.println(Arrays.toString(temp));
		}

	}

}

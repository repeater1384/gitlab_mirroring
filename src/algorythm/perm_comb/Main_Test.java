package algorythm.perm_comb;

import java.util.Arrays;
import java.util.List;

public class Main_Test {

	public static void main(String[] args) {
		PowerSet psmng = new PowerSet(new int[] { 1, 2, 3, 4, 5 });
		List<List<Integer>> result = psmng.getPoserSet();
		for (List<Integer> ele : result) {
			System.out.println(ele);

//		
//		int N = 5;
//		int R = 3;
//		int[] test = new int[N];
//		for (int i = 0; i < N; i++)
//			test[i] = i + 1;
//
//		Permutation permmng = new Permutation(test, R);
//		List<int[]> result = permmng.get_perm();
//		for (int[] ele : result)
//			System.out.println(Arrays.toString(ele));
		}
	}

}

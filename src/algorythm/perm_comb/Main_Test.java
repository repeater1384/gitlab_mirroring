package algorythm.perm_comb;

import java.util.Arrays;
import java.util.List;

public class Main_Test {

	public static void main(String[] args) {
//		PowerSet psmng = new PowerSet(new int[] {1,3,5,6,7,90});
//		List<List<Integer>> result = psmng.getPoserSet();
//		for(List<Integer> ele : result) {
//			System.out.println(ele);
		int SIZE = 5;
		int[] test = new int[SIZE];
		for (int i = 0; i < SIZE; i++)
			test[i] = i + 1;
		Combination permmng = new Combination(test, 3);
		List<int[]> result = permmng.get_comb();
		for (int[] ele : result)
			System.out.println(Arrays.toString(ele));
	}

}

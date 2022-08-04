package algorythm;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
//		PowerSet psmng = new PowerSet(new int[] {1,3,5,6,7,90});
//		List<List<Integer>> result = psmng.getPoserSet();
//		for(List<Integer> ele : result) {
//			System.out.println(ele);
		int[] test = new int[25];
		for (int i = 0; i < 25; i++)
			test[i] = i + 1;
		Permutation permmng = new Permutation(test, 6);
		List<int[]> result = permmng.get_perm();
		for (int[] ele : result)
			System.out.println(Arrays.toString(ele));
	}

}

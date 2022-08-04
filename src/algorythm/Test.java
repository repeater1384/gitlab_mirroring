package algorythm;

import java.util.List;

public class Test {

	public static void main(String[] args) {
		PowerSet psmng = new PowerSet(new int[] {1,3,5,6,7,90});
		List<List<Integer>> result = psmng.getPoserSet();
		for(List<Integer> ele : result) {
			System.out.println(ele);
		}
	}

}

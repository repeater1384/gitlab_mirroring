package algorythm;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {
	static int[] arr;
	static boolean[] selected;
	static List<List<Integer>> result;

	public PowerSet(int[] arr) {
		PowerSet.arr = arr;
		PowerSet.selected = new boolean[arr.length];
		result = new ArrayList<>();
		make(0);
	}

	void make(int idx) {
		if (idx == arr.length) {
			List<Integer> temp = new ArrayList<>();
			for (int i = 0; i < arr.length; i++) {
				if (selected[i])
					temp.add(arr[i]);
			}
			result.add(temp);
			return;
		}

		selected[idx] = true;
		make(idx + 1);
		selected[idx] = false;
		make(idx + 1);
	}

	public List<List<Integer>> getPoserSet() {
		return result;
	}

}

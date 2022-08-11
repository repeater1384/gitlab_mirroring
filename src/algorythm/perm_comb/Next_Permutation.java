package algorythm.perm_comb;

import java.util.Arrays;

public class Next_Permutation {

	public static int[] get_next_permutation(int[] arr) {
		int N = arr.length;
		for(int i = N-2;i>=0;i--) {
			if(arr[i]>=arr[i+1])continue;
			int j;
			for(j = N-1;j>=0;j--) {
				if(arr[i]<arr[j])break;
			}
			int temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
//			System.out.println("prv"+Arrays.toString(arr));
			Arrays.sort(arr, i+1, N);
//			System.out.println("sort"+Arrays.toString(arr));
			return arr;
		}
		return null;
	}
}

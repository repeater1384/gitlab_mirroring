//package algorythm.perm_comb;
//
//import java.util.Arrays;
//import java.util.Collections;
//
//public class Prev_Permutation {
//
//	public static int[] get_next_permutation(int[] arr) {
//		int N = arr.length;
//		for(int i = N-2;i>=0;i--) {
//			if(arr[i]<=arr[i+1])continue;
//			int j;
//			for(j = N-1;j>=0;j--) {
//				if(arr[i]>arr[j])break;
//			}
//			int temp = arr[i];
//			arr[i] = arr[j];
//			arr[j] = temp;
//			
//			Arrays.sort(arr, i+1, N,Collections.reverseOrder());
//			return arr;
//		}
//		return null;
//	}
//}

package algorythm.perm_comb;

import java.util.Arrays;

public class next_combination {
	public static boolean get_next_combination(int[] arr, int[] temp,int r) {
		int N = arr.length;
		
		for(int i = N-2;i>=0;i--) {
			if(temp[i]>=temp[i+1])continue;
			int j;
			for(j = N-1;j>=0;j--) {
				if(temp[i]<temp[j])break;
			}
			int t = temp[i];
			temp[i] = temp[j];
			temp[j] = t;
			Arrays.sort(temp, i+1, N);
			
			int idx = 0;
			int[] result = new int[r];
			for(int k = 0 ;k<N;k++)if(temp[k]==1)result[idx++]=arr[k];
			System.out.println(Arrays.toString(result));
			return true;
		}
		return false;
	}
}

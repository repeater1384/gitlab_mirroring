import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();

		int[] memory = new int[N];
		int[] needResource = new int[N];
		for (int i = 0; i < N; i++)
			memory[i] = sc.nextInt();
		for (int i = 0; i < N; i++)
			needResource[i] = sc.nextInt();
		int[] dp = new int[M+1];
		
		Arrays.fill(dp, Integer.MAX_VALUE);
		for(int i = 0 ; i<N;i++) {
			int[] temp = dp.clone();
			for(int m = 0 ; m<=M;m++) {
				if(memory[i] >= m) {
					temp[m] = Math.min(temp[m], needResource[i]);
				}else if (dp[m-memory[i]] != Integer.MAX_VALUE) {
					temp[m] = Math.min(temp[m], temp[m-memory[i]]+needResource[i]);
				}
			}
			if(i<=3)
				System.out.println(Arrays.toString(temp));
			dp = temp;
		}
		System.out.println(dp[M]);
		sc.close();
	}

}
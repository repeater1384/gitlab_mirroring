package solved.swea;
import java.util.*;

public class 햄버거 {
	static int[][] dp;
	static int[] ti;
	static int[] ki;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int L = sc.nextInt();
			dp = new int[N + 1][L + 1];
			ti = new int[N + 1];
			ki = new int[N + 1];
			for (int i = 1; i <= N; i++) {
				ti[i] = sc.nextInt();
				ki[i] = sc.nextInt();
			}
			System.out.printf("#%d %d\n", tc, dfs(N, L));
		}
		sc.close();
	}

	static int dfs(int i, int j) {
		if (i == 0 || j == 0 || dp[i][j] !=0) 
			return dp[i][j];
		
		if (j < ki[i])
			return dp[i][j] = dfs(i - 1, j);
		
		return dp[i][j] = Math.max(dfs(i - 1, j), dfs(i - 1, j - ki[i]) + ti[i]);
	}
}
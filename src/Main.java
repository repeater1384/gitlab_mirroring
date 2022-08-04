import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int MOD = 1_000_000;
		int[][][] dp = new int[N + 1][2][3];
		dp[1][0][0] = dp[1][0][1] = dp[1][1][0] = 1;

		for (int i = 2; i <= N; i++) {
			dp[i][0][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2]) % MOD;
			dp[i][0][1] = dp[i - 1][0][0];
			dp[i][0][2] = dp[i - 1][0][1];
			dp[i][1][0] = (dp[i - 1][0][0] + dp[i - 1][0][1] + dp[i - 1][0][2] + dp[i - 1][1][0] + dp[i - 1][1][1]
					+ dp[i - 1][1][2]) % MOD;
			dp[i][1][1] = dp[i - 1][1][0];
			dp[i][1][2] = dp[i - 1][1][1];
		}

<<<<<<< HEAD
		// a, i, j -> a모양으로 i,j위치에 배치 될수 있는 가짓수
		// 0 -> 가로 1 -> 세로 2 -> 대각선
		int[][][] dp = new int[3][N + 1][N + 1];
		dp[0][1][2] = 1;

		for (int i = 1; i <= N; i++) {
			for (int j = 3; j <= N; j++) {
				if (matrix[i - 1][j - 1] != 1) {
					dp[0][i][j] = dp[0][i][j - 1] + dp[2][i][j - 1];
					dp[1][i][j] = dp[1][i - 1][j] + dp[2][i - 1][j];
				}
				if (i - 2 >= 0 && matrix[i - 1][j - 1] != 1 && matrix[i - 1][j - 2] != 1 && matrix[i - 2][j - 1] != 1)
					dp[2][i][j] = dp[0][i - 1][j - 1] + dp[1][i - 1][j - 1] + dp[2][i - 1][j - 1];
			}
		}
		System.out.println(dp[0][N][N] + dp[1][N][N] + dp[2][N][N]);
=======
		int answer = (dp[N][0][0] + dp[N][0][1] + dp[N][0][2] + dp[N][1][0] + dp[N][1][1] + dp[N][1][2]) % MOD;
		System.out.println(answer);
		sc.close();
>>>>>>> 73a7c3d1495f7236c379a0b006f68fe4f3d58f35
	}
}
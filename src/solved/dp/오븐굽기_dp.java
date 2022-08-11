package solved.dp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 오븐굽기_dp {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		int MAX_SIZE = 100001; // 쿠키 1000개, 100초 -> 다 굽는데 최대 100000초.
		while (T-- != 0) {
			int N = Integer.parseInt(br.readLine());
			int[] A = new int[N];
			int[] B = new int[N];

			int a_sum = 0;
			for (int i = 0; i < N; i++) {

				StringTokenizer st = new StringTokenizer(br.readLine());
				A[i] = Integer.parseInt(st.nextToken());
				B[i] = Integer.parseInt(st.nextToken());
				a_sum += A[i];

			}

			// dp[i] -> A오븐이 i초만큼 일할때, B오븐이 일하는 시간
			int[] dp = new int[MAX_SIZE];
			Arrays.fill(dp, Integer.MAX_VALUE);
			dp[a_sum] = 0; // A가 다 일하면 B는 0초만큼 일함
			for (int i = 0; i < N; i++) {
				for (int t = 0; t < MAX_SIZE; t++) {
					if (dp[t] != Integer.MAX_VALUE) {
						// i번째 쿠키를
						dp[t - A[i]] = Math.min(dp[t - A[i]], dp[t] + B[i]);
					}
				}
			}
			
			int answer = Integer.MAX_VALUE;
			for (int t = 0; t < MAX_SIZE; t++)
				answer = Math.min(answer, Math.max(t, dp[t]));

			System.out.println(answer);

		}

	}

}
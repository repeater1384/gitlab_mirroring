package failed;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CF815_D {
	static List<List<Integer>> adjList;
	static int answer = 0;
	static int tempNode = -1;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		while (T-- > 0) {
			int N = sc.nextInt();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = sc.nextInt();
			int[][] table = new int[N][N];
			List<int[]> edges = new ArrayList<>();
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					table[i][j] = arr[i] ^ j;
					table[j][i] = arr[j] ^ i;
					if (table[i][j] < table[j][i])
						edges.add(new int[] { i, j });
				}
			}

			adjList = new ArrayList<>();
			for (int i = 0; i < N; i++)
				adjList.add(new ArrayList<Integer>());
			for (int[] edge : edges) {
				int a = edge[0];
				int b = edge[1];
				adjList.get(a).add(b);
			}
			int[] dp = new int[N];
			dp[N - 1] = 1;
			answer = 0;
			for (int i = N - 1; i >= 0; i--) {
				for (int j : adjList.get(i)) {
					dp[i] = Math.max(dp[i], dp[j] + 1);
				}
				answer = Math.max(dp[i], answer);
			}
			System.out.println(answer);
		}
	}

}
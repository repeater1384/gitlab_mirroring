package algorythm.dijkstra;

import java.util.Arrays;
import java.util.Scanner;

public class dijkstra {
	static int N;
	static int[][] adjMatrix;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				adjMatrix[i][j] = sc.nextInt();

		boolean[] visited = new boolean[N];
		int[] minDist = new int[N];
		Arrays.fill(minDist, Integer.MAX_VALUE);
		int start = 0;
		minDist[start] = 0;
		int cnt = N;
		while (cnt-- > 0) {

			int minCost = Integer.MAX_VALUE;
			int minCostV = -1;
			for (int cur = 0; cur < N; cur++) {
				if (!visited[cur] && minDist[cur] < minCost) {
					minCostV = cur;
					minCost = minDist[cur];
				}
			}

			visited[minCostV] = true;

			for (int next = 0; next < N; next++) {
				if (adjMatrix[minCostV][next] == 0 || visited[next])
					continue;
				minDist[next] = Math.min(minDist[next], minCost + adjMatrix[minCostV][next]);
			}

			System.out.println(Arrays.toString(minDist));
		}

		int end = N - 1;
		System.out.println(minDist[end]);

		sc.close();
	}
}

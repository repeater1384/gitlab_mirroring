package algorythm.MST;

import java.util.Arrays;
import java.util.Scanner;

public class Prim {
	static int N;
	static int[][] adjMatrix;
	static boolean[] visited;
	static int[] minEdge;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		adjMatrix = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				adjMatrix[i][j] = sc.nextInt();

		visited = new boolean[N];
		minEdge = new int[N];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		System.out.println(getMST());

	}

	static int getMST() {
		int cost = 0;
		int curIdx = 0;

		for (int i = 0; i < N - 1; i++) {
			visited[curIdx] = true;

			for (int next = 0; next < N; next++)
				if (adjMatrix[curIdx][next] != 0)
					minEdge[next] = Math.min(minEdge[next], adjMatrix[curIdx][next]);

			int minEdgeCost = Integer.MAX_VALUE;
			int minEdgeIdx = -1;
			for (int idx = 0; idx < N; idx++) {
				if (!visited[idx] && minEdgeCost > minEdge[idx]) {
					minEdgeCost = minEdge[idx];
					minEdgeIdx = idx;
				}
			}

			cost += minEdgeCost;
			curIdx = minEdgeIdx;

		}

		return cost;
	}

}

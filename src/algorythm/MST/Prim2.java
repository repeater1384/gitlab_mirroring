package algorythm.MST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Prim2 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = 7;
		int M = sc.nextInt();
		int PRIME = 131_071;
		List<Integer>[] adjList = new ArrayList[N];
		for (int i = 0; i < N; i++)
			adjList[i] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			int a = sc.nextInt();
			int b = sc.nextInt();
			int cost = sc.nextInt();
			adjList[a].add(b * PRIME + cost);
			adjList[b].add(a * PRIME + cost);
		}

		// prim
		int[] minEdge = new int[N];
		Arrays.fill(minEdge, Integer.MAX_VALUE);
		boolean[] visited = new boolean[N];
		int curIdx = 0;
		int cnt = N - 1;
		int totalCost = 0;
		while (cnt-- > 0) {
			visited[curIdx] = true;
			for (int toCost : adjList[curIdx]) {
				int to = toCost / PRIME;
				int cost = toCost % PRIME;
				minEdge[to] = Math.min(minEdge[to], cost);
			}

			System.out.println(Arrays.toString(minEdge));
			int nextIdx = -1;
			int nextCost = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				if (!visited[i] && nextCost > minEdge[i]) {
					nextCost = minEdge[i];
					nextIdx = i;
				}
			}
			totalCost += nextCost;

			curIdx = nextIdx;
		}

		System.out.println(totalCost);

	}

}

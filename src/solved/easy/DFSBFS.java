package solved.easy;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFSBFS {
	static int N;
	static List<List<Integer>> adjList;
	static StringBuilder sb;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int V = Integer.parseInt(st.nextToken());

		adjList = new ArrayList<>();
		for (int i = 0; i <= N; i++)
			adjList.add(new ArrayList<Integer>());

		while (M-- > 0) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		for (int i = 0; i <= N; i++)
			Collections.sort(adjList.get(i));
		
		sb = new StringBuilder();

		dfs(V, new boolean[N + 1]);
		sb.append("\n");
		bfs(V);
		System.out.println(sb.toString());
	}

	private static void dfs(int cur, boolean[] visited) {
		visited[cur] = true;
		sb.append(cur).append(' ');
		for (int next : adjList.get(cur)) {
			if (!visited[next])
				dfs(next, visited);
		}
	}

	private static void bfs(int start) {
		Queue<Integer> queue = new LinkedList<>();
		boolean[] visited = new boolean[N + 1];
		queue.add(start);
		visited[start] = true;
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			sb.append(cur).append(' ');
			for (int next : adjList.get(cur)) {
				if (!visited[next]) {
					queue.add(next);
					visited[next] = true;
				}
			}
		}
	}

}
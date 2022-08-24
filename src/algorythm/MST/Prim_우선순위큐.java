package algorythm.MST;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int v, cost;

	public Edge(int v, int cost) {
		super();
		this.v = v;
		this.cost = cost;
	}

	@Override
	public int compareTo(Edge o) {
		return this.cost - o.cost;
	}

}

class Prim_우선순위큐 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			st = new StringTokenizer(br.readLine());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());

			List<Edge>[] adjList = new ArrayList[V + 1];
			for (int i = 1; i <= V; i++)
				adjList[i] = new ArrayList<>();

			while (E-- > 0) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int cost = Integer.parseInt(st.nextToken());
				adjList[a].add(new Edge(b, cost));
				adjList[b].add(new Edge(a, cost));
			}

			long answer = 0;
			boolean[] visited = new boolean[V + 1];
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			pq.add(new Edge(1, 0));
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				if (visited[e.v])
					continue;

				int v = e.v;
				int cost = e.cost;
				visited[v] = true;
				answer += cost;

				for (Edge next : adjList[v])
					if (!visited[next.v])
						pq.add(next);
			}

			System.out.printf("#%d %d\n", tc, answer);
		}
	}

}
package solved.difficult;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.StringTokenizer;

class Edge implements Comparable<Edge> {
	int to, weight;

	public Edge(int to, int weight) {
		super();
		this.to = to;
		this.weight = weight;
	}

	@Override
	public int compareTo(Edge o) {
		return Integer.compare(this.weight, o.weight);
	}

}

public class k번째최단경로 {
	static int N, V, K;
	static int[][] adjMatrix;
	static PriorityQueue<Integer>[] allPath;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adjMatrix = new int[N + 1][N + 1];
		allPath = new PriorityQueue[N + 1];
		for (int i = 1; i <= N; i++) {
			allPath[i] = new PriorityQueue<Integer>((o1, o2) -> Integer.compare(o2, o1));
		}

		while (V-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int cost = Integer.parseInt(st.nextToken());
			adjMatrix[a][b] = cost;
		}

		dijkstra(1);
		for (int i = 1; i <= N; i++) {
			if (allPath[i].size() < K)
				System.out.println(-1);
			else {
				System.out.println(allPath[i].peek());
			}
		}
	}

	static void dijkstra(int start) {

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.add(new Edge(start, 0));
		allPath[start].add(0);

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();

			for (int i = 1; i <= N; i++) {
				if (adjMatrix[cur.to][i] == 0)
					continue;
				int cost = cur.weight + adjMatrix[cur.to][i];

				if (allPath[i].size() < K || allPath[i].peek() > cost) {
					if (allPath[i].size() == K)
						allPath[i].poll();
					pq.add(new Edge(i, cost));
					allPath[i].add(cost);
				}

			}
		}
	}
}
<<<<<<< HEAD
import java.util.Scanner;
 
public class Main {
	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		System.out.println(recursive(N,0));
		sc.close();
	}

	static int recursive(int cur, int count){
		if(cur<=1)
			return count;
		return Math.min(recursive(cur/2, count+1+(cur%2)), recursive(cur/3, count+1+(cur%3)));
	}	
=======
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
<<<<<<< HEAD
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
=======
import java.util.HashSet;
import java.util.List;
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

public class Main {
	static int N, V, K;
	static int[][] adjMatrix;
	static Set<Integer>[] allPath;
>>>>>>> 8e9fa7e16769e4b452b6e2f9dc7202df219bfbec

	public static void main(String[] args) throws IOException {
		StringTokenizer st = null;
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
<<<<<<< HEAD
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		List<Integer>[] adjList = new ArrayList[V + 1];
		for (int i = 1; i <= V; i++)
			adjList[i] = new ArrayList<Integer>();
		while (E-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			adjList[a].add(b);
			adjList[b].add(a);
		}

		// bfs
		Queue<int[]> queue = new LinkedList<>();
		int[] visited = new int[V + 1];
		boolean check = true;
		outer : for (int startV = 1; startV <= V; startV++) {
			if (visited[startV] != 0)
				continue;
			queue.add(new int[] { startV, 1 });
			visited[startV] = 1;

			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				int curV = cur[0];
				int curColor = cur[1];
				int otherColor = curColor == 1 ? -1 : 1;
				for (int nextV : adjList[curV]) {
					if (visited[nextV] == 0) {
						visited[nextV] = otherColor;
						queue.add(new int[] { nextV, otherColor });
					} else if (visited[nextV] == curColor) {
						check = false;
						break outer;
					}
				}
			}
		}

		System.out.println(check ? 1 : 0);
=======

		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		adjMatrix = new int[N + 1][N + 1];
		allPath = new HashSet[N + 1];
		for (int i = 1; i <= N; i++) {
			allPath[i] = new HashSet<Integer>();
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
				Integer[] temp = allPath[i].toArray(new Integer[0]);
				Arrays.sort(temp);
				System.out.println(temp[K - 1]);
			}
		}

	}

	static void dijkstra(int start) {

		PriorityQueue<Edge> pq = new PriorityQueue<>();

		pq.add(new Edge(start, 0));

		while (!pq.isEmpty()) {
			Edge cur = pq.poll();
			allPath[cur.to].add(cur.weight);
			
			for (int i = 1; i <= N; i++) {
				if(adjMatrix[cur.to][i]==0)continue;
				if(allPath[i].size()>K)continue;
				int cost = cur.weight + adjMatrix[cur.to][i];
				pq.add(new Edge(i, cost));
			}
		}
>>>>>>> 8e9fa7e16769e4b452b6e2f9dc7202df219bfbec
	}
>>>>>>> cf1618138d61548944e646de80b5cd271d97d223
}
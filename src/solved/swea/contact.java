package solved.swea;
import java.io.*;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class contact {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int length = Integer.parseInt(st.nextToken());
			int start = Integer.parseInt(st.nextToken());

			boolean[][] adjMatrix = new boolean[101][101];
			st = new StringTokenizer(br.readLine());
			while (length > 0) {
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				adjMatrix[a][b] = true;
				length -= 2;
			}

			Queue<Integer> queue = new LinkedList<>();
			boolean[] visited = new boolean[101];
			int[] distance = new int[101];
			queue.add(start);
			visited[start] = true;
			distance[start] = 0;
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				for (int next = 1; next < 101; next++) {
					if (adjMatrix[cur][next] && !visited[next]) {
						queue.add(next);
						visited[next] = true;
						distance[next] = distance[cur] + 1;
					}
				}
			}

			int maxDis = 0;
			int answer = 0;
			for (int i = 1; i < 101; i++) {
				if (distance[i] >= maxDis) {
					if (distance[i] == maxDis)
						answer = Math.max(answer, i);
					else
						answer = i;
					maxDis = distance[i];
				}
			}
			System.out.printf("#%d %d\n", tc, answer);

		}
		br.close();
	}
}
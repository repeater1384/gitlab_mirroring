import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C;
	static int cheeseSize;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };
	static int[][] arr;
	static boolean[][] visited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		arr = new int[R][C];

		cheeseSize = 0;
		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
				if (arr[i][j] == 1)
					cheeseSize++;
			}
		}

		int lastCheeseSize = cheeseSize;
		int round = 0;
		while (cheeseSize != 0) {
			round++;
			lastCheeseSize = cheeseSize;

			visited = new boolean[R][C];
			bfs();
			for (int i = 0; i < R; i++) {
				for (int j = 0; j < C; j++) {
					if (arr[i][j] == 2) {
						cheeseSize--;
						arr[i][j] = 0;
					}
				}
			}
		}
		System.out.println(round);
		System.out.println(lastCheeseSize);
	}

	static class Node {
		int row;
		int col;

		public Node(int row, int col) {
			this.row = row;
			this.col = col;
		}

		public void meltCheese() {
			for (int i = 0; i < 4; i++) {
				int nextRow = this.row + dr[i];
				int nextCol = this.col + dc[i];
				if (nextRow < R && nextCol < C && nextRow >= 0 && nextCol >= 0 && arr[nextRow][nextCol] == 1)
					arr[nextRow][nextCol] = 2;
			}
		}
	}

	static void bfs() {
		Queue<Node> queue = new LinkedList<>();
		visited[0][0] = true;
		queue.offer(new Node(0, 0));
		while (!queue.isEmpty()) {
			Node air = queue.poll();
			int cR = air.row;
			int cC = air.col;
			air.meltCheese();
			for (int i = 0; i < 4; i++) {
				int nR = cR + dr[i];
				int nC = cC + dc[i];
				if (nR < R && nC < C && nR >= 0 && nC >= 0 && !visited[nR][nC] && arr[nR][nC] == 0) {
					visited[nR][nC] = true;
					queue.offer(new Node(nR, nC));
				}
			}
		}
	}
}
package solved.swea;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

class 정사각형방_1861 {
	static int N;
	static int[][] arr;
	static boolean[][] visited;
	static boolean[][] new_visited;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	static int dfs(int x, int y) {
		int result = 0;
		LinkedList<int[]> stack = new LinkedList<>();
		visited = new boolean[N][N];
		visited[y][x] = true;
		new_visited[y][x] = true;
		stack.add(new int[] { x, y, 1 });
		while (!stack.isEmpty()) {
			int[] cur = stack.pollLast();
			int cx = cur[0];
			int cy = cur[1];
			int count = cur[2];
			result = Math.max(count, result);
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx < 0 || N <= nx || ny < 0 || N <= ny || visited[ny][nx])
					continue;
				
				if (arr[ny][nx] == arr[cy][cx] + 1) {
					visited[ny][nx] = true;
					new_visited[ny][nx] = true;
					stack.add(new int[] { nx, ny, count + 1 });
				}
			}
		}

		return result;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			arr = new int[N][N];
			new_visited = new boolean[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					arr[i][j] = sc.nextInt();

			int answer = -1;
			List<Integer> startNumList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!new_visited[i][j]) {
						int cur = dfs(j, i);
						if (cur > answer) {
							answer = cur;
							startNumList.clear();
							startNumList.add(arr[i][j]);
						} else if (cur == answer) {
							startNumList.add(arr[i][j]);
						}

					}
				}
			}
			Collections.sort(startNumList);
			int startNum = startNumList.get(0);
			System.out.printf("#%d %d %d\n", tc, startNum, answer);
		}
		sc.close();
	}
}
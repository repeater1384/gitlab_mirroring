package solved.스터디3주차;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

class 스터디3주차_미로2 {
	static char[][] arr;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			br.readLine();

			arr = new char[100][100];
			int sx = 0, sy = 0, ex = 0, ey = 0;
			for (int i = 0; i < 100; i++) {
				arr[i] = br.readLine().toCharArray();
				for (int j = 0; j < 100; j++) {
					if (arr[i][j] == '2') {
						sx = j;
						sy = i;
					}
					if (arr[i][j] == '3') {
						ex = j;
						ey = i;
					}
				}
			}

			System.out.printf("#%d %d\n", tc, dfs(sx, sy, ex, ey));
		}
		br.close();
	}

	static int dfs(int sx, int sy, int ex, int ey) {
		Stack<int[]> stack = new Stack<>();
		boolean[][] visited = new boolean[100][100];
		stack.add(new int[] { sx, sy });
		visited[sy][sx] = true;

		while (!stack.isEmpty()) {
			int[] cur = stack.pop();
			int cx = cur[0];
			int cy = cur[1];
			if (cx == ex && cy == ey)
				return 1;
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (nx < 0 || 100 <= nx || ny < 0 || 100 <= ny || visited[ny][nx] || arr[ny][nx] == '1')
					continue;
				stack.add(new int[] { nx, ny });
				visited[ny][nx] = true;
			}
		}
		return 0;
	}
}
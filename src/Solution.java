import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Solution {
	static int ROUND, N, M;
	static int[][] mainMatrix;
	static int[][] processMatrix;

	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };
	static int answer;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			ROUND = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			mainMatrix = new int[N][M];
			answer = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < M; j++) {
					mainMatrix[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			dfs(0, new int[ROUND]);
			System.out.printf("#%d %d\n", tc, answer);
		}
	}

	static void dfs(int depth, int[] result) {
		if (depth == ROUND) {
			fallEveryBall(result);
			answer = Math.min(answer, countRemainBrick());
			return;
		}
		for (int j = 0; j < M; j++) {
			result[depth] = j;
			dfs(depth + 1, result);
		}
	}

	static void fallEveryBall(int[] result) {
		processMatrix = new int[N][M];
		for (int i = 0; i < N; i++)
			processMatrix[i] = mainMatrix[i].clone();

		for (int col : result) {
			for (int i = 0; i < N; i++) {
				if (processMatrix[i][col] != 0) {
					crash(col, i);
					fallBrick();
					break;
				}
			}
		}
	}

	static void crash(int cx, int cy) {
		int bombSize = processMatrix[cy][cx];
		processMatrix[cy][cx] = 0;
		for (int k = 0; k < 4; k++) {
			for (int bs = 0; bs < bombSize; bs++) {
				int nx = cx + dx[k] * bs;
				int ny = cy + dy[k] * bs;
				if (0 <= nx && nx < M && 0 <= ny && ny < N && processMatrix[ny][nx] != 0)
					crash(nx, ny);
			}
		}
	}

	static void fallBrick() {
		int[][] fallMatrix = new int[N][M];
		for (int j = 0; j < M; j++) {
			int idx = N - 1;
			for (int i = N - 1; i >= 0; i--) {
				if (processMatrix[i][j] == 0)
					continue;
				fallMatrix[idx--][j] = processMatrix[i][j];
			}
		}

		processMatrix = fallMatrix;
	}

	static int countRemainBrick() {
		int remainBrick = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (processMatrix[i][j] > 0)
					remainBrick++;
			}
		}
		return remainBrick;
	}
}
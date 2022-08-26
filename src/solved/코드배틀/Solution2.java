package solved.코드배틀;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
// 헌터

class Solution2 {
	static int[][] matrix;
	static int N;
	static int CNT;
	static int[][] monster;
	static int[][] client;
	static int answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			matrix = new int[N][N];
			CNT = 0;
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					CNT = Math.max(CNT, matrix[i][j]);
				}
			}
			monster = new int[CNT + 1][2];
			client = new int[CNT + 1][2];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					int cur = matrix[i][j];
					if (cur > 0)
						monster[cur] = new int[] { i, j };
					if (cur < 0)
						client[-1 * cur] = new int[] { i, j };
				}
			}

			answer = Integer.MAX_VALUE;
			dfs(new int[CNT * 2], new boolean[CNT * 2], 0);
			System.out.printf("#%d %d\n", tc, answer);
		}
	}

	static void dfs(int[] result, boolean[] visited, int depth) {
		if (depth == CNT * 2) {
			int cur = 0;
			int sx = 0;
			int sy = 0;
			
			for (int num : result) {
				if (num <= CNT) {
					cur += getDis(sx, sy, monster[num][1], monster[num][0]);
					sy = monster[num][0];
					sx = monster[num][1];
				} else {
					cur += getDis(client[num - CNT][1], client[num - CNT][0], sx, sy);
					sy = client[num - CNT][0];
					sx = client[num - CNT][1];
				}
			}
			
			answer = Math.min(answer, cur);
			return;
			
		}
		for (int i = 1; i <= CNT * 2; i++) {
			if ((i <= CNT && !visited[i - 1]) || (i > CNT && visited[i - 1 - CNT] && !visited[i - 1])) {
				visited[i - 1] = true;
				result[depth] = i;
				dfs(result, visited, depth + 1);
				visited[i - 1] = false;
			}
		}
	}

	static int getDis(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
}
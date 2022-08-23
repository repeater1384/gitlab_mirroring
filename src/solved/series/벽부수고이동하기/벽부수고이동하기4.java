package solved.series.벽부수고이동하기;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class 벽부수고이동하기4 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();

		int[][] matrix = new int[N][M];
		int[][] emptyCnt = new int[N][M];
		int[][] answer = new int[N][M];
		int[][] emptyIdentify = new int[N][M];
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		for (int i = 0; i < N; i++) {
			char[] temp = sc.next().toCharArray();
			for (int j = 0; j < M; j++) {
				matrix[i][j] = temp[j] - '0';
			}
		}

		Queue<int[]> queue3 = new LinkedList<>();
		int identifyNum = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (matrix[i][j] == 0 && emptyCnt[i][j] == 0) {
					Queue<int[]> queue = new LinkedList<>();
					Queue<int[]> queue2 = new LinkedList<>();

					queue.add(new int[] { i, j });
					matrix[i][j] = -1;
					int cnt = 0;

					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						queue2.add(cur);
						int ci = cur[0];
						int cj = cur[1];
						cnt++;
						for (int k = 0; k < 4; k++) {
							int ni = ci + dy[k];
							int nj = cj + dx[k];
							if (0 <= ni && ni < N && 0 <= nj && nj < M && matrix[ni][nj] == 0) {
								queue.add(new int[] { ni, nj });
								matrix[ni][nj] = -1;
							}
						}
					}

					while (!queue2.isEmpty()) {
						int[] cur = queue2.poll();
						int ci = cur[0];
						int cj = cur[1];
						emptyCnt[ci][cj] = cnt;
						emptyIdentify[ci][cj] = identifyNum;
					}

					identifyNum++;
				}

				if (matrix[i][j] == 1) {
					queue3.add(new int[] { i, j });
				}
			}
		}
		while (!queue3.isEmpty()) {
			int[] cur = queue3.poll();
			int ci = cur[0];
			int cj = cur[1];
			answer[ci][cj]++;
			Set<Integer> set = new HashSet<>();
			for (int k = 0; k < 4; k++) {
				int ni = ci + dy[k];
				int nj = cj + dx[k];
				if (0 <= ni && ni < N && 0 <= nj && nj < M && !set.contains(emptyIdentify[ni][nj])) {
					answer[ci][cj] += emptyCnt[ni][nj];
					set.add(emptyIdentify[ni][nj]);
				}
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++)
				sb.append(answer[i][j] % 10);
			sb.append('\n');
		}
		System.out.println(sb.toString());
	}

}
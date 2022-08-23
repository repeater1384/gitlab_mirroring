package solved.스터디4주차;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스터디4주차_홈방범서비스 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int[][] arr = new int[N][N];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					arr[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			int answer = 0;
			for (int si = 0; si < N; si++) {
				for (int sj = 0; sj < N; sj++) {

					for (int K = N + 1; K >= 1; K--) {
						int curCost = getCost(K);
						int homeCnt = 0;
						for (int i = 0; i < N; i++) {
							for (int j = 0; j < N; j++) {
								if (isInArea(K, si, sj, i, j)) {
									if (arr[i][j] == 1)
										homeCnt++;
								}
							}
						}
						if (curCost <= homeCnt * M) {
							answer = Math.max(answer, homeCnt);
						}

					}
				}
			}
			System.out.printf("#%d %d\n", tc, answer);
		}
	}

	static int getCost(int K) {
		return K * K + (K - 1) * (K - 1);
	}

	static boolean isInArea(int K, int si, int sj, int i, int j) {
		return K > Math.abs(si - i) + Math.abs(sj - j);
	}

}
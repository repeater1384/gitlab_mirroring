package A형시험;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Town {
	int x, y, dis;

	public Town(int x, int y, int dis) {
		super();
		this.x = x;
		this.y = y;
		this.dis = dis;
	}
}

class Solution {

	static int[][] matrix;
	static int N; // 마을 개수
	static int answer;

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("C:\\SSAFY\\SSAFY_GIT\\pr1\\input1.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		int T = Integer.parseInt(br.readLine().replace(" ", ""));
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine().replace(" ", ""));
			Town[] townArr = new Town[N];
			matrix = new int[31][31];

			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken()) + 15;
				int y = Integer.parseInt(st.nextToken()) + 15;
				int dis = Integer.parseInt(st.nextToken());
				townArr[i] = new Town(x, y, dis);
				matrix[y][x] = Integer.MIN_VALUE;
			}

			for (int i = 0; i < 31; i++) {
				for (int j = 0; j < 31; j++) {
					for (Town town : townArr) {
						if (town.x == j && town.y == i)
							continue;
						if (Math.abs(i - town.y) + Math.abs(j - town.x) <= town.dis)
							matrix[i][j]++;
					}
				}
			}

			answer = Integer.MAX_VALUE;

			// 1개만 건설 가능한 경우.
			for (int i = 0; i < 31; i++) {
				for (int j = 0; j < 31; j++) {
					if (matrix[i][j] == N) {
						int cur = 0;
						for (Town town : townArr) {
							cur += Math.abs(i - town.y) + Math.abs(j - town.x);
						}
						answer = Math.min(answer, cur);
					}
				}
			}

			if (answer == Integer.MAX_VALUE) {
				// 2개 건설해야 하는 경우.
				List<int[]> canPlace = new ArrayList<>();
				for (int i = 0; i < 31; i++) {
					for (int j = 0; j < 31; j++) {
						if (matrix[i][j] > 0)
							canPlace.add(new int[] { i, j });
					}
				}
				int canPlaceSize = canPlace.size();
//				for (int[] is : canPlace) {
//					System.out.println(Arrays.toString(is));
//				}
				for (int i = 0; i < canPlaceSize - 1; i++) {
					next: for (int j = i + 1; j < canPlaceSize; j++) {
						int curDis = 0;
						int[] place1 = canPlace.get(i);
						int[] place2 = canPlace.get(j);

						int p1y = place1[0];
						int p1x = place1[1];
						int p2y = place2[0];
						int p2x = place2[1];

						for (Town t : townArr) {
							int curTownDis = Integer.MAX_VALUE;
							int dis1 = Math.abs(p1y - t.y) + Math.abs(p1x - t.x);
							int dis2 = Math.abs(p2y - t.y) + Math.abs(p2x - t.x);
							if (dis1 <= t.dis || dis2 <= t.dis) {
								curTownDis = Math.min(dis1, dis2);
							}

							if (curTownDis == Integer.MAX_VALUE)
								continue next;
							curDis += curTownDis;
						}
						answer = Math.min(answer, curDis);

					}
				}

			}

			System.out.printf("#%d %d\n", tc, answer == Integer.MAX_VALUE ? -1 : answer);
//			for (int[] row : matrix) {
//				System.out.println(Arrays.toString(row));
//			}
		}
	}
}
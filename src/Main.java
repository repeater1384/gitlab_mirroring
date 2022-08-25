import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class Point {
	int y, x;

	public Point(int y, int x) {
		this.y = y;
		this.x = x;
	}

	@Override
	public String toString() {
		return "Point [y=" + y + ", x=" + x + "]";
	}

}

public class Main {
	static int N, M, K;
	static int[][] matrix;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		LinkedList<Point>[] teamRail = new LinkedList[M];
		boolean[] reverseTeam = new boolean[M];

		for (int i = 0; i < M; i++)
			teamRail[i] = new LinkedList<>();

		matrix = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				matrix[i][j] = sc.nextInt();

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		// team 정보 받아오기.
		boolean[][] visited = new boolean[N][N];

		int teamIdx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j] && matrix[i][j] == 1) {
					// 새로운 팀 발견
					Queue<Point> queue = new LinkedList<>();
					queue.add(new Point(i, j));
					visited[i][j] = true;

					while (!queue.isEmpty()) {
						Point cur = queue.poll();
						int cy = cur.y;
						int cx = cur.x;
						int cNum = matrix[cy][cx];
						teamRail[teamIdx].add(cur);
						matrix[cy][cx] = 9;
						for (int k = 0; k < 4; k++) {
							int ny = cy + dy[k];
							int nx = cx + dx[k];
							if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[ny][nx]) {
								if (cNum == 1 && matrix[ny][nx] == 3)
									continue;
								if (matrix[ny][nx] == 2 || matrix[ny][nx] == 3) {
									queue.add(new Point(ny, nx));
									visited[ny][nx] = true;
								}
							}
						}
					}

					teamIdx++;
				}

			}
		}
		int answer = 0;
		for (int round = 0; round < K; round++) {
//			System.out.println(teamRail[0]+" "+answer+" "+reverseTeam[0]);
//			printArr();
			// 1. 머리쪽으로 한칸 가기.
			for (int t = 0; t < M; t++) {
				Point head;
				if (!reverseTeam[t]) {
					head = teamRail[t].get(0);
				} else {
					head = teamRail[t].get(teamRail[t].size() - 1);
				}

				Point newHead = null;
				for (int k = 0; k < 4; k++) {
					int ny = head.y + dy[k];
					int nx = head.x + dx[k];
					if (0 <= nx && nx < N && 0 <= ny && ny < N && matrix[ny][nx] == 4) {
						matrix[ny][nx] = 9;
						newHead = new Point(ny, nx);
						break;
					}
				}

				// 빈 공간이 없는 팀이면.
				if (newHead == null) {
					if (!reverseTeam[t]) {
						teamRail[t].add(teamRail[t].poll());
					} else {
						teamRail[t].addFirst(teamRail[t].pollLast());
					}
				} else {
					if (!reverseTeam[t]) {
						teamRail[t].addFirst(newHead);
						Point remove = teamRail[t].pollLast();
						matrix[remove.y][remove.x] = 4;
					} else {
						teamRail[t].add(newHead);
						Point remove = teamRail[t].poll();
						matrix[remove.y][remove.x] = 4;
					}
				}
			}
			// 2. 공던지기
			int curRound = round % (N * 4);
			int crashY = -1, crashX = -1;

			if (curRound / N == 0) {
				int i = curRound % N;
				for (int j = 0; j < N; j++) {
					if (matrix[i][j] == 9) {
						crashY = i;
						crashX = j;
						break;
					}
				}
			}

			if (curRound / N == 1) {
				int j = curRound % N;
				for (int i = N - 1; i >= 0; i--) {
					if (matrix[i][j] == 9) {
						crashY = i;
						crashX = j;
						break;
					}
				}
			}

			if (curRound / N == 2) {
				int i = N - 1 - (curRound % N);
				for (int j = N - 1; j >= 0; j--) {
					if (matrix[i][j] == 9) {
						crashY = i;
						crashX = j;
						break;
					}
				}
			}

			if (curRound / N == 3) {
				int j = N - 1 - (curRound % N);
				for (int i = 0; i < N; i++) {
					if (matrix[i][j] == 9) {
						crashY = i;
						crashX = j;
						break;
					}
				}
			}

			// 충돌한 사람이 있으면.
			if (crashY != -1 && crashX != -1) {
				outer: for (int t = 0; t < M; t++) {
					if (!reverseTeam[t]) {
						for (int idx = 0; idx < teamRail[t].size(); idx++) {
							Point p = teamRail[t].get(idx);
							if (crashY == p.y && crashX == p.x) {
								answer += (idx + 1) * (idx + 1);
								reverseTeam[t] = true;
								break outer;
							}
						}
					} else {
						int size = teamRail[t].size();
						for (int idx = 0; idx < teamRail[t].size(); idx++) {
							Point p = teamRail[t].get(size - 1 - idx);
							if (crashY == p.y && crashX == p.x) {
								answer += (idx + 1) * (idx + 1);
								reverseTeam[t] = false;
								break outer;
							}
						}
					}
				}
			}

		}
		System.out.println(answer);
		for (int i = 0; i < M; i++) {
			System.out.println(teamRail[i]);
		}

		sc.close();
	}

	static void printArr() {
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
	}
}
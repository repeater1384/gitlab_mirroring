package solved.코드트리;
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
		return "P [y=" + y + ", x=" + x + "]";
	}

}

public class 꼬리잡기흑흑흑 {
	static int N, M, K;
	static int[][] matrix;
	static LinkedList<Point>[] teamRail;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };
	static boolean[] reverseTeam;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		matrix = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				matrix[i][j] = sc.nextInt();

		reverseTeam = new boolean[M];
		teamRail = new LinkedList[M];

		for (int i = 0; i < M; i++)
			teamRail[i] = new LinkedList<>();
		teamSetting();

		for (int round = 0; round < K; round++) {
			// 1. 머리쪽으로 한칸 가기.
			move();
			// 2. 공던지기 후 점수매기기
			Point crash = throwBall(round);
			scoring(crash);

			int[][] teno = new int[N][N];
			for (int t = 0; t < M; t++) {
				if (!reverseTeam[t]) {
					for (int idx = 0; idx < teamRail[t].size(); idx++) {
						Point p = teamRail[t].get(idx);
						teno[p.y][p.x] = idx + 1;
					}
				} else {
					int size = teamRail[t].size();
					for (int idx = 0; idx < teamRail[t].size(); idx++) {
						Point p = teamRail[t].get(size - 1 - idx);
						teno[p.y][p.x] = idx + 1;
					}
				}
			}

		}
		System.out.println(answer);

		sc.close();

	}

	private static void move() {
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
					teamRail[t].addFirst(teamRail[t].pollLast());
				} else {
					teamRail[t].add(teamRail[t].poll());
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
//		Queue<>

	}

	static void scoring(Point crash) {
		if (crash == null)
			return;

		for (int t = 0; t < M; t++) {
			int size = teamRail[t].size();
			for (int idx = 0; idx < size; idx++) {
				Point p = !reverseTeam[t] ? teamRail[t].get(idx) : teamRail[t].get(size - 1 - idx);
				if (crash.y == p.y && crash.x == p.x) {
					answer += (idx + 1) * (idx + 1);
					reverseTeam[t] = reverseTeam[t] ? false : true;
					return;
				}
			}
		}
	}

	static void teamSetting() {
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
	}

	static Point throwBall(int round) {
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

		if (crashY == -1 && crashX == -1)
			return null;
		return new Point(crashY, crashX);
	}

	static void printArr(int[][] matrix) {
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
	}

	static void printArr() {
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
	}
}
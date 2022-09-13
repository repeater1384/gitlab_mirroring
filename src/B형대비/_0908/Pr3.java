package B형대비._0908;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pr3 {

	static class Ball {
		int ballNum, y, x, weight, dir;
		boolean isLive = true;

		public Ball(int ballNum, int y, int x, int weight, int dir) {
			super();
			this.ballNum = ballNum;
			this.y = y;
			this.x = x;
			this.weight = weight;
			this.dir = dir;
		}

	}

	// U D R L
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { 1, -1, 0, 0 };
	static int T, N;
	static int SIZE = 4001;
	static int[][] check = new int[SIZE][SIZE];

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());

			// check[y][x] -> (y,x)에 있는 공의 번호.

			Ball[] balls = new Ball[N + 1];
			for (int n = 1; n <= N; n++) {
				st = new StringTokenizer(br.readLine());
				int x = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int y = (Integer.parseInt(st.nextToken()) + 1000) * 2;
				int weight = Integer.parseInt(st.nextToken());
				switch (st.nextToken().charAt(0)) {
				case 'U':
					balls[n] = new Ball(n, y, x, weight, 0);
					break;
				case 'D':
					balls[n] = new Ball(n, y, x, weight, 1);
					break;
				case 'R':
					balls[n] = new Ball(n, y, x, weight, 2);
					break;
				case 'L':
					balls[n] = new Ball(n, y, x, weight, 3);
					break;
				}
				check[y][x] = n;
			}
			int answer = -1;
			for (int time = 1; time <= SIZE; time++) {
//				for (Ball cur : balls) {
//					if (cur == null)
//						continue;
//					System.out.println(cur.ballNum + " " + cur.x + " " + cur.y + " " + cur.weight + " " + cur.isLive);
//				}
//				System.out.println("----------------------");
				for (Ball cur : balls) {
					if (cur == null || !cur.isLive)
						continue;

					check[cur.y][cur.x] = 0;
					int ny = cur.y + dy[cur.dir];
					int nx = cur.x + dx[cur.dir];
					if (!check(ny, nx)) {
						cur.isLive = false;
					} else {
						cur.y = ny;
						cur.x = nx;

						if (check[ny][nx] == 0) {
							check[ny][nx] = cur.ballNum;
						} else {
							int otherBallNum = check[ny][nx];
							if (balls[otherBallNum].weight == cur.weight) {
								if (otherBallNum > cur.ballNum) {
									cur.isLive = false;
								} else {
									balls[otherBallNum].isLive = false;
									check[ny][nx] = cur.ballNum;
								}
							} else {
								if (balls[otherBallNum].weight > cur.weight) {
									cur.isLive = false;
								} else {
									balls[otherBallNum].isLive = false;
									check[ny][nx] = cur.ballNum;

								}
							}
							answer = time;
						}
					}
				}
			}
			System.out.println(answer);

			// init;
			for (Ball cur : balls) {
				if (cur == null || !cur.isLive)
					continue;
				check[cur.y][cur.x] = 0;
			}
		}
	}

	static boolean check(int x, int y) {
		return 0 <= x && x < SIZE && 0 <= y && y < SIZE;
	}

}

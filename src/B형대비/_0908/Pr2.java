package B형대비._0908;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Pr2 {
	
	static class Ball {
		int y, x, dir;
		boolean isLive = true;

		public Ball(int y, int x, int dir) {
			super();
			this.y = y;
			this.x = x;
			this.dir = dir;
		}

		void next() {
			int ny = y + dy[dir];
			int nx = x + dx[dir];
			if (0 <= nx && nx < N && 0 <= ny && ny < N) {
				y = ny;
				x = nx;
			} else {
				dir = nextDir[dir];
			}
		}

	}

	// U D R L
	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[] nextDir = { 1, 0, 3, 2 };
	static int T, N, M;
	static int[][] check;

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;

		T = Integer.parseInt(br.readLine());
		while (T-- > 0) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			Ball[] balls = new Ball[M];
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int i = Integer.parseInt(st.nextToken()) - 1;
				int j = Integer.parseInt(st.nextToken()) - 1;
				switch (st.nextToken().charAt(0)) {
				case 'U':
					balls[m] = new Ball(i, j, 0);
					break;
				case 'D':
					balls[m] = new Ball(i, j, 1);
					break;
				case 'R':
					balls[m] = new Ball(i, j, 2);
					break;
				case 'L':
					balls[m] = new Ball(i, j, 3);
					break;
				}
			}
			
			
			for (int i = 0; i < 2 * N; i++) {
				check = new int[N][N];

				for (Ball cur : balls) {
					if (!cur.isLive)
						continue;
					cur.next();
					check[cur.y][cur.x]++;
				}

				for (Ball cur : balls) {
					if (cur.isLive &&check[cur.y][cur.x] >= 2) {
						cur.isLive = false;
						M--;
					}
				}
			}
			System.out.println(M);
		}
	}

}

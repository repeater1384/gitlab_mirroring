package solved.코드트리;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class EscapeMan {
	int dir, d;

	public EscapeMan(int dir, int d) {
		super();
		this.dir = dir;
		this.d = d;
	}

	@Override
	public String toString() {
		return "EscapeMan [dir=" + dir + ", d=" + d + "]";
	}

}

public class 술래잡기 {

	static int N, M, H, K;
	static List<EscapeMan>[][] mainMatrix;
	static boolean[][] treeMatrix;
	static int SX, SY, SDir; // 술래 x,y,dir
	static List<int[]> SLMOVE;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { -1, 0, 1, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		mainMatrix = new ArrayList[N][N];
		treeMatrix = new boolean[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				mainMatrix[i][j] = new ArrayList<>();

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int dir = Integer.parseInt(st.nextToken());
			mainMatrix[r][c].add(new EscapeMan(dir, dir == 1 ? 1 : 3));
		}

		for (int i = 0; i < H; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			treeMatrix[r][c] = true;
		}

		// make.
		SLMOVE();
		SX = N / 2;
		SY = N / 2;
		SDir = 0;

		int answer = 0;
		for (int turn = 1; turn <= K; turn++) {
//
//			int[][] temp = new int[N][N];
//			for (int i = 0; i < N; i++)
//				for (int j = 0; j < N; j++)
//					temp[i][j] = mainMatrix[i][j].size();
//			for (int[] is : temp) {
//				System.out.println(Arrays.toString(is));
//			}
//			System.out.println();

			// 좌,우, 상,하
			move();
//			temp = new int[N][N];
//			for (int i = 0; i < N; i++)
//				for (int j = 0; j < N; j++)
//					temp[i][j] = mainMatrix[i][j].size();
//			for (int[] is : temp) {
//				System.out.println(Arrays.toString(is));
//			}
//			System.out.println("-------------");

			int[] S = SLMOVE.get(turn % SLMOVE.size());
			SY = S[0];
			SX = S[1];
			SDir = S[2];
			// 도망자 죽이고 점수계산.
			for (int dis = 0; dis < 3; dis++) {
				int ny = SY + dy[SDir] * dis;
				int nx = SX + dx[SDir] * dis;
				if (0 <= nx && nx < N && 0 <= ny && ny < N) {
					if (treeMatrix[ny][nx])
						continue;
					if (mainMatrix[ny][nx].size() > 0) {
						answer += turn * mainMatrix[ny][nx].size();
						mainMatrix[ny][nx] = new ArrayList<>();
					}
				}
			}
		}
		System.out.println(answer);
	}

	static void SLMOVE() {

		int sx = N / 2;
		int d = 0;
		int sy = N / 2;

		SLMOVE = new ArrayList<>();
		SLMOVE.add(new int[] { N / 2, N / 2, 0 });
		List<Integer> moveAmount = new ArrayList<>();

		for (int i = 1; i < N; i++) {
			moveAmount.add(i);
			moveAmount.add(i);
		}
		moveAmount.add(N - 1);

		for (int m : moveAmount) {
			for (int i = 0; i < m - 1; i++) {
				sx += dx[d];
				sy += dy[d];
				SLMOVE.add(new int[] { sy, sx, d });
			}
			sx += dx[d];
			sy += dy[d];
			d = (d + 1) % 4;
			SLMOVE.add(new int[] { sy, sx, d });
		}
		SLMOVE.remove(SLMOVE.size() - 1);
		SLMOVE.add(new int[] { 0, 0, 2 });
		d = 2;
		sx = 0;
		sy = 0;
		for (int k = moveAmount.size() - 1; k >= 0; k--) {
			int m = moveAmount.get(k);
			for (int i = 0; i < m - 1; i++) {
				sx += dx[d];
				sy += dy[d];
				SLMOVE.add(new int[] { sy, sx, d });
			}
			sx += dx[d];
			sy += dy[d];
			d = (d + 3) % 4;
			SLMOVE.add(new int[] { sy, sx, d });
		}

		SLMOVE.remove(SLMOVE.size() - 1);

//		for (int[] a : SLMOVE) {
//			System.out.println(Arrays.toString(a));
//		}
//
//		System.out.println(SLMOVE.size());
	}

	static void move() {
		// 도망자들의 이동.
		List<EscapeMan>[][] temp = new ArrayList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				temp[i][j] = new ArrayList<>();
		// 좌,우, 상,하
		int[] dx = { -1, 1, 0, 0 };
		int[] dy = { 0, 0, -1, 1 };

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (Math.abs(i - SY) + Math.abs(j - SX) <= 3) {
//					System.out.println(i + " " + j + " " + SY + " " + SX);
					for (int idx = 0; idx < mainMatrix[i][j].size(); idx++) {
						EscapeMan cur = mainMatrix[i][j].get(idx);
						int r = i;
						int c = j;
						int d = cur.d;
						int dir = cur.dir;

						r += dy[d];
						c += dx[d];
						if (0 <= r && r < N && 0 <= c && c < N) {
							if (r == SY && c == SX) {
								r -= dy[d];
								c -= dx[d];
							}
						} else {
							if (dir == 1) {
								if (d == 1)
									d = 0;
								else
									d = 1;
							}
							if (dir == 2) {
								if (d == 2)
									d = 3;
								else
									d = 2;
							}

							r += dy[d] * 2;
							c += dx[d] * 2;
							if (r == SY && c == SX) {
								r -= dy[d];
								c -= dx[d];
							}
						}
						temp[r][c].add(new EscapeMan(dir, d));
					}
				} else {
					for (int idx = 0; idx < mainMatrix[i][j].size(); idx++) {
						EscapeMan cur = mainMatrix[i][j].get(idx);
						temp[i][j].add(cur);
					}
				}
			}
		}
		mainMatrix = temp;
//		for (int i = 0; i < N; i++)
//			for (int j = 0; j < N; j++)
//				for (int k = 0; k < temp[i][j].size(); k++)
//					System.out.println(i + " " + j + " " + temp[i][j].get(k));
	}
}
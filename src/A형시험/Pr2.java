package A형시험;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

class Core {
	int y, x;

	public Core(int y, int x) {
		super();
		this.y = y;
		this.x = x;
	}

}

class Pr2 {
	static int N;
	static int[][] matrix;

	static List<Core> coreList;
	static int answer;

	static int maxConnect;

	static List<Integer> coreIdxSuit;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
//		System.setIn(new FileInputStream("C:\\SSAFY\\SSAFY_GIT\\pr2\\input2.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int T = Integer.parseInt(br.readLine());
		for (int tc = 1; tc <= T; tc++) {
			N = Integer.parseInt(br.readLine());
			matrix = new int[N][N];
			coreList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					matrix[i][j] = Integer.parseInt(st.nextToken());
					if (matrix[i][j] == 1 && (i != 0 && i != N - 1 && j != 0 && j != N - 1))
						coreList.add(new Core(i, j));
				}
			}
			
			maxConnect = 0;
			answer = 0;

//			for (int flag = 0; flag < (1 << coreList.size()); flag++) {
//				coreIdxSuit = new ArrayList<>();
//				for (int coreIdx = 0; coreIdx < coreList.size(); coreIdx++) {
//					if ((flag & (1 << coreIdx)) != 0)
//						coreIdxSuit.add(coreIdx);
//				}
//				dfs(0);
//			}
			
			for (int round = coreList.size(); round >= 0; round--) {
				if (maxConnect != 0)
					break;
				comb(0, new int[round], round, 0);
			}
//			for (int[] row : matrix) {
//				System.out.println(Arrays.toString(row));
//			}
//			for (Core c : coreList) {
//				System.out.println(c);
//			}

			System.out.printf("#%d %d\n", tc, answer);
		}
	}

	static void comb(int depth, int[] result, int round, int idx) {
		if (depth == round) {
//			System.out.println(Arrays.toString(result));
			coreIdxSuit = new ArrayList<>();
			for (int i = 0; i < depth; i++)
				coreIdxSuit.add(result[i]);
			dfs(0);
			
			return;
		}

		for (int i = idx; i < coreList.size(); i++) {
			result[depth] = i;
			comb(depth + 1, result, round, i+1);
		}
	}

	static void dfs(int depth) {
//		if (coreIdxSuit.size() < maxConnect)
//			return;

		if (depth == coreIdxSuit.size()) {
			int count = 0;
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					if (matrix[i][j] == 2)
						count++;

			if (coreIdxSuit.size() > maxConnect) {
				answer = count;
				maxConnect = coreIdxSuit.size();
			} else if (coreIdxSuit.size() == maxConnect) {
				answer = Math.min(answer, count);
			}
			
//			for (int[] row : matrix) {
//				System.out.println(Arrays.toString(row));
//			}
//			System.out.println("----");
			return;
		}
//
//		int[][] copyArr = new int[N][N];
//		for (int i = 0; i < N; i++)
//			copyArr[i] = matrix[i].clone();

		Core curCore = coreList.get(coreIdxSuit.get(depth));
		int sx = curCore.x;
		int sy = curCore.y;

//		for (int[] is : matrix) {
//			System.out.println(Arrays.toString(is));
//		}
//		System.out.println();
		for (int k = 0; k < 4; k++) {
			int cx = sx;
			int cy = sy;

			while (true) {
				cx += dx[k];
				cy += dy[k];
				if (cx < 0 || cx == N || cy < 0 || cy == N) {
					dfs(depth + 1);
					break;
				} else if (matrix[cy][cx] == 0) {
					matrix[cy][cx] = 2;
				} else {
					break;
				}

			}

//			for (int i = 0; i < N; i++)
//				matrix[i] = copyArr[i].clone();
			while (true) {
				cx -= dx[k];
				cy -= dy[k];
				if (matrix[cy][cx] == 1)
					break;
				matrix[cy][cx] = 0;
			}

		}
	}

}
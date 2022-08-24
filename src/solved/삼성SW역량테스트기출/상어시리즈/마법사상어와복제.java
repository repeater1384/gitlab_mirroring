package solved.삼성SW역량테스트기출.상어시리즈;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 마법사상어와복제 {
	static int N = 4;
	static Queue<Integer>[][] fish;
	static Queue<Integer>[][] practice;
	static int[][] smell;
	// 상어 전용.
	static int sharkR, sharkC;
	static int[] dx = { 0, -1, 0, 1 };
	static int[] dy = { -1, 0, 1, 0 };
	static int[] bestMove;
	static int bestFishCnt;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int M = sc.nextInt();
		int S = sc.nextInt();

		fish = new LinkedList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				fish[i][j] = new LinkedList<>();

		while (M-- > 0) {
			int fr = sc.nextInt() - 1;
			int fc = sc.nextInt() - 1;
			int fd = sc.nextInt() - 1;
			fish[fr][fc].add(fd);
		}

		sharkR = sc.nextInt() - 1;
		sharkC = sc.nextInt() - 1;

		smell = new int[N][N];

		int[] dr = { 0, -1, -1, -1, 0, 1, 1, 1 };
		int[] dc = { -1, -1, 0, 1, 1, 1, 0, -1 };
		while (S-- > 0) {
			// 1. 복제. fish는 그대로 두고 practice에서 구현 후 합침
			practice = new LinkedList[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					practice[i][j] = new LinkedList<>();

			// 2. 물고기의 이동. fish를 가지고 practice에 add함.
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					for (int dir : fish[i][j]) {
						int d = dir;
						for (; d > dir - 8; d--) {
							int nr = i + dr[(d + 8) % 8];
							int nc = j + dc[(d + 8) % 8];
							if (nr < 0 || 4 <= nr || nc < 0 || 4 <= nc)
								continue;
							if (sharkR == nr && sharkC == nc)
								continue;
							if (smell[nr][nc] > 0)
								continue;
							practice[nr][nc].add((d + 8) % 8);
							break;
						}
						if (d == dir - 8)
							practice[i][j].add(dir);
					}
				}
			}

			// 3. 상어의 이동. practice를 가지고 상어의 이동을 정한다.
			bestFishCnt = 0;
			bestMove = null;
			dfs(0, new int[3]);
//			System.out.println(Arrays.toString(bestMove));
			for (int i = 0; i < 3; i++) {
				sharkR += dy[bestMove[i]];
				sharkC += dx[bestMove[i]];
				if (practice[sharkR][sharkC].size() > 0) {
					practice[sharkR][sharkC] = new LinkedList<>();
					smell[sharkR][sharkC] = 3;
				}
			}

			// 4. 냄새 제거.
//			System.out.println("smell");
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					smell[i][j] = Math.max(smell[i][j] - 1, 0);
//					System.out.print(smell[i][j]+" ");
				}
//				System.out.println();
			}

			// 5. 복제 완료. practice -> fish
			for (int i = 0; i < 4; i++) {
				for (int j = 0; j < 4; j++) {
					for (int d : practice[i][j])
						fish[i][j].add(d);
				}
			}

//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					System.out.println(i + " " + j + " " + fish[i][j]);
//				}
//			}
//			System.out.println("shark" + sharkR + " " + sharkC);
		}
		int answer = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				answer += fish[i][j].size();
			}
		}
		System.out.println(answer);
		sc.close();
	}

	static void dfs(int depth, int[] result) {
		if (depth == 3) {
			int sy = sharkR;
			int sx = sharkC;
			int[][] fishCntTemp = new int[4][4];
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 4; j++)
					fishCntTemp[i][j] = practice[i][j].size();
			int fishCnt = 0;
			for (int i = 0; i < 3; i++) {
				sy += dy[result[i]];
				sx += dx[result[i]];
				if (0 <= sx && sx < N && 0 <= sy && sy < N) {
					fishCnt += fishCntTemp[sy][sx];
					fishCntTemp[sy][sx] = 0;
				} else {
					return;
				}
			}
			if (bestMove == null)
				bestMove = result.clone();
			if (bestFishCnt < fishCnt) {
				bestMove = result.clone();
				bestFishCnt = fishCnt;
			}

			return;
		}

		for (int i = 0; i < 4; i++) {
//			if (depth >= 1 && (result[depth - 1] + 2) % 4 == i)
//				continue;
			result[depth] = i;
			dfs(depth + 1, result);
		}
	}
}
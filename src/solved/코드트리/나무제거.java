package solved.코드트리;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 나무제거 {
	static int N, M, K, C;
	static int[][] matrix, killSpray;

	public static void main(String[] args) throws FileNotFoundException {
//		System.setIn(new FileInputStream("input.txt"));
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();
		C = sc.nextInt();
		matrix = new int[N][N];
		killSpray = new int[N][N];

		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				matrix[i][j] = sc.nextInt();

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };

		int[] dx2 = { 1, 1, -1, -1 };
		int[] dy2 = { 1, -1, 1, -1 };

		int answer = 0;
		while (M-- > 0) {

			// 1. 나무의 성장

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (matrix[i][j] <= 0)
						continue;
					for (int k = 0; k < 4; k++) {
						int ni = i + dy[k];
						int nj = j + dx[k];
						if (0 <= ni && ni < N && 0 <= nj && nj < N && matrix[ni][nj] > 0)
							matrix[i][j]++;
					}
				}
			}

			// 2. 나무의 번식
			int[][] temp = new int[N][N];
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (matrix[i][j] <= 0)
						continue;
					List<int[]> pos = new ArrayList<>();
					for (int k = 0; k < 4; k++) {
						int ni = i + dy[k];
						int nj = j + dx[k];
						if (0 <= ni && ni < N && 0 <= nj && nj < N && matrix[ni][nj] == 0 && killSpray[ni][nj] == 0)
							pos.add(new int[] { ni, nj });
					}

					for (int[] cur : pos) {
						int ni = cur[0];
						int nj = cur[1];
						temp[ni][nj] += matrix[i][j] / pos.size();
					}
				}
			}
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					matrix[i][j] += temp[i][j];
				}
			}

			// 3. 살충제 뿌리기

			int ki = -1;
			int kj = -1;
			int maxKill = 0;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (matrix[i][j] <= 0)
						continue;

					int curKill = matrix[i][j];
					for (int k = 0; k < 4; k++) {
						for (int dk = 1; dk <= K; dk++) {
							int ni = i + dy2[k] * dk;
							int nj = j + dx2[k] * dk;
							if (ni < 0 || ni >= N || nj < 0 || nj >= N || matrix[ni][nj] <= 0)
								break;
							curKill += matrix[ni][nj];
						}
					}
					if (maxKill < curKill) {
						maxKill = curKill;
						ki = i;
						kj = j;
					}
				}
			}
			if (ki >= 0 && kj >= 0) {

				answer += matrix[ki][kj];
				matrix[ki][kj] = 0;
				killSpray[ki][kj] = C + 1;

				for (int k = 0; k < 4; k++) {
					for (int dk = 1; dk <= K; dk++) {
						int ni = ki + dy2[k] * dk;
						int nj = kj + dx2[k] * dk;
						if (ni < 0 || ni >= N || nj < 0 || nj >= N)
							break;

						killSpray[ni][nj] = C + 1;
						if (matrix[ni][nj] <= 0)
							break;

						answer += matrix[ni][nj];
						matrix[ni][nj] = 0;
					}
				}
			}
			// 1년이 지났습니다~
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					killSpray[i][j] = Math.max(killSpray[i][j] - 1, 0);
				}
			}

//			for (int[] row : killSpray)
//				System.out.println(Arrays.toString(row));
//			System.out.println("---------");
//			for (int[] row : matrix)
//				System.out.println(Arrays.toString(row));
//			System.out.println();
		}
		System.out.println(answer);
		sc.close();
	}
}
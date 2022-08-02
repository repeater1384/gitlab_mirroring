import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();

		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, 1, 0, -1 };
		int di = 0;
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] arr = new int[N][N];
			int num = 1;
			int cx = -1;
			int cy = 0;

			while (num <= N * N) {

				int nx = cx + dx[di];
				int ny = cy + dy[di];
				if (nx < 0 || N <= nx || ny < 0 || N <= ny || arr[ny][nx] != 0) {
					di = (di + 1) % 4;
					continue;
				}
				cx = nx;
				cy = ny;
				arr[cy][cx] = num++;

			}
			System.out.printf("#%d\n", tc);
			for (int[] row : arr) {
				for (int col : row) {
					System.out.print(col + " ");
				}
				System.out.println();
			}
		}

		sc.close();
	}

}

package solved.삼성SW역량테스트기출;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 마법사상어와비바라기 {
	static int N;
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int M = sc.nextInt();
		arr = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				arr[i][j] = sc.nextInt();

		Queue<int[]> cloud = new LinkedList<>();
		cloud.add(new int[] { N - 2, 0 });
		cloud.add(new int[] { N - 2, 1 });
		cloud.add(new int[] { N - 1, 0 });
		cloud.add(new int[] { N - 1, 1 });

		int[] dx1 = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dy1 = { 0, -1, -1, -1, 0, 1, 1, 1 };
		int[] dx2 = { -1, -1, 1, 1 };
		int[] dy2 = { -1, 1, -1, 1 };

		while (M-- > 0) {
			int dir = sc.nextInt() - 1;
			int speed = sc.nextInt();

			boolean[][] isCloud = new boolean[N][N];

			int cloudSize = cloud.size();
			while (cloudSize-- > 0) {
				int[] cur = cloud.poll();
				int y = (N + cur[0] + dy1[dir] * (speed % N)) % N;
				int x = (N + cur[1] + dx1[dir] * (speed % N)) % N;
				arr[y][x]++;
				cloud.add(new int[] { y, x });
			}
			while (!cloud.isEmpty()) {
				int[] cur = cloud.poll();
				int y = cur[0];
				int x = cur[1];
				isCloud[y][x] = true;
				for (int k = 0; k < 4; k++) {
					int nx = x + dx2[k];
					int ny = y + dy2[k];
					if (0 <= nx && nx < N && 0 <= ny && ny < N && arr[ny][nx] > 0)
						arr[y][x]++;
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (!isCloud[i][j]) {
						if (arr[i][j] >= 2) {
							arr[i][j] -= 2;
							cloud.add(new int[] { i, j });
						}
					}
				}
			}
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer += arr[i][j];
			}
		}
		
		System.out.println(answer);
		sc.close();
	}
}
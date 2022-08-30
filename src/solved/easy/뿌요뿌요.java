package solved.easy;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 뿌요뿌요 {
	static char[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		arr = new char[12][6];
		for (int i = 0; i < 12; i++)
			arr[i] = sc.next().toCharArray();

		int answer = 0;
		while (true) {
			if (!boom())
				break;
			answer++;
			fall();
		}
		System.out.println(answer);
		sc.close();
	}

	static boolean boom() {
		boolean canBomb = false;
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { 1, 0, -1, 0 };
		boolean[][] visited = new boolean[12][6];
		for (int i = 0; i < 12; i++) {
			for (int j = 0; j < 6; j++) {
				if (arr[i][j] == '.' || visited[i][j])
					continue;

				Queue<int[]> queue = new LinkedList<>();
				Queue<int[]> willBomb = new LinkedList<>();
				int cnt = 0;
				queue.add(new int[] { i, j });
				visited[i][j] = true;
				while (!queue.isEmpty()) {
					int cur[] = queue.poll();
					int ci = cur[0];
					int cj = cur[1];

					cnt++;
					willBomb.add(cur);
					for (int k = 0; k < 4; k++) {
						int ni = ci + dy[k];
						int nj = cj + dx[k];
						if (0 <= ni && ni < 12 && 0 <= nj && nj < 6 && !visited[ni][nj] && arr[i][j] == arr[ni][nj]) {
							queue.add(new int[] { ni, nj });
							visited[ni][nj] = true;
						}
					}
				}

				if (cnt >= 4) {
					canBomb = true;
					while (!willBomb.isEmpty()) {
						int cur[] = willBomb.poll();
						int ci = cur[0];
						int cj = cur[1];
						arr[ci][cj] = '.';
					}
				}

			}
		}

		return canBomb;
	}

	static void fall() {

		for (int j = 0; j < 6; j++) {
			char[] temp = new char[12];
			Arrays.fill(temp, '.');

			int idx = 11;
			for (int i = 11; i >= 0; i--) {
				if (arr[i][j] == '.')
					continue;
				temp[idx--] = arr[i][j];
			}
			for (int i = 0; i < 12; i++) {
				arr[i][j] = temp[i];
			}
		}
	}
}
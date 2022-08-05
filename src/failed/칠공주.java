package failed;
import java.util.Arrays;
import java.util.Scanner;

public class 칠공주 {
	static char[][] matrix;
	static boolean[][] visited2;
	static int answer;
	static int[] arr;
	static int r;
	static int[] result;
	static boolean[] visited;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	static boolean[][] temp;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		matrix = new char[5][];
		visited2 = new boolean[5][5];
		for (int i = 0; i < 5; i++)
			matrix[i] = sc.nextLine().toCharArray();

		answer = 0;
		int size = 25;
		arr = new int[size];
		for (int i = 0; i < 25; i++)
			arr[i] =i;
		r = 7;
		result = new int[r];
		visited = new boolean[size];
		comb(0, 0, 0);
		System.out.println(answer);
		sc.close();
	}

	static void comb(int depth, int start, int dasom) {
		if (depth == r) {
			if (dasom >= 4) {
				System.out.println(Arrays.toString(result));
				for (int r : result) {
					int x = r % 5;
					int y = r / 5;
					temp[y][x] = true;
				}
				if (isLinked(result[0] % 5, result[0] / 5) == 7)
					answer++;

				for (int r : result) {
					int x = r % 5;
					int y = r / 5;
					temp[y][x] = false;
				}
			}
			return;
		}
		for (int i = start; i < arr.length; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[depth] = arr[i];
				comb(depth + 1, start + 1, dasom + matrix[i/5][i%5] == 'S' ? 1 : 0);
				visited[i] = false;
			}
		}
	}

	static int isLinked(int x, int y) {
		int count = 1;
		visited2[y][x] = true;
		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx < 0 || 5 <= nx || ny < 0 || 5 <= ny || visited2[ny][nx] || temp[ny][nx])
				continue;
			count += isLinked(nx, ny);
		}
		return count;
	}
}
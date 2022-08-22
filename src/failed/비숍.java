package failed;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 비숍 {
	static int N;
	static int[][] arr;
	static List<int[]> availablePos;
	static int answer = 0;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		arr = new int[N][N];
		availablePos = new ArrayList<>();
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++) {
				if (sc.nextInt() == 1)
					availablePos.add(new int[] { i, j });
			}
		dfs(1, 0);
		System.out.println(answer);
		sc.close();
	}

	static void dfs(int depth, int idx) {
		int[] cur = availablePos.get(idx);
		int i = cur[0];
		int j = cur[1];

		for (int d = -N; d < N; d++) {
			if (d == 0 || i + d < 0 || i + d >= N || j + d < 0 || j + d >= N)
				continue;
			if (arr[i + d][j + d] == 1)
				return;

		}
		for (int d = -N; d < N; d++) {
			if (d == 0 || i + d < 0 || i + d >= N || j - d < 0 || j - d >= N)
				continue;
			if (arr[i + d][j - d] == 1)
				return;
		}
		arr[i][j] = 1;
		answer = Math.max(depth, answer);
		if (depth == availablePos.size())
			return;

		for (int next = idx + 1; next < availablePos.size(); next++)
			dfs(depth + 1, next);
		arr[i][j] = 0;

	}
}
package solved.swea;
import java.util.Arrays;
import java.util.Scanner;

class 최적경로 {
	static int answer;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int[][] pos = new int[N + 2][2];
			pos[0][0] = sc.nextInt();
			pos[0][1] = sc.nextInt();
			pos[N + 1][0] = sc.nextInt();
			pos[N + 1][1] = sc.nextInt();

			for (int i = 1; i <= N; i++) {
				pos[i][0] = sc.nextInt();
				pos[i][1] = sc.nextInt();
			}
			
			answer = Integer.MAX_VALUE;
			perm(N, 0, new int[N + 2], new boolean[N + 2], pos);
			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}

	static void perm(int N, int depth, int selected[], boolean[] visited, int[][] pos) {
		if (N == depth) {
			selected[0] = 0;
			selected[N + 1] = N + 1;
			int curDis = 0;
			for (int i = 1; i < N + 2; i++) {
				curDis += Math.abs(pos[selected[i]][0] - pos[selected[i - 1]][0])
						+ Math.abs(pos[selected[i]][1] - pos[selected[i - 1]][1]);
			}
			answer = Math.min(curDis, answer);
			return;
		}
		for (int i = 1; i <= N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				selected[depth + 1] = i;
				perm(N, depth + 1, selected, visited, pos);
				visited[i] = false;
			}
		}
	}
}
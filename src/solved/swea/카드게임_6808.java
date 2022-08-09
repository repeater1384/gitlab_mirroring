package solved.swea;
import java.util.*;

class 카드게임_6808 {
	static int[] arr;
	static int[] arr2;
	static int win;
	static int lose;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			arr = new int[9];
			int[] card = new int[19];

			for (int i = 1; i < 19; i++)
				card[i] = i;
			for (int i = 0; i < 9; i++) {
				arr[i] = sc.nextInt();
				card[arr[i]] = 0;
			}

			arr2 = new int[9];
			int idx = 0;
			for (int i = 1; i < 19; i++) {
				if (card[i] != 0)
					arr2[idx++] = card[i];
			}
			win = 0;
			lose = 0;
			perm(0, new boolean[9], new int[9]);
			System.out.printf("#%d %d %d\n", tc, win, lose);
		}
		sc.close();
	}

	static void perm(int depth, boolean[] visited, int[] result) {
		if (depth == 9) {
			int check = 0;
			for (int i = 0; i < 9; i++) {
				if (arr[i] > arr2[result[i]])
					check += arr[i] + arr2[result[i]];
				else
					check -= arr[i] + arr2[result[i]];
			}

			if (check > 0)
				win++;
			else
				lose++;

		}
		for (int i = 0; i < 9; i++) {
			if (!visited[i]) {
				visited[i] = true;
				result[depth] = i;
				perm(depth + 1, visited, result);
				visited[i] = false;
			}
		}
	}

}
import java.util.Scanner;

class Solution {
	public static void main(String[] a) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			int[] arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = sc.nextInt();

			int answer = -1;
			for (int i = 0; i < N - 1; i++) {
				for (int j = i + 1; j < N; j++) {
					int cur = arr[i] + arr[j];
					answer = Math.max(answer, cur > M ? -1 : cur);
				}
			}

			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}
}
package solved.swea;
import java.util.Scanner;

class 요리사 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int half = N / 2;
			int[][] arr = new int[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					arr[i][j] = sc.nextInt();

			int answer = Integer.MAX_VALUE;
			for (int flag = 0; flag < (1 << N); flag++) {
				int cnt = 0;
				for (int i = 0; i < N; i++)
					if ((flag & (1 << i)) != 0)
						cnt++;
				
				if (cnt == half) {
					int[] A = new int[half];
					int[] B = new int[half];
					int aIdx = 0, bIdx = 0;
					for (int i = 0; i < N; i++) {
						if ((flag & (1 << i)) != 0)
							A[aIdx++] = i;
						else
							B[bIdx++] = i;
					}

					int aSum = 0, bSum = 0;
					for (int i = 0; i < half; i++) {
						for (int j = 0; j < half; j++) {
							if (i != j) {
								aSum += arr[A[i]][A[j]];
								bSum += arr[B[i]][B[j]];
							}
						}
					}
					answer = Math.min(answer,Math.abs(aSum-bSum));
				}
			}

			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}
}
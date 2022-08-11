package solved.swea;
import java.util.Scanner;

public class 평탄화 {
	static int[] arr;
	static int max, min;
	static int N = 100;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int T = sc.nextInt();
			arr = new int[N];

			for (int i = 0; i < N; i++) {
				arr[i] = sc.nextInt();
			}

			while (T-- > 0) {
				update();
				if (max == min)
					break;

				int max_idx = 0;
				int min_idx = N - 1;
				for (int i = 0; i < N; i++) {
					if (arr[i] == max)
						max_idx = i;
					if (arr[i] == min)
						min_idx = i;
				}
				
				arr[max_idx]--;
				arr[min_idx]++;
			}

			update();
			System.out.printf("#%d %d\n", tc, max - min);
		}

	}

	static void update() {
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		for (int i = 0; i < N; i++) {
			max = Math.max(arr[i], max);
			min = Math.min(arr[i], min);
		}
	}
}
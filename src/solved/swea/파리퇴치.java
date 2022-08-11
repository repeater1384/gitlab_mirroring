package solved.swea;
import java.util.Scanner;

public class 파리퇴치 {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int K = sc.nextInt();
			int arr[][] = new int[N][N];

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					arr[i][j] = sc.nextInt();
				}
			}
			int temp;
			int answer = 0;
			for (int si = 0; si <= N - K; si++) {
				for (int sj = 0; sj <= N - K; sj++) {
					temp = 0;
					for (int di = 0; di < K; di++) {
						for (int dj = 0; dj < K; dj++) {
							temp += arr[si + di][sj + dj];
						}
					}
					answer = temp > answer ? temp : answer;
				}
			}
			
			System.out.printf("#%d %d\n",tc,answer);
		}
	}
}
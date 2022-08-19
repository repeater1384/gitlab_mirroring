package solved.삼성SW역량테스트기출;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 경사로 {
	static int N;
	static int L;
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		L = sc.nextInt();
		arr = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				arr[i][j] = sc.nextInt();

		List<int[]> allRoad = new ArrayList<>();
		for (int j = 0; j < N; j++) {
			int[] temp = new int[N];
			for (int i = 0; i < N; i++) {
				temp[i] = arr[i][j];
			}
			allRoad.add(temp);
			allRoad.add(arr[j]);
		}

		int answer = 0;
		for (int[] road : allRoad) {
			if (isCan(road)) {
				answer++;
			}
		}
		System.out.println(answer);
		sc.close();

	}

	static boolean isCan(int[] arr) {
		int continuous = 1;
		int cur = arr[0];
		for (int i = 1; i < N; i++) {
			if (Math.abs(arr[i] - arr[i - 1]) > 1)
				return false;
		}

		for (int i = 1; i < N; i++) {
			if (arr[i] == cur) {
				continuous++;
			} else if (arr[i] > cur) {
				if (continuous < L)
					return false;
				continuous = 1;
				cur = arr[i];
			} else {
				for (int di = 0; di < L; di++) {
					if (i + di >= N || arr[i] != arr[i + di])
						return false;
				}
				i += L - 1;
				continuous = 0;
				cur = arr[i];
			}
		}
		return true;
	}
}
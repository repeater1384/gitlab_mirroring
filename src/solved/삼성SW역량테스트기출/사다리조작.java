package solved.삼성SW역량테스트기출;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 사다리조작 {
	static int N, M, H;
	static boolean[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		H = sc.nextInt();
		// 사다리 패딩.
		arr = new boolean[H + 2][N + 2];
		while (M-- > 0) {

			int a = sc.nextInt();
			int b = sc.nextInt();
			arr[a][b] = true;
		}

		List<int[]> canBuild = new ArrayList<>();
		for (int i = 1; i <= H; i++) {
			for (int j = 1; j < N; j++) {
				if (!arr[i][j - 1] && !arr[i][j] && !arr[i][j + 1]) {
					canBuild.add(new int[] { i, j });
				}
			}
		}
		
//		for (int[] is : canBuild) {
//			System.out.println(Arrays.toString(is));
//		}
		
//		for (boolean[] temp : arr) {
//			for (boolean t : temp) {
//				System.out.print(t ? 1 + "\t" : 0 + "\t");
//			}
//			System.out.println();
//		}

		// try 0
		if (ladder(arr)) {
			System.out.println(0);
			System.exit(0);
		}

		// try 1
		for (int i = 0; i < canBuild.size(); i++) {
			int[] temp = canBuild.get(i);
			int ci = temp[0];
			int cj = temp[1];
			arr[ci][cj] = true;
			if (ladder(arr)) {
				System.out.println(1);
				System.exit(0);
			}
			arr[ci][cj] = false;
		}

		// try 2
		for (int i = 0; i < canBuild.size() - 1; i++) {
			for (int j = i + 1; j < canBuild.size(); j++) {
				int[] temp1 = canBuild.get(i);
				int ci1 = temp1[0];
				int cj1 = temp1[1];
				int[] temp2 = canBuild.get(j);
				int ci2 = temp2[0];
				int cj2 = temp2[1];

				if (ci1 == ci2 && Math.abs(cj1 - cj2) == 1)
					continue;
				arr[ci1][cj1] = arr[ci2][cj2] = true;

				if (ladder(arr)) {
					System.out.println(2);
					System.exit(0);
				}
				arr[ci1][cj1] = arr[ci2][cj2] = false;
			}
		}

		// try3
		for (int i = 0; i < canBuild.size() - 2; i++) {
			for (int j = i + 1; j < canBuild.size() - 1; j++) {
				for (int k = j + 1; k < canBuild.size(); k++) {
					int[] temp1 = canBuild.get(i);
					int ci1 = temp1[0];
					int cj1 = temp1[1];
					int[] temp2 = canBuild.get(j);
					int ci2 = temp2[0];
					int cj2 = temp2[1];
					int[] temp3 = canBuild.get(k);
					int ci3 = temp3[0];
					int cj3 = temp3[1];

					if (ci1 == ci2 && Math.abs(cj1 - cj2) == 1)
						continue;
					if (ci1 == ci3 && Math.abs(cj1 - cj3) == 1)
						continue;
					if (ci2 == ci3 && Math.abs(cj2 - cj3) == 1)
						continue;

					arr[ci1][cj1] = arr[ci2][cj2] = arr[ci3][cj3] = true;

					if (ladder(arr)) {
						System.out.println(3);
						System.exit(0);
					}
					arr[ci1][cj1] = arr[ci2][cj2] = arr[ci3][cj3] = false;
				}
			}

		}

		System.out.println(-1);
		sc.close();
	}

	static boolean ladder(boolean[][] cur) {
		for (int start = 1; start <= N; start++) {
			int j = start;
			int i = 0;
			while (true) {
				if (i == H + 1) {
					if (start != j)
						return false;
					break;
				}
				if (cur[i][j - 1]) {
					j--;
					i++;
					continue;
				}
				if (cur[i][j]) {
					j++;
					i++;
					continue;
				}
				if (!cur[i][j]) {
					i++;
					continue;
				}
			}
		}
		return true;
	}
}
package solved.삼성SW역량테스트기출;
import java.util.Scanner;

public class 색종이붙이기 {
	static int N = 10;
	static int[][] arr;
	static int answer = Integer.MAX_VALUE;
	static int[] useSize = new int[6];

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		arr = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				arr[i][j] = sc.nextInt();

		dfs(0);
		System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
		sc.close();
	}

	static void dfs(int use) {
		int x=0,y=0;
		boolean isFinsish = true;
		loop1: for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (arr[i][j] == 1) {
					y = i;
					x = j;
					isFinsish = false;
					break loop1;
				}
			}
		}

		if (isFinsish) {
			answer = Math.min(answer, use);
			return;
		}

		if (answer <= use)
			return;

		for (int size = 5; size > 0; size--) {
			if (useSize[size] == 5)
				continue;

			boolean can = true;
			loop1: for (int dy = 0; dy < size; dy++) {
				for (int dx = 0; dx < size; dx++) {
					if (y + dy >= 10 || x + dx >= 10 || arr[y + dy][x + dx] == 0) {
						can = false;
						break loop1;
					}
				}
			}

			if (can) {
				int[][] temp = copy(arr);
				useSize[size]++;
				for (int dy = 0; dy < size; dy++) 
					for (int dx = 0; dx < size; dx++) 
						arr[y+dy][x+dx] = 0;
				
				dfs(use + 1);
				arr = copy(temp);
				useSize[size]--;
			}

		}

	}

	static int[][] copy(int[][] orig) {
		int[][] result = new int[orig.length][];
		for (int i = 0; i < orig.length; i++)
			result[i] = orig[i].clone();
		return result;
	}
}


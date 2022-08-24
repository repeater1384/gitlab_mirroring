import java.util.Arrays;
import java.util.Scanner;

class Solution {
	static int N;
	static int[] arr;
	static int[] sortedArr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = sc.nextInt();
			sortedArr = arr.clone();
			Arrays.sort(sortedArr);
			int answer = 0;
//			dfs(0, new int[5]);
			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}

	static void dfs(int depth, int[] result) {
		if (depth == Math.min(5, N)) {
			int[] final_result = shuffleDeck(result);
			for(int i = 0 ;i<N;i++) {
				if(final_result[i]!=sortedArr[i])return;
			}
			
			
		}

		for (int i = 0; i < N; i++) {
			result[depth] = i;
			dfs(depth + 1, result);
		}
	}

	static int[] shuffleDeck(int[] result) {
		int[] temp = arr.clone();
		for (int shuffleNum : result) {
			temp = shuffle(shuffleNum);
		}
		return temp;

	}

	static int[] shuffle(int shuffleNum) {
		int[] shuffled = arr.clone();
		int half = N / 2;
		for (int i = 1; i <= shuffleNum; i++) {
			int cnt = half - Math.abs(half - i);
			int c = half - cnt;
			while (cnt-- > 0) {
				int temp = shuffled[c];
				shuffled[c] = shuffled[c + 1];
				shuffled[c + 1] = temp;
				c += 2;
			}
		}
		return shuffled;
	}
}
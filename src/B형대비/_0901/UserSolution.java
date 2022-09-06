package B형대비._0901;

import java.util.Arrays;

class UserSolution {
	public final static int N = 4;
	static int[][] finalResult;
	static int resultIdx;

	public void doUserImplementation(int guess[]) {
		// Implement a user's implementation function
		//
		// The array of guess[] is a return array that
		// is your guess for what digits[] would be.

		Solution.Result result = new Solution.Result();
		int[] noUse = new int[10];
		int[] mustUse = null;
		int startNum = 0123;
		for (int i = startNum; i <= 9876; i++) {
			int a = i / 1000;
			int b = i % 1000 / 100;
			int c = i % 100 / 10;
			int d = i % 10;
			if (a == b || a == c || a == d || b == c || b == d || c == d)
				continue;
			if (noUse[a] == 1 || noUse[b] == 1 || noUse[c] == 1 || noUse[d] == 1)
				continue;
			guess[0] = a;
			guess[1] = b;
			guess[2] = c;
			guess[3] = d;
			result = Solution.query(guess);
			if (result.strike == 4)
				return;
			if (result.ball + result.strike == 4) {
				mustUse = new int[] { a, b, c, d };
				break;
			}
			if (result.ball == 0 && result.strike == 0) {
				noUse[a] = 1;
				noUse[b] = 1;
				noUse[c] = 1;
				noUse[d] = 1;
			}
		}
		finalResult = new int[24][4];
		resultIdx = 0;
		dfs(new boolean[N], new int[N], 0, mustUse);
		for (int i = 0; i < 24; i++) {
			guess[0] = finalResult[i][0];
			guess[1] = finalResult[i][1];
			guess[2] = finalResult[i][2];
			guess[3] = finalResult[i][3];
			result = Solution.query(guess);
			if (result.strike == 4)
				return;
		}
	}

	static void dfs(boolean[] used, int[] result, int depth, int[] mustUsed) {
		if (depth == 4) {
			finalResult[resultIdx++] = result.clone();
			return;
		}

		for (int i = 0; i < 4; i++) {
			if (!used[i]) {
				used[i] = true;
				result[depth] = mustUsed[i];
				dfs(used, result, depth + 1, mustUsed);
				used[i] = false;
			}
		}
	}
}

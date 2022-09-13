package B형대비._0901;

class UserSolution {
	static int[][] finalResult;
	static int resultIdx;
	static boolean[] isDead;

	public void doUserImplementation(int guess[]) {
		// Implement a user's implementation function
		//
		// The array of guess[] is a return array that
		// is your guess for what digits[] would be.

		Solution.Result result = new Solution.Result();
		finalResult = new int[5040][4];
		resultIdx = 0;
		isDead = new boolean[5040];

		makeNum(new int[4], new boolean[10], 0);
		while (true) {
			int[] temp = null;
			for (int i = 0; i < 5040; i++) {
				if (isDead[i])
					continue;
				temp = finalResult[i];
				break;
			}
			for (int i = 0; i < 4; i++)
				guess[i] = temp[i];
			result = Solution.query(guess);
			if (result.strike == 4) {
				break;
			}
			for (int i = 0; i < 5040; i++) {
				if (isDead[i])
					continue;
				if (!isOk(finalResult[i], guess, result.strike, result.ball)) {
					isDead[i] = true;
				}
			}
		}
	}

	static boolean isOk(int[] myGuess, int[] target, int strike, int ball) {
		int _strike = 0;
		int _ball = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (myGuess[i] == target[j]) {
					if (i == j)
						_strike++;
					else
						_ball++;
				}
			}
		}
		return strike == _strike && ball == _ball;
	}

	static void makeNum(int[] result, boolean[] used, int depth) {
		if (depth == 4) {
			finalResult[resultIdx++] = result.clone();
			return;
		}
		for (int i = 0; i < 10; i++) {
			if (!used[i]) {
				used[i] = true;
				result[depth] = i;
				makeNum(result, used, depth + 1);
				used[i] = false;
			}
		}
	}
}

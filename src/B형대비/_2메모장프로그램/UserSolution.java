package B형대비._2메모장프로그램;

class UserSolution {

	static StringBuilder sb;
	static int[][] freqLine;
	static int size;
	static int N, H, W;
	static int cursor;

	void init(int H, int W, char mStr[]) {
		this.H = H;
		this.W = W;
		this.N = (int) Math.sqrt(H * W) + 1;
		freqLine = new int[N][26];
		sb = new StringBuilder();
		size = 0;
		cursor = 0;

		for (int i = 0; i < H * W; i++) {
			if (mStr[i] == '\0')
				break;
			sb.append(mStr[i]);
			freqLine[i / N][mStr[i] - 'a']++;
			size++;
		}

	}

	void insert(char mChar) {
		sb.insert(cursor, mChar);
		int l = cursor++ / N;
		freqLine[l][mChar - 'a']++;
		size++;

		while (l < N - 1) {
			if ((l + 1) * N < size) {
				char c = sb.charAt((l + 1) * N);
				freqLine[l][c - 'a']--;
				freqLine[l + 1][c - 'a']++;
			}
			l++;
		}
	}

	char moveCursor(int mRow, int mCol) {
		cursor = Math.min(size, (mRow - 1) * W + (mCol - 1));
		char next = cursor == size ? '$' : sb.charAt(cursor);
		return next;
	}

	int countCharacter(char mChar) {
		int cnt = 0;
		int l = cursor / N;
		for (int i = cursor; i < Math.min((l + 1) * N, size); i++) {
			if (sb.charAt(i) == mChar)
				cnt++;
		}
		l++;
		while (l < N - 1) {
			cnt += freqLine[l++][mChar - 'a'];
		}

		return cnt;
	}
}
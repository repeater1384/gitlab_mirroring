package B형대비._2메모장프로그램;

class UserSolution2 {
	static StringBuilder sb;
	static int cursor;
	static int H;
	static int W;
	static int curSize;
	static int[] freq;

	void init(int H, int W, char mStr[]) {
		cursor = 0;
		curSize = 0;
		this.H = H;
		this.W = W;
		freq = new int[26];

		sb = new StringBuilder();
		for (char c : mStr) {
			if (c == '\0')
				break;
			sb.append(c);
			freq[c - 'a']++;
			curSize++;
		}

	}

	void insert(char mChar) {
		sb.insert(cursor++, mChar);
		curSize++;
		freq[mChar - 'a']++;
	}

	char moveCursor(int mRow, int mCol) {
		cursor = Math.min(curSize, (mRow - 1) * W + (mCol - 1));
		char next = cursor == curSize ? '$' : sb.charAt(cursor);
		return next;
	}

	int countCharacter(char mChar) {
		if (cursor < curSize / 2) {
			int cnt = freq[mChar - 'a'];
			for (int i = 0; i < cursor; i++) {
				if (sb.charAt(i) == mChar) {
					cnt--;
				}
			}
			return cnt;
		}
		int cnt = 0;
		char[] temp = sb.substring(cursor).toCharArray();
		for (char c : temp) {
			if (c == mChar)
				cnt++;
		}
		return cnt;
	}
}
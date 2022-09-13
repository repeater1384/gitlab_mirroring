package B형대비._2메모장프로그램;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class UserSolution {
	static LinkedList<Character> list;
	static int[] freq;
	static List<Integer>[] idxList;
	static int cursor;
	static int H;
	static int W;

	void init(int H, int W, char mStr[]) {
		list = new LinkedList<>();
		freq = new int[26];
		idxList = new ArrayList[26];
		cursor = 0;
		this.H = H;
		this.W = W;
		for (int i = 0; i < H * W; i++) {
			char c = mStr[i];
			if (c == '\0')
				break;
			list.add(c);
			freq[c - 'a']++;
			idxList[c - 'a'].add(i);
		}

	}

	void insert(char mChar) {
		list.add(cursor++, mChar);
		freq[mChar - 'a']++;
		for (int alpha = 0; alpha < 26; alpha++) {
			int startIdx = lowerBound(alpha, cursor);
			for(int idx = startIdx; idx<idxList[alpha].size();idx++) {
				idxList[alpha].
			}
		}
	}

	char moveCursor(int mRow, int mCol) {
		cursor = Math.min(list.size(), (mRow - 1) * W + (mCol - 1));
//		System.out.println(list+" "+list.size());
//		System.out.println(cursor);
		char nextCursorChar = cursor == list.size() ? '$' : list.get(cursor);
//		System.out.println("next" + " " + nextCursorChar);
		return nextCursorChar;
	}

	int countCharacter(char mChar) {
		int cnt = freq[mChar - 'a'];
		for (int i = 0; i < cursor; i++) {
			if (list.get(i) == mChar)
				cnt--;
		}
		return cnt;
	}

	int lowerBound(int alpha, int key) {
		int start = 0;
		int end = idxList[alpha].size();
		while (start < end) {
			int mid = (start + end) / 2;
			if (idxList[alpha].get(mid) < key)
				start = mid + 1;
			else
				end = mid;
		}
		return end;
	}
}
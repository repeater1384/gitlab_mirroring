package B형대비._2메모장프로그램;

import java.util.LinkedList;
import java.util.PriorityQueue;

class UserSolution2 {
	static LinkedList<Character> list;
	static int[] freq;
//	static List<Integer>[] idxList;
	static PriorityQueue<Double>[] idxHeapList;
	static int cursor;
	static int H;
	static int W;
	
	static double dCursor = 0.999997;
	void init(int H, int W, char mStr[]) {
		list = new LinkedList<>();
		freq = new int[26];
//		idxList = new ArrayList[26];
		idxHeapList = new PriorityQueue[26];
		for (int i = 0; i < 26; i++) {
//			idxList[i] = new ArrayList<>();
			idxHeapList[i] = new PriorityQueue<>();
		}
		cursor = 0;
		this.H = H;
		this.W = W;
		for (int i = 0; i < H * W; i++) {
			char c = mStr[i];
			if (c == '\0')
				break;
			list.add(c);
			freq[c - 'a']++;
//			idxList[c - 'a'].add(i);
			idxHeapList[c-'a'].add(i + dCursor);
		}
//		for (int i = 0; i < 26; i++) {
//			System.out.println(((char) (i + 'a')) + " " + idxList[i]);
//		}
//		System.out.println("--------------");
		for (int i = 0; i < 26; i++) {
			System.out.println(((char) (i + 'a')) + " " + idxHeapList[i]);
		}
		System.out.println("---------------");
	}

	void insert(char mChar) {
		dCursor -= 0.000003;
		list.add(cursor++, mChar);
		freq[mChar - 'a']++;
		
		
		PriorityQueue<Double> temp = new PriorityQueue<>();
		PriorityQueue<Double> cur = idxHeapList[mChar-'a'];
		while(!cur.isEmpty()) {
			double val = cur.poll();
			if (val < cursor - 1)
				temp.add(val);
			else
				temp.add(val + 1);
		}
		idxHeapList[mChar-'a'] = temp;
		idxHeapList[mChar-'a'].add(cursor+dCursor-1);
//		idxList[alpha] = temp;
		
//		for (int alpha = 0; alpha < 26; alpha++) {
//			List<Integer> temp = new ArrayList<>();
//			List<Integer> cur = idxList[alpha];
//			for (int i = 0; i < cur.size(); i++) {
//				if (cur.get(i) < cursor - 1)
//					temp.add(cur.get(i));
//				else
//					temp.add(cur.get(i) + 1);
//			}
//			idxList[alpha] = temp;
//		}
//		idxList[mChar - 'a'].add(lowerBound(mChar - 'a', cursor - 1), cursor - 1);
		for (int i = 0; i < 26; i++) {
			System.out.println(((char) (i + 'a')) + " " + idxHeapList[i]);
		}
		System.out.println("---------------");
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
		PriorityQueue<Double> cur = new PriorityQueue(idxHeapList[mChar-'a']);
		int cnt = cur.size();
		while(!cur.isEmpty()) {
			if(cur.poll()>=cursor) {
				return cnt;
			}
			cnt--;
		}
		return -1;
		
//		return idxList[mChar - 'a'].size() - lowerBound(mChar - 'a', cursor);

	}

//	int lowerBound(int alpha, int key) {
//		int start = 0;
//		int end = idxList[alpha].size();
//		while (start < end) {
//			int mid = (start + end) / 2;
//			if (idxList[alpha].get(mid) < key)
//				start = mid + 1;
//			else
//				end = mid;
//		}
//		return end;
//	}
}
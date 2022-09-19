package B형대비._1오타교정시스템;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

class Search {
	int searchTime;
	char[] word;

	public Search(int searchTime, char[] word) {
		super();
		this.searchTime = searchTime;
		this.word = word;
	}

	@Override
	public String toString() {
		return "Search [searchTime=" + searchTime + ", word=" + Arrays.toString(word) + "]";
	}

}

class UserSolution {

	Search[] lastSearch;
	Map<String, HashSet<Integer>> canDB;
	Map<String, HashSet<String>> correctWordList;

	void init(int n) {
		lastSearch = new Search[n + 1];
		canDB = new HashMap<>();
		correctWordList = new HashMap<>();
	}

	int search(int mId, int searchTimestamp, char[] searchWord, char[][] correctWord) {
		if (lastSearch[mId] != null && lastSearch[mId].searchTime >= searchTimestamp - 10) {
			char[] old = lastSearch[mId].word;

			if (wrongType(old, searchWord) > 0) {
				String oldStr = char2String(old);
				String newStr = char2String(searchWord);
				String key = oldStr + newStr;

				if (canDB.containsKey(key)) {
					HashSet<Integer> cur = canDB.get(key);
					cur.add(mId);
					canDB.put(key, cur);
					if (cur.size() >= 3) {
						if (correctWordList.containsKey(oldStr)) {
							HashSet<String> cwList = correctWordList.get(oldStr);
							cwList.add(newStr);
							correctWordList.put(oldStr, cwList);
						} else {
							HashSet<String> cwList = new HashSet<>();
							cwList.add(newStr);
							correctWordList.put(oldStr, cwList);
						}
					}
				} else {
					HashSet<Integer> cur = new HashSet<>();
					cur.add(mId);
					canDB.put(key, cur);
				}
			}
			lastSearch[mId] = null;
		} else {
			lastSearch[mId] = new Search(searchTimestamp, searchWord);

		}

		int idx = 0;
		String curWord = char2String(searchWord);
		if (correctWordList.containsKey(curWord)) {
			HashSet<String> cwList = correctWordList.get(curWord);
			for (String string : cwList) {
				for (int i = 0; i < string.length(); i++) {
					correctWord[idx][i] = string.charAt(i);
				}
				idx++;
			}
		}

		return idx;
	}

	String char2String(char[] word) {
		for (int i = 0; i < word.length; i++) {
			if (word[i] == '\0') {
				return String.copyValueOf(word, 0, i);
			}
		}
		return null;
	}

	int wrongType(char[] wrongArr, char[] correctArr) {
		// -1 : 오타/정타 쌍이 아님
		// 1 : 추가
		// 2 : 치환
		// 3 : 삭제
		String wrong = char2String(wrongArr);
		String correct = char2String(correctArr);

		int wLen = wrong.length();
		int cLen = correct.length();
		if (Math.abs(wLen - cLen) >= 2)
			return -1;

		// 추가된 유형
		if (wLen > cLen) {
			boolean flag = true;
			for (int i = 0; i < cLen; i++) {
				if (wrong.charAt(i + (flag ? 0 : 1)) == correct.charAt(i))
					continue;
				if (flag) {
					flag = false;
					i--;
				} else {
					return -1;
				}
			}
			return 1;
		}
		// 수정된 유형
		else if (wLen == cLen) {
			boolean flag = true;
			for (int i = 0; i < cLen; i++) {
				if (wrong.charAt(i) == correct.charAt(i))
					continue;
				if (flag) {
					flag = false;
				} else {
					return -1;
				}
			}
			return 2;
		}
		// 삭제된 유형
		else {
			boolean flag = true;
			for (int i = 0; i < wLen; i++) {
				if (wrong.charAt(i) == correct.charAt(i + (flag ? 0 : 1)))
					continue;
				if (flag) {
					flag = false;
					i--;
				} else {
					return -1;
				}
			}
			return 3;
		}
	}
}

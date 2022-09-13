package B형대비._5이미지검색;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class Info implements Comparable<Info> {
	int id, oneCnt;

	public Info(int id, int oneCnt) {
		super();
		this.id = id;
		this.oneCnt = oneCnt;
	}

	@Override
	public int compareTo(Info o) {
		if (this.oneCnt == o.oneCnt)
			return this.id - o.id;
		return this.oneCnt - o.oneCnt;
	}

}

class UserSolution {
	final int MAX_N = 10000;
	final int MAX_M = 10;

	static char[][][] myImageList = null;
	static int[][] convertList = null;
	static int N = -1;
	static int M = -1;
	static List<Info> infoList = null;

	void init(int N, int M, char mImageList[][][]) {
		myImageList = mImageList;
		convertList = new int[N][M];
		infoList = new ArrayList<>();
		this.N = N;
		this.M = M;
		myInit();
	}

	static void myInit() {
		for (int id = 0; id < N; id++) {
			char[][] cur = myImageList[id];
			int cnt = 0;
			for (int i = 0; i < M; i++) {
				int value = 0;
				for (int j = 0; j < M; j++) {
					if (cur[i][j] == 1)
						value += 1 << (M - 1 - j);
				}
				convertList[id][i] = value;
				cnt += getOneCnt(value);
			}
			infoList.add(new Info(id, cnt));
		}
		Collections.sort(infoList);
	}

	static int getOneCnt(int i) {
		int cnt = 0;
		while (i > 0) {
			i &= (i - 1);
			cnt++;
		}
		return cnt;
	}

	int findImage(char mImage[][]) {
		int[] curConvert = new int[M];
		int curCnt = 0;
		for (int i = 0; i < M; i++) {
			int value = 0;
			for (int j = 0; j < M; j++) {
				if (mImage[i][j] == 1)
					value += 1 << (M - 1 - j);
			}
			curConvert[i] = value;
			curCnt += getOneCnt(value);
		}
		int minDiff = 3;
		int minId = 0;

		int start = Math.max(0, lowerBound(curCnt - 2));
		int end = Math.min(N - 1, lowerBound(curCnt + 3) - 1);
		for (int i = start; i <= end; i++) {
			int diff = getDiff(curConvert, convertList[infoList.get(i).id]);
			if (diff == minDiff) {
				minId = Math.min(infoList.get(i).id, minId);
			} else if (diff < minDiff) {
				minDiff = diff;
				minId = infoList.get(i).id;
			}
		}
		return minId + 1;
	}

	static int getDiff(int[] A, int[] B) {
		int diff = 0;
		for (int i = 0; i < M; i++) {
			diff += getOneCnt(A[i] ^ B[i]);
		}
		return diff;
	}

	static int lowerBound(int key) {
		int start = 0;
		int end = infoList.size();
		while (start < end) {
			int mid = (start + end) / 2;
			if (infoList.get(mid).oneCnt < key)
				start = mid + 1;
			else
				end = mid;
		}
		return end;
	}

}

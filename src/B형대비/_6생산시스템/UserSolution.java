package B형대비._6생산시스템;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

class Request {
	int pId, mLine, eId, mTime;
	int intoTime;
	int endTime;

	public Request(int pId, int mLine, int eId, int mTime) {
		super();
		this.pId = pId;
		this.mLine = mLine;
		this.eId = eId;
		this.mTime = mTime;
	}

	public void reCalc() {
		this.endTime = this.intoTime + this.mTime;
	}

	@Override
	public String toString() {
		return "Request [pId=" + pId + ", mLine=" + mLine + ", eId=" + eId + ", mTime=" + mTime + ", intoTime="
				+ intoTime + ", endTime=" + endTime + "]";
	}

}

class UserSolution {
	static Queue<Request>[] waitLine;
	static PriorityQueue<Request> mainPQ;
	static Map<Integer, Integer> productStatus;

	static int[] lineUsed;
	static boolean[] equipUsed;

	static int L;
	static int M;

	static int lastTime;

	void init(int L, int M) {
		waitLine = new LinkedList[L];
		for (int i = 0; i < L; i++)
			waitLine[i] = new LinkedList<>();
		mainPQ = new PriorityQueue<Request>((a, b) -> a.endTime - b.endTime);
		productStatus = new HashMap<>();

		lineUsed = new int[L];
		Arrays.fill(lineUsed, -1);
		equipUsed = new boolean[M];

		this.L = L;
		this.M = M;

		return;
	}

	int request(int tStamp, int pId, int mLine, int eId, int mTime) {
		addPQ(tStamp - 1);
		pollPQ(tStamp - 1);
		waitLine[mLine].add(new Request(pId, mLine, eId, mTime));
		productStatus.put(pId, 1);

		pollPQ(tStamp);
		addPQ(tStamp);
		pollPQ(tStamp);

		return lineUsed[mLine];
	}

	int status(int tStamp, int pId) {
		pollPQ(tStamp);
		if (!productStatus.containsKey(pId))
			return 0;
		return productStatus.get(pId);
	}

	static void pollPQ(int tStamp) {
		// tStamp까지 뺄수있는거 다 뺴기.
		while (!mainPQ.isEmpty() && mainPQ.peek().endTime <= tStamp) {

			Request cur = mainPQ.poll();
			lineUsed[cur.mLine] = -1;
			equipUsed[cur.eId] = false;
			productStatus.put(cur.pId, 3);

			if (mainPQ.isEmpty() || mainPQ.peek().endTime != cur.endTime)
				addPQ(cur.endTime);
		}
	}

	static void addPQ(int curTime) {
		// 이 시간에 넣을수 있는거 다 넣기.
		List<Integer> canAdd = new ArrayList<>();
		for (int i = 0; i < L; i++) {
			if (lineUsed[i] != -1)
				continue;
			if (waitLine[i].size() == 0)
				continue;
			if (equipUsed[waitLine[i].peek().eId])
				continue;
			canAdd.add(i);
			equipUsed[waitLine[i].peek().eId] = true;
		}

		for (int i : canAdd) {
			Request cur = waitLine[i].poll();
			lineUsed[i] = cur.pId;
			equipUsed[cur.eId] = true;
			productStatus.put(cur.pId, 2);
			cur.intoTime = curTime;
			cur.reCalc();
			mainPQ.add(cur);
		}
		for (int i = 0; i < L; i++) {
			if (lineUsed[i] != -1)
				continue;
			if (waitLine[i].size() == 0)
				continue;
			if (equipUsed[waitLine[i].peek().eId])
				continue;
			// 넣을수 있음.

			Request cur = waitLine[i].poll();
			lineUsed[i] = cur.pId;
			equipUsed[cur.eId] = true;
			productStatus.put(cur.pId, 2);
			cur.intoTime = curTime;
			cur.reCalc();
			mainPQ.add(cur);
		}
	}
}
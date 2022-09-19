package B형대비._4세젤예;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Cell {
	List<Info> hasCell;
	int value;
	boolean canCalc;

	public Cell(char[] equation, int value, boolean canCalc) {
		super();
		this.hasCell = new ArrayList<>();
		this.value = value;
		this.canCalc = canCalc;
	}

}

class Info {
	boolean isCell;
	boolean isPositive;
	int val;

	public Info(boolean isCell, boolean isPositive, int val) {
		super();
		this.isCell = isCell;
		this.isPositive = isPositive;
		this.val = val;
	}
}

public class UserSolution {
	static final int MOD = 100000007;
	static final int ROW = 99;
	static final int COL = 26;

	static Cell Table[];
	static List<Integer>[] referenceIdx;

	public void initTable() {
		Table = new Cell[ROW * COL + 1];
		referenceIdx = new ArrayList[ROW * COL + 1];
		for (int i = 0; i < ROW * COL + 1; i++) {
			Table[i] = new Cell(null, 0, true);
			referenceIdx[i] = new ArrayList<>();
		}
	}

	public boolean updateCell(int row, int col, char equation[], int value[][]) {
		int idx = convertPos1(row, col);
		for (int i = 0; i < ROW * COL; i++) {
			for (int j = referenceIdx[i].size() - 1; j >= 0; j--) {
				if (referenceIdx[i].get(j) == idx) {
					referenceIdx[i].remove(j);
				}
			}
		}

		List<Info> hasCell = new ArrayList<>();
		boolean isPostive = true;
		for (int i = 0; i < equation.length; i++) {
			if (equation[i] == '=')
				continue;
			if (equation[i] == '+') {
				isPostive = true;
				continue;
			}
			if (equation[i] == '-') {
				isPostive = false;
				continue;
			}

			if ('A' <= equation[i] && equation[i] <= 'Z') {
				if (i + 2 < equation.length && ('0' <= equation[i + 2] && equation[i + 2] <= '9')) {
					hasCell.add(new Info(true, isPostive,
							convertPos3(new char[] { equation[i], equation[i + 1], equation[i + 2] })));
					referenceIdx[convertPos3(new char[] { equation[i], equation[i + 1], equation[i + 2] })].add(idx);
					i += 2;
				} else {
					hasCell.add(new Info(true, isPostive, convertPos2(new char[] { equation[i], equation[i + 1] })));
					referenceIdx[convertPos2(new char[] { equation[i], equation[i + 1] })].add(idx);
					i++;
				}
			} else {
				int j = i;
				while (j < equation.length && '0' <= equation[j] && equation[j] <= '9') {
					j++;
				}
				char[] temp = new char[j - i];
				for (int k = i; k < j; k++) {
					temp[k - i] = equation[k];
				}
				hasCell.add(new Info(false, isPostive, Integer.parseInt(new String(temp))));
				i = j - 1;
			}
		}

		boolean canCalc = true;
		for (Info info : hasCell) {
			if (info.isCell == false)
				continue;
			if (Table[info.val].canCalc == false || info.val == idx) {
				canCalc = false;
				break;
			}
		}

		Queue<Integer> temp2 = new LinkedList<>();
		boolean[] visit = new boolean[ROW * COL];
		for (int n : referenceIdx[idx]) {
			temp2.add(n);
			visit[n] = true;
		}
		while (!temp2.isEmpty()) {
			int cur = temp2.poll();
			if (cur == idx) {
				canCalc = false;
				break;
			}
			for (int next : referenceIdx[cur]) {
				if (!visit[next]) {
					temp2.add(next);
					visit[next] = true;
				}
			}
		}

		// 내꺼 업데이트.
		Table[idx].canCalc = canCalc;
		Table[idx].hasCell = hasCell;

		// 내꺼 계산 못할때 나를 참조하는 애들 false로 바꾸기.
		if (!canCalc) {
			Queue<Integer> temp = new LinkedList<>();
			for (int next : referenceIdx[idx]) {
				if (Table[next].canCalc) {
					Table[next].canCalc = false;
					temp.add(next);
				}
			}
			while (!temp.isEmpty()) {
				int cur = temp.poll();
				for (int next : referenceIdx[cur]) {
					if (Table[next].canCalc) {
						Table[next].canCalc = false;
						temp.add(next);
					}
				}
			}
		}
		// 내꺼 계산가능할떄.
		if (canCalc) {
			int resultValue = calc(hasCell);
			Table[idx].value = resultValue;

			int[] visitedCntIdx = new int[ROW * COL];
			Queue<Integer> queue = new LinkedList<>();
			for (int i : referenceIdx[idx]) {
				queue.add(i);
				visitedCntIdx[i]++;
			}
			while (!queue.isEmpty()) {
				int curIdx = queue.poll();
				Cell curCell = Table[curIdx];
				boolean curCanCalc = true;
				for (Info info : curCell.hasCell) {
					if (info == null)
						continue;
					if (info.isCell == false) {
						continue;
					}
					if (Table[info.val].canCalc == false) {
						if (visitedCntIdx[curIdx] < 2) {
							visitedCntIdx[curIdx]++;
							queue.add(curIdx);
						}
						curCanCalc = false;
						break;
					}
				}

				Table[curIdx].canCalc = curCanCalc;
				if (curCanCalc) {
					int curResultValue = calc(Table[curIdx].hasCell);
					Table[curIdx].value = curResultValue;
					for (int i : referenceIdx[curIdx]) {
						if (visitedCntIdx[i] < 2) {
							queue.add(i);
							visitedCntIdx[i]++;
						}
					}
				}
			}
		}

		boolean allCanCalc = true;
		int cantCalcCnt = 0;
		for (int i = 0; i < ROW * COL; i++) {
			Cell curCell = Table[i];
			if (curCell.canCalc == false) {
				allCanCalc = false;
				cantCalcCnt++;
			}
		}

		for (int i = 0; i < ROW * COL; i++) {
			Cell curCell = Table[i];
			int r = i / COL;
			int c = i % COL;
			if (curCell.canCalc == false) {
				value[r][c] = cantCalcCnt;
			} else {
				value[r][c] = curCell.value;
			}
		}

		return allCanCalc;
	}

	static int convertPos1(int row, int col) {
		return row * COL + col;
	}

	static int convertPos2(char[] pos) {
		int row = pos[1] - '0' - 1;
		int col = pos[0] - 'A';
		return convertPos1(row, col);
	}

	static int convertPos3(char[] pos) {
		int row = Integer.parseInt(new String(new char[] { pos[1], pos[2] })) - 1;
		int col = pos[0] - 'A';
		return convertPos1(row, col);
	}

	public int calc(List<Info> hasCell) {
		int result = 0;
		for (Info e : hasCell) {
			if (e.isCell) {
				if (e.isPositive)
					result += Table[e.val].value;
				else {
					result -= Table[e.val].value;
				}
			} else {
				if (e.isPositive)
					result += e.val;
				else {
					result -= e.val;
				}
			}
			result %= MOD;
		}
		return result;
	}

}

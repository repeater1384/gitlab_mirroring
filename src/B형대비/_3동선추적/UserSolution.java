package B형대비._3동선추적;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class UserSolution {
	static final int SIZE = 10000;
	static final int placeSIZE = 50000 + 1;
	static final int userSIZE = 1000 + 1;
	static List<int[]>[] rowList;
	static List<int[]>[] colList;
	static List<int[]>[] dia1List;
	static List<int[]>[] dia2List;

	static List<Integer>[] userPassedPlace;
	static int[][] pIDList;
	static boolean[] isHide;

	void init() {
		rowList = new ArrayList[SIZE];
		colList = new ArrayList[SIZE];
		dia1List = new ArrayList[SIZE * 2 + 1];
		dia2List = new ArrayList[SIZE * 2 + 1];

		for (int i = 0; i < SIZE; i++) {
			rowList[i] = new ArrayList<>();
			colList[i] = new ArrayList<>();
			dia1List[i] = new ArrayList<>();
			dia2List[i] = new ArrayList<>();
		}
		for (int i = SIZE; i < SIZE * 2 + 1; i++) {
			dia1List[i] = new ArrayList<>();
			dia2List[i] = new ArrayList<>();
		}

		pIDList = new int[placeSIZE][2];
		isHide = new boolean[placeSIZE];

		userPassedPlace = new ArrayList[userSIZE];
		for (int i = 0; i < userSIZE; i++) {
			userPassedPlace[i] = new ArrayList<>();
		}
	}

	void addPlace(int pID, int r, int c) {
		pIDList[pID] = new int[] { r, c };
		rowList[r].add(new int[] { pID, r, c });
		colList[c].add(new int[] { pID, r, c });
		dia1List[r + c].add(new int[] { pID, r, c });
		dia2List[SIZE - 1 - r + c].add(new int[] { pID, r, c });
		Collections.sort(rowList[r], (a, b) -> (a[2] - b[2]));
		Collections.sort(colList[c], (a, b) -> (a[1] - b[1]));
		Collections.sort(dia1List[r + c], (a, b) -> (b[1] - a[1]));
		Collections.sort(dia2List[SIZE - 1 - r + c], (a, b) -> (a[1] - b[1]));

//		System.out.println("-----------------------------");
//		System.out.println("row");
//		rowList[r].forEach((temp) -> {
//			System.out.println(Arrays.toString(temp));
//		});
//		
//		System.out.println("col");
//		colList[c].forEach((temp) -> {
//			System.out.println(Arrays.toString(temp));
//		});
//		System.out.println("dia1");
//		dia1List[r + c].forEach((temp) -> {
//			System.out.println(Arrays.toString(temp));
//		});
//		System.out.println("dia2");
//		dia2List[SIZE - 1 - r + c].forEach((temp) -> {
//			System.out.println(Arrays.toString(temp));
//		});

	}

	void removePlace(int pID) {
		int[] pos = pIDList[pID];
		int r = pos[0];
		int c = pos[1];
		pIDList[pID] = null;
		for (int i = 0; i < rowList[r].size(); i++) {
			if (rowList[r].get(i)[0] == pID) {
				rowList[r].remove(i);
			}
		}

		for (int i = 0; i < colList[c].size(); i++) {
			if (colList[c].get(i)[0] == pID) {
				colList[c].remove(i);
			}
		}

		for (int i = 0; i < dia1List[r + c].size(); i++) {
			if (dia1List[r + c].get(i)[0] == pID) {
				dia1List[r + c].remove(i);
			}
		}

		for (int i = 0; i < dia2List[SIZE - 1 - r + c].size(); i++) {
			if (dia2List[SIZE - 1 - r + c].get(i)[0] == pID) {
				dia2List[SIZE - 1 - r + c].remove(i);
			}
		}

	}

	void contactTracing(int uID, int visitNum, int moveInfo[], int visitList[]) {
		List<Integer> passed = new ArrayList<>();
		int cur_pID = moveInfo[0];
		passed.add(cur_pID);
		visitList[0] = moveInfo[0];
		for (int visit = 1; visit < visitNum; visit++) {
			int dir = moveInfo[visit];
			int r = pIDList[cur_pID][0];
			int c = pIDList[cur_pID][1];

			if (dir == 0 || dir == 4) {
				List<int[]> temp = colList[c];
				int flag = 0;
				for (; flag < temp.size(); flag++) {
					if (temp.get(flag)[0] == cur_pID) {
						break;
					}
				}

				if (dir == 0) {
					for (int next = flag - 1; next >= 0; next--) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				} else {
					for (int next = flag + 1; next < temp.size(); next++) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				}

			} else if (dir == 1 || dir == 5) {

				List<int[]> temp = dia1List[r + c];
				int flag = 0;
				for (; flag < temp.size(); flag++) {
					if (temp.get(flag)[0] == cur_pID) {
						break;
					}
				}

				if (dir == 5) {
					for (int next = flag - 1; next >= 0; next--) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				} else {
					for (int next = flag + 1; next < temp.size(); next++) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				}

			} else if (dir == 2 || dir == 6) {

				List<int[]> temp = rowList[r];
				int flag = 0;
				for (; flag < temp.size(); flag++) {
					if (temp.get(flag)[0] == cur_pID) {
						break;
					}
				}

				if (dir == 6) {
					for (int next = flag - 1; next >= 0; next--) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				} else {
					for (int next = flag + 1; next < temp.size(); next++) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				}

			} else if (dir == 3 || dir == 7) {

				List<int[]> temp = dia2List[SIZE - 1 - r + c];
				int flag = 0;
				for (; flag < temp.size(); flag++) {
					if (temp.get(flag)[0] == cur_pID) {
						break;
					}
				}

				if (dir == 7) {
					for (int next = flag - 1; next >= 0; next--) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				} else {
					for (int next = flag + 1; next < temp.size(); next++) {
						if (isHide[temp.get(next)[0]])
							continue;
						passed.add(temp.get(next)[0]);
						cur_pID = temp.get(next)[0];
						break;
					}
				}

			}
			visitList[visit] = cur_pID;
		}
		for (int pID : passed) {
			isHide[pID] = true;
		}
		userPassedPlace[uID] = passed;
//		System.out.println(visitNum + " " + passed + " "+Arrays.toString(visitList));

	}

	void disinfectPlaces(int uID) {
		for (int pID : userPassedPlace[uID]) {
			isHide[pID] = false;
		}
	}

}
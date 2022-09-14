//package B형대비._4세젤예;
//
//import java.util.ArrayList;
//import java.util.List;
//
//class Cell {
//	List<Info> hasCell;
//	char[] equation;
//	int value;
//	boolean canCalc;
//
//	public Cell(char[] equation) {
//		super();
//		this.equation = equation;
//	}
//
//	public Cell(char[] equation, int value, boolean canCalc) {
//		super();
//		this.hasCell = new ArrayList<>();
//		this.equation = equation;
//		this.value = value;
//		this.canCalc = canCalc;
//	}
//
//}
//
//class Info {
//	boolean isCell;
//	boolean isPositive;
//	int val;
//
//	public Info(boolean isCell, boolean isPositive, int val) {
//		super();
//		this.isCell = isCell;
//		this.isPositive = isPositive;
//		this.val = val;
//	}
//
//	@Override
//	public String toString() {
//		return "Info [isCell=" + isCell + ", isPositive=" + isPositive + ", val=" + val + "]";
//	}
//
//}
//
//public class UserSolution2 {
//	static final int MOD = 100000007;
//	static final int ROW = 99;
//	static final int COL = 26;
//
//	static Cell Table[];
////	static boolean canCalc[];
////	static int calcedValue[];
//
//	public void initTable() {
//		Table = new Cell[ROW * COL];
////		canCalc = new boolean[ROW * COL];
////		Arrays.fill(canCalc, true);
////		calcedValue = new int[ROW * COL];
//		for (int i = 0; i < ROW * COL; i++) {
//			Table[i] = new Cell(null, 0, true);
//		}
//	}
//
//	public boolean updateCell(int row, int col, char equation[], int value[][]) {
//		int idx = convertPos1(row, col);
//		List<Info> hasCell = new ArrayList<>();
//		boolean isPostive = true;
//		for (int i = 0; i < equation.length; i++) {
//			if (equation[i] == '=')
//				continue;
//			if (equation[i] == '+') {
//				isPostive = true;
//				continue;
//			}
//			if (equation[i] == '-') {
//				isPostive = false;
//				continue;
//			}
//
//			if ('A' <= equation[i] && equation[i] <= 'Z') {
//				if (i + 2 < equation.length && ('0' <= equation[i + 2] && equation[i + 2] <= '9')) {
//					hasCell.add(new Info(true, isPostive,
//							convertPos3(new char[] { equation[i], equation[i + 1], equation[i + 2] })));
//					i += 2;
//				} else {
//					hasCell.add(new Info(true, isPostive, convertPos2(new char[] { equation[i], equation[i + 1] })));
//					i++;
//				}
//			} else {
//				int j = i;
//				while (j < equation.length && '0' <= equation[j] && equation[j] <= '9') {
//					j++;
//				}
//				char[] temp = new char[j - i];
//				for (int k = i; k < j; k++) {
//					temp[k - i] = equation[k];
//				}
//				hasCell.add(new Info(false, isPostive, Integer.parseInt(new String(temp))));
//				i = j - 1;
//			}
//		}
//
//		boolean canCalc = true;
//		for (Info info : hasCell) {
//			if (info.isCell == false)
//				continue;
//			if (Table[info.val].canCalc == false || info.val == idx) {
//				canCalc = false;
//				break;
//			}
//		}
//
//		// 내꺼 업데이트.
//		Table[idx].canCalc = canCalc;
//		Table[idx].hasCell = hasCell;
//		if (canCalc) {
//			int resultValue = calc(hasCell);
//			Table[idx].value = resultValue;
//		}
//
//		// 나를 참조하고 있던거 업데이트.
//
//		List<Integer> willUpdateIdx = new ArrayList<>();
//		for (int i = 0; i < ROW * COL; i++) {
//			Cell curCell = Table[i];
//			if (curCell.hasCell == null)
//				continue;
//			for (Info info : curCell.hasCell) {
////				System.out.println(info.val );
//				if (info.isCell == true && info.val == idx) {
//					willUpdateIdx.add(i);
//					break;
//				}
//			}
//		}
//		
//		boolean[] visitedIdx = new boolean[ROW*COL];
//		while(true) {
//			List<Integer> nextIdx = new ArrayList<>();
//			for (int i : willUpdateIdx) {
//				Cell curCell = Table[i];
//				boolean curCanCalc = true;
//				for (Info info : curCell.hasCell) {
//					if (info == null)
//						continue;
//					if (info.isCell == false) {
//						continue;
//					}
//					if (Table[info.val].canCalc == false) {
//						curCanCalc = false;
//						if(willUpdateIdx.contains(info.val) && !visitedIdx[info.val]) {
//							nextIdx.add(info.val);
//							visitedIdx[info.val] = true;
//						}
//						break;
//					}
//				}
//				
//				Table[i].canCalc = curCanCalc;
//				if (curCanCalc) {
//					int resultValue = calc(Table[i].hasCell);
//					Table[i].value = resultValue;
//					
//					for (int j = 0; j < ROW * COL; j++) {
//						Cell curCurCell = Table[j];
//						if (curCurCell.hasCell == null)
//							continue;
//						for (Info info : curCurCell.hasCell) {
////							System.out.println(info.val );
//							if (info.isCell == true && info.val == i) {
//								nextIdx.add(j);
//								break;
//							}
//						}
//					}
//					
//				}
//			}
//			if (row == 72 && col == 14) {
//				System.out.println(willUpdateIdx);
//				System.out.println(nextIdx);
//			}
//			if(nextIdx.size()==0)break;
//			willUpdateIdx = nextIdx;
//		}
//
//		boolean allCanCalc = true;
//		int cantCalcCnt = 0;
//		for (int i = 0; i < ROW * COL; i++) {
//			Cell curCell = Table[i];
//			if (curCell.canCalc == false) {
//				allCanCalc = false;
//				cantCalcCnt++;
//			}
//		}
//		for (int i = 0; i < ROW * COL; i++) {
//			Cell curCell = Table[i];
//			int r = i / COL;
//			int c = i % COL;
//			if (curCell.canCalc == false) {
//				value[r][c] = cantCalcCnt++;
//			} else {
//				value[r][c] = curCell.value;
//			}
//		}
//
//		if (row == 72 && col == 14) {
//
//			System.out.println(
//					"---------------" + row + " " + col + " " + " " + idx + " " + allCanCalc + "-------------------");
//			System.out.println(willUpdateIdx);
//			for (Info info : hasCell) {
//				System.out.println(info);
//			}
//
//		}
//		return allCanCalc;
//	}
//
//	static int convertPos1(int row, int col) {
//		return row * COL + col;
//	}
//
//	static int convertPos2(char[] pos) {
//		int row = pos[1] - '0' - 1;
//		int col = pos[0] - 'A';
//		return convertPos1(row, col);
//	}
//
//	static int convertPos3(char[] pos) {
//		int row = Integer.parseInt(new String(new char[] { pos[1], pos[2] })) - 1;
//		int col = pos[0] - 'A';
//		return convertPos1(row, col);
//	}
//
////	static int calc(char[] equation) {
////		int result = 0;
////		
////	}
//
//	public int calc(List<Info> hasCell) {
//		int result = 0;
//		for (Info e : hasCell) {
//			if (e.isCell) {
//				if (e.isPositive)
//					result += Table[e.val].value;
//				else {
//					result -= Table[e.val].value;
//				}
//			} else {
//				if (e.isPositive)
//					result += e.val;
//				else {
//					result -= e.val;
//				}
//			}
//			result %= 100000007;
//		}
//		return result;
//	}
//	
//}

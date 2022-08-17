package solved.swea;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class BC {
	int y, x, circle, power;

	public BC(int y, int x, int circle, int power) {
		super();
		this.y = y;
		this.x = x;
		this.circle = circle;
		this.power = power;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + circle;
		result = prime * result + power;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BC other = (BC) obj;
		if (circle != other.circle)
			return false;
		if (power != other.power)
			return false;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

}

class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		int[] dx = { 0, -1, 0, 1, 0 };
		int[] dy = { 0, 0, 1, 0, -1 };
		for (int tc = 1; tc <= T; tc++) {
			int moveSize = sc.nextInt() + 1;
			int bcSize = sc.nextInt();
			int[] aMove = new int[moveSize];
			int[] bMove = new int[moveSize];
			for (int i = 0; i < moveSize - 1; i++)
				aMove[i] = sc.nextInt();
			for (int i = 0; i < moveSize - 1; i++)
				bMove[i] = sc.nextInt();

			BC[] bcList = new BC[bcSize];
			for (int i = 0; i < bcSize; i++)
				bcList[i] = new BC(sc.nextInt() - 1, sc.nextInt() - 1, sc.nextInt(), sc.nextInt());
			Arrays.sort(bcList, (a, b) -> (b.power - a.power));

			int aX = 0, aY = 0;
			int bX = 9, bY = 9;
			int answer = 0;
			for (int move = 0; move < moveSize; move++) {
				List<BC> aBCList = getIncludeBC(aX, aY, bcList);
				List<BC> bBCList = getIncludeBC(bX, bY, bcList);
				answer += canCharge(aBCList, bBCList);
				aX += dx[aMove[move]];
				aY += dy[aMove[move]];
				bX += dx[bMove[move]];
				bY += dy[bMove[move]];
			}

			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}

	static List<BC> getIncludeBC(int x, int y, BC[] bcList) {
		List<BC> result = new ArrayList<>();
		for (BC bc : bcList) {
			int bx = bc.x;
			int by = bc.y;
			int dis = Math.abs(x - bx) + Math.abs(y - by);
			if (dis <= bc.circle)
				result.add(bc);
		}
		return result;
	}

	static int canCharge(List<BC> A, List<BC> B) {

		if (A.size() == 0 && B.size() > 0)
			return B.get(0).power;
		if (A.size() > 0 && B.size() == 0)
			return A.get(0).power;

		int result = 0;
		for (int i = 0; i < A.size(); i++) {
			for (int j = 0; j < B.size(); j++) {
				int curSum = 0;
				if (A.get(i).equals(B.get(j)))
					curSum += A.get(i).power;
				else
					curSum += A.get(i).power + B.get(j).power;
				result = Math.max(result, curSum);
			}
		}
		return result;
	}
}
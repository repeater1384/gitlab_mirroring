import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class Solution {
	static int N;
	static int[] arr;
	static int[][] matrix;
	static List<Integer> order1;
	static List<Integer> order2;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			arr = new int[N];
			matrix = new int[3][2];
			for (int i = 0; i < 3; i++) {
				matrix[i][0] = sc.nextInt() - 1;
				matrix[i][1] = sc.nextInt();
			}
			order1 = new ArrayList<>();
			order2 = new ArrayList<>();
			order1.add(0);
			order2.add(0);

			for (int i = 1; i <= N; i++) {
				order1.add(i);
				order1.add(-1 * i);
				order2.add(-1 * i);
				order2.add(i);
			}

			List<int[]> seat = new ArrayList<>();
			seat.add(new int[] { 1, 2, 3 });
			seat.add(new int[] { 1, 3, 2 });
			seat.add(new int[] { 2, 1, 3 });
			seat.add(new int[] { 2, 3, 1 });
			seat.add(new int[] { 3, 1, 2 });
			seat.add(new int[] { 3, 2, 1 });
			List<int[]> order = new ArrayList<>();
			order.add(new int[] { 0, 0, 0 });
			order.add(new int[] { 0, 0, 1 });
			order.add(new int[] { 0, 1, 0 });
			order.add(new int[] { 0, 1, 1 });
			order.add(new int[] { 1, 0, 0 });
			order.add(new int[] { 1, 0, 1 });
			order.add(new int[] { 1, 1, 0 });
			order.add(new int[] { 1, 1, 1 });

			int answer = Integer.MAX_VALUE;
			for (int i = 0; i < 6; i++) {
				for (int j = 0; j < 8; j++) {
					answer = Math.min(answer, calc(seat.get(i), order.get(j)));
				}
			}
			System.out.printf("#%d %d\n", tc, answer);
		}
	}

	static int calc(int[] seat, int[] order) {
		int[] temp = new int[N];
		int result = 0;
		for (int idx = 0; idx < 3; idx++) {
			int s = seat[idx] - 1;
			int o = order[idx];
			List<Integer> curOrder = o == 1 ? order1 : order2;
			int first = matrix[s][0];
			int people = matrix[s][1];
			for (int d : curOrder) {
				if (0 <= first + d && first + d < N && temp[first + d] == 0) {
					temp[first + d] = 1;
					result += Math.abs(d) + 1;
					people--;
					if (people == 0)
						break;
				}
			}
		}

		return result;
	}
}

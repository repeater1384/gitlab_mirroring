import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Solution {
	static int answer;
	static int[] parents;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			parents = new int[N + 1];
			for (int i = 1; i <= N; i++)
				parents[i] = i;
			int M = sc.nextInt();
			while (M-- > 0) {
				int a = sc.nextInt();
				int b = sc.nextInt();
				if (!isSameParents(a, b))
					union(a, b);
			}
			Set<Integer> count = new HashSet<>();
			for (int i = 1; i <= N; i++)
				count.add(find_parents(i));

			System.out.printf("#%d %d\n", tc, count.size());
		}
		sc.close();
	}

	static int find_parents(int x) {
		if (x == parents[x])
			return x;
		return parents[x] = find_parents(parents[x]);
	}

	static boolean isSameParents(int x, int y) {
		return find_parents(x) == find_parents(y);
	}

	static void union(int x, int y) {
		x = parents[x];
		y = parents[y];
		if (x > y)
			parents[x] = y;
		else
			parents[y] = x;
	}
}
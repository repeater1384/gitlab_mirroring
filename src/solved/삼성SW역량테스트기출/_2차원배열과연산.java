package solved.삼성SW역량테스트기출;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class _2차원배열과연산 {
	static int R, C, K;
	static int[][] arr;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		arr = new int[3][3];
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				arr[i][j] = sc.nextInt();

		int answer = 0;
		while (true) {
			if(answer==101) {
				System.out.println(-1);
				break;
			}
			try {
				if (arr[R - 1][C - 1] == K) {
					System.out.println(answer);
					break;
				}
			} catch (Exception e) {
			}
			answer++;

			int r = arr.length;
			int c = arr[0].length;
			
			if (r >= c) {
				List<Integer>[] temp = new ArrayList[r];
				for (int i = 0; i < r; i++) {
					temp[i] = new ArrayList<>();
				}
				int maxC = 0;
				for (int i = 0; i < r; i++) {
					Map<Integer, Integer> freq = new HashMap<>();
					for (int j = 0; j < c; j++) {
						if (arr[i][j] == 0)
							continue;
						Integer v = freq.get(arr[i][j]);
						if (v == null) {
							freq.put(arr[i][j], 1);
						} else {
							freq.put(arr[i][j], v + 1);
						}
					}
					List<Integer> keySet = new ArrayList<>(freq.keySet());
					Collections.sort(keySet,
							(o1, o2) -> (freq.get(o1) == freq.get(o2) ? o1 - o2 : freq.get(o1) - freq.get(o2)));
					for (int a : keySet) {
						temp[i].add(a);
						temp[i].add(freq.get(a));
					}
					maxC = Math.min(Math.max(maxC, temp[i].size()), 100);
				}

				int[][] newArr = new int[r][maxC];
				for (int i = 0; i < r; i++) {
					for (int j = 0; j < temp[i].size(); j++) {
						newArr[i][j] = temp[i].get(j);
					}
				}
				arr = newArr;
				c = maxC;
			} else {
				List<Integer>[] temp = new ArrayList[c];
				for (int j = 0; j < c; j++) {
					temp[j] = new ArrayList<>();
				}
				int maxR = 0;
				for (int j = 0; j < c; j++) {
					Map<Integer, Integer> freq = new HashMap<>();
					for (int i = 0; i < r; i++) {
						if (arr[i][j] == 0)
							continue;
						Integer v = freq.get(arr[i][j]);
						if (v == null) {
							freq.put(arr[i][j], 1);
						} else {
							freq.put(arr[i][j], v + 1);
						}
					}
					List<Integer> keySet = new ArrayList<>(freq.keySet());
					Collections.sort(keySet,
							(o1, o2) -> (freq.get(o1) == freq.get(o2) ? o1 - o2 : freq.get(o1) - freq.get(o2)));

					for (int a : keySet) {
						temp[j].add(a);
						temp[j].add(freq.get(a));
					}
					maxR = Math.min(Math.max(maxR, temp[j].size()), 100);
				}

				int[][] newArr = new int[maxR][c];
				for (int j = 0; j < c; j++) {
					for (int i = 0; i < temp[j].size(); i++) {
						newArr[i][j] = temp[j].get(i);
					}
				}
				arr = newArr;
				r = maxR;
			}

		}
		sc.close();
	}
}
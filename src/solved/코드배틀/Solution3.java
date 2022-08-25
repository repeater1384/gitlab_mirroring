package solved.코드배틀;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

class Solution3 {
	static int N;
	static int[] arr;
	static int[] sortedArr;
	static Integer[] reverseSortedArr;
	static boolean find;

	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			arr = new int[N];
			for (int i = 0; i < N; i++)
				arr[i] = sc.nextInt();
			sortedArr = arr.clone();
			reverseSortedArr = new Integer[N];
			for (int i = 0; i < N; i++)
				reverseSortedArr[i] = new Integer(arr[i]);
			Arrays.sort(sortedArr);
			Arrays.sort(reverseSortedArr, Collections.reverseOrder());

			int answer = -1;
			find = false;

			for (int i = 0; i <= 5; i++) {
				dfs(0, new int[i], i);
				if (find) {
					answer = i;
					break;
				}
			}
			System.out.printf("#%d %d\n", tc, answer);
		}
		sc.close();
	}

	static void dfs(int depth, int[] result, int round) {
		if (depth == round) {
			int[] final_result = shuffleDeck(result);
//			if(round<=3)
//			System.out.println(Arrays.toString(result) + "asdf" + Arrays.toString(final_result));
			boolean check = true;
			for (int i = 0; i < N; i++) {
				if (final_result[i] != sortedArr[i]) {
					check = false;
					break;
				}
			}
			if (check) {
				find = true;
				return;
			}

			check = true;
			for (int i = 0; i < N; i++) {
				if (final_result[i] != reverseSortedArr[i]) {
					check = false;
					break;
				}
			}
			if (check) {
				find = true;
			}
			return;
		}

		for (int i = 0; i < N; i++) {
			result[depth] = i;
			dfs(depth + 1, result, round);
		}
	}

	static int[] shuffleDeck(int[] result) {
		int[] temp = arr.clone();
		for (int shuffleNum : result) {
			temp = shuffle(shuffleNum, temp);
		}
		return temp;

	}

	static int[] shuffle(int shuffleNum, int[] temp2) {
		int[] shuffled = temp2.clone();
		int half = N / 2;
		for (int i = 1; i <= shuffleNum; i++) {
			int cnt = half - Math.abs(half - i);
			int c = half - cnt;
			while (cnt-- > 0) {
				int temp = shuffled[c];
				shuffled[c] = shuffled[c + 1];
				shuffled[c + 1] = temp;
				c += 2;
			}
		}
		return shuffled;
	}
}
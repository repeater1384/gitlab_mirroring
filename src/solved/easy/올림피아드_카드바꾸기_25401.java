package solved.easy;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class 올림피아드_카드바꾸기_25401 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] arr = new int[N];
		for (int i = 0; i < N; i++)
			arr[i] = sc.nextInt();
		int answer = N;

		// #1. 모든 수를 같게 만들기. 최빈값 찾기.
		Map<Integer, Integer> freq = new HashMap<>();
		int maxFreq = 0;
		for (int i = 0; i < N; i++) {
			Integer cur = freq.get(arr[i]);
			if (cur == null)
				freq.put(arr[i], 1);
			else {
				maxFreq = Math.max(maxFreq, cur + 1);
				freq.put(arr[i], cur + 1);
			}
		}
		answer = N - maxFreq;

		// #2. 공차>0인 등차수열 만들기.
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (arr[j] - arr[i] > 0 && (arr[j] - arr[i]) % (j - i) == 0) {
					// 공차
					int d = (arr[j] - arr[i]) / (j - i);
					// 초항
					int a = arr[i] - (i * d);

					int needChange = 0;
					for (int n = 0; n < N; n++) {
						if (a + n * d == arr[n])
							continue;
						needChange++;
					}
					answer = Math.min(answer, needChange);
				}
			}
		}

		// #3. 공차<0인 등차수열 만들기. (arr 뒤집고 #2 그대로 진행)
		for (int i = 0; i < N / 2; i++) {
			int temp = arr[i];
			arr[i] = arr[N - 1 - i];
			arr[N - 1 - i] = temp;
		}
		for (int i = 0; i < N - 1; i++) {
			for (int j = i + 1; j < N; j++) {
				if (arr[j] - arr[i] > 0 && (arr[j] - arr[i]) % (j - i) == 0) {
					// 공차
					int d = (arr[j] - arr[i]) / (j - i);
					// 초항
					int a = arr[i] - (i * d);

					int needChange = 0;
					for (int n = 0; n < N; n++) {
						if (a + n * d == arr[n])
							continue;
						needChange++;
					}
					answer = Math.min(answer, needChange);
				}
			}
		}

		System.out.println(answer);
		sc.close();
	}
}
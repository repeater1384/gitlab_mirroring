package solved.스터디3주차;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 스터디3주차_소수의연속합 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		boolean[] isPrime = new boolean[4000001];
		Arrays.fill(isPrime, true);
		List<Integer> primes = new ArrayList<>();
		isPrime[2] = true;
		for (int i = 2; i < N + 1; i++) {
			if (isPrime[i]) {
				primes.add(i);
				for (int j = i * 2; j < N + 1; j += i)
					isPrime[j] = false;
			}
		}

		int sum = 0;
		int end = 0;
		int answer = 0;

		for (int start = 0; start < primes.size(); start++) {
			while (sum < N && end < primes.size())
				sum += primes.get(end++);
			if (sum == N)
				answer++;
			sum -= primes.get(start);
		}
		
		System.out.println(answer);
		sc.close();
	}
}
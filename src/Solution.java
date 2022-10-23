import java.util.Scanner;

public class Solution {

	static long MOD = 1234567891;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			long N = sc.nextLong();
			long K = sc.nextLong();

			long A = factorial(N);
			long B = factorial(K) * factorial(N - K) % MOD;

			long nCk = A * fast_pow(B, MOD - 2) % MOD;
			
			System.out.printf("#%d %d\n", tc, nCk);

		}
		sc.close();
	}

	public static long factorial(long N) {
		long fac = 1;

		while (N > 1) {
			fac = (fac * N) % MOD;
			N--;
		}
		return fac;
	}

	public static long fast_pow(long base, long exp) {
		long result = 1;

		while (exp > 0) {
			if (exp % 2 == 1) {
				result *= base;
				result %= MOD;
			}
			base = (base * base) % MOD;
			exp /= 2;
		}

		return result;
	}

}
package solved.easy;
public class 카드2 {
	@SuppressWarnings("resource")
	public static void main(String[] args) {
		int N = new java.util.Scanner(System.in).nextInt();
		int K = (int) Math.pow(2, (int) (Math.log(N > 1 ? N - 1 : 0.5) / Math.log(2) + 1));
		System.out.print(2 * N - K);
	}
}
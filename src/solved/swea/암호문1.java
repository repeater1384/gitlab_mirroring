package solved.swea;
import java.util.*;

class 암호문1 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int N = Integer.parseInt(sc.nextLine());
			String answer = sc.nextLine();
			int K = Integer.parseInt(sc.nextLine());
			
			StringTokenizer st = new StringTokenizer(sc.nextLine());
			for (int b = 0; b < K; b++) {
				st.nextToken();
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				String snew = "";
				while (Y-- > 0) {
					snew += st.nextToken() + " ";
				}
				answer = answer.substring(0, 7 * X) + snew + answer.substring(7 * X, answer.length());
			}

			System.out.print("#" + tc + " ");
			System.out.println(answer.substring(0,70));
		}
		sc.close();
	}
}
package solved.삼성SW역량테스트기출;
import java.util.LinkedList;
import java.util.Scanner;

public class 컨베이어벨트위의로봇 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int K = sc.nextInt();
		LinkedList<Integer> arr = new LinkedList<>();
		boolean[] isRobot = new boolean[N + 1];
		for (int i = 0; i < 2 * N; i++)
			arr.add(sc.nextInt());

		int zeroCnt = 0;
		int answer = 0;
		while (zeroCnt < K) {
			answer++;
			arr.addFirst(arr.pollLast());
			for (int i = N - 1; i >= 1; i--)
				isRobot[i] = isRobot[i - 1];
			isRobot[0] = false;
			isRobot[N - 1] = false;
			for (int i = N - 2; i >= 0; i--) {
				if (isRobot[i] && !isRobot[i + 1] && arr.get(i + 1) >= 1) {
					isRobot[i] = false;
					isRobot[i + 1] = true;
					arr.set(i + 1, arr.get(i + 1) - 1);
					if (arr.get(i + 1) == 0)
						zeroCnt++;
				}
			}

			if (arr.get(0) >= 1) {
				isRobot[0] = true;
				arr.set(0, arr.get(0) - 1);
				if (arr.get(0) == 0)
					zeroCnt++;
			}
		}
		System.out.println(answer);
		sc.close();
	}
}
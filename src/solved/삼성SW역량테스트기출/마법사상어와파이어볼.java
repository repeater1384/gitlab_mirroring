package solved.삼성SW역량테스트기출;
import java.util.LinkedList;
import java.util.Scanner;

class FireBall {
	int m, s, d;

	public FireBall(int m, int s, int d) {
		super();
		this.m = m;
		this.s = s;
		this.d = d;
	}

	@Override
	public String toString() {
		return "FireBall [m=" + m + ", s=" + s + ", d=" + d + "]";
	}

}

public class 마법사상어와파이어볼 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();
		LinkedList<FireBall>[][] matrix = new LinkedList[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				matrix[i][j] = new LinkedList<FireBall>();

		while (M-- > 0) {
			int r = sc.nextInt() - 1;
			int c = sc.nextInt() - 1;
			int m = sc.nextInt();
			int s = sc.nextInt();
			int d = sc.nextInt();
			matrix[r][c].add(new FireBall(m, s, d));
		}

		int[] dr = { -1, -1, 0, 1, 1, 1, 0, -1 };
		int[] dc = { 0, 1, 1, 1, 0, -1, -1, -1 };

		while (K-- > 0) {
			LinkedList<FireBall>[][] nextMatrix = new LinkedList[N][N];
			for (int i = 0; i < N; i++)
				for (int j = 0; j < N; j++)
					nextMatrix[i][j] = new LinkedList<FireBall>();

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					LinkedList<FireBall> curList = matrix[i][j];
//					System.out.println(i + " " + j + " " + curList);

					while (!curList.isEmpty()) {
						FireBall cur = curList.poll();
						int dir = cur.d;
						int speed = cur.s;
						int nr = (N + i + dr[dir] * (speed%N)) % N;
						int nc = (N + j + dc[dir] * (speed%N)) % N;
						if (0 <= nr && nr < N && 0 <= nc && nc < N) {
							nextMatrix[nr][nc].add(cur);
						}
					}
				}
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					LinkedList<FireBall> curList = nextMatrix[i][j];
//					System.out.println(i + " " + j + " " + curList);
					if (curList.size() <= 1)
						continue;

					int mSum = 0;
					int sSum = 0;
					int size = curList.size();
					boolean[] check = new boolean[size];
					int idx = 0;

					while (!curList.isEmpty()) {
						FireBall cur = curList.poll();
						mSum += cur.m;
						sSum += cur.s;
						check[idx++] = cur.d % 2 == 0 ? true : false;
					}

					int individualM = mSum / 5;
					int individualS = sSum / size;
					if (individualM == 0)
						continue;

					boolean checkTemp = check[0];
					boolean dirIsSame = true;
					for (int k = 1; k < size; k++) {
						if (checkTemp != check[k])
							dirIsSame = false;
					}

					if (dirIsSame) {
						for (int dir = 0; dir < 8; dir += 2)
							nextMatrix[i][j].add(new FireBall(individualM, individualS, dir));
					} else {
						for (int dir = 1; dir < 8; dir += 2)
							nextMatrix[i][j].add(new FireBall(individualM, individualS, dir));
					}
				}
			}
			matrix = nextMatrix;
//			System.out.println();
		}

		int answer = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				LinkedList<FireBall> curList = matrix[i][j];
				while (!curList.isEmpty()) {
					FireBall cur = curList.poll();
					answer += cur.m;
				}
			}
		}
		System.out.println(answer);
		sc.close();
	}
}
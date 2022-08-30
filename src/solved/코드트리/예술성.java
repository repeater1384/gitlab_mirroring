package solved.코드트리;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;
import java.util.Set;

public class 예술성 {
	static int N;
	static int[][] mainMatrix;
	static int[][] groupMatrix;
	static List<int[]> groupInfo;
	static int answer = 0;

	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		mainMatrix = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				mainMatrix[i][j] = sc.nextInt();

		grouping();
		getScore();
		
		rotate();
		grouping();
		getScore();
		
		rotate();
		grouping();
		getScore();
		
		rotate();
		grouping();
		getScore();
		
		System.out.println(answer);
		sc.close();
	}

	static void grouping() {
		groupMatrix = new int[N][N];
		groupInfo = new ArrayList<>();

		boolean[][] visited = new boolean[N][N];
		int groupIdx = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					int groupNum = mainMatrix[i][j];
					int groupCnt = 0;
					Queue<int[]> queue = new LinkedList<>();
					queue.add(new int[] { i, j });
					visited[i][j] = true;

					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						int cy = cur[0];
						int cx = cur[1];
						groupMatrix[cy][cx] = groupIdx;
						groupCnt++;
						for (int k = 0; k < 4; k++) {
							int ny = cy + dy[k];
							int nx = cx + dx[k];
							if (0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx]
									&& mainMatrix[ny][nx] == groupNum) {
								queue.add(new int[] { ny, nx });
								visited[ny][nx] = true;
							}
						}
					}
					groupInfo.add(new int[] { groupNum, groupCnt });
					groupIdx++;
				}
			}
		}
	}

	static void getScore() {
		int groupSize = groupInfo.size();
		int[][] groupNeighbor = new int[groupSize][groupSize];

		boolean[][] visited = new boolean[N][N];
		int score = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					int groupNum = groupMatrix[i][j];

					Queue<int[]> queue = new LinkedList<>();
					queue.add(new int[] { i, j });
					visited[i][j] = true;

					while (!queue.isEmpty()) {
						int[] cur = queue.poll();
						int cy = cur[0];
						int cx = cur[1];
						for (int k = 0; k < 4; k++) {
							int ny = cy + dy[k];
							int nx = cx + dx[k];
							if (0 <= ny && ny < N && 0 <= nx && nx < N) {
								if (groupMatrix[ny][nx] == groupNum && !visited[ny][nx]) {
									queue.add(new int[] { ny, nx });
									visited[ny][nx] = true;
								}
								if (groupMatrix[ny][nx] != groupNum) {
									groupNeighbor[groupNum][groupMatrix[ny][nx]]++;
								}
							}
						}
					}
				}
			}
		}

		for (int i = 0; i < groupSize - 1; i++) {
			for (int j = i + 1; j < groupSize; j++) {
				if (groupNeighbor[i][j] == 0)
					continue;
				answer += (groupInfo.get(i)[1] + groupInfo.get(j)[1]) * groupInfo.get(i)[0] * groupInfo.get(j)[0]
						* groupNeighbor[i][j];
			}
		}

	}

	static void rotate() {
		int[][] rotateMatrix = new int[N][N];
		int half = N / 2;
		int[] temp1 = new int[N];
		for (int i = 0; i < N; i++)
			temp1[i] = mainMatrix[half][i];

		for (int i = 0; i < N; i++)
			rotateMatrix[half][i] = mainMatrix[i][half];
		for (int i = 0; i < N; i++)
			rotateMatrix[i][half] = temp1[N - 1 - i];

		int smallSize = half;
		int[] startR = { 0, half + 1 };
		int[] startC = { 0, half + 1 };

		for (int sr : startR) {
			for (int sc : startC) {
				for (int i = 0; i < smallSize; i++) {
					for (int j = 0; j < smallSize; j++) {
						rotateMatrix[sr + i][sc + j] = mainMatrix[sr + smallSize - 1 - j][sc + i];
					}
				}
			}
		}
		mainMatrix = rotateMatrix;
	}

	static void printArr(int[][] arr) {
		for (int[] is : arr) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println();
	}

	static void printArr(List<int[]> arr) {
		for (int[] is : arr) {
			System.out.print(Arrays.toString(is) + " ");
		}
		System.out.println();
	}

}
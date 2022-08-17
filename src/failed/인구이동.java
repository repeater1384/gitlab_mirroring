//package failed;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Queue;
//import java.util.Scanner;
//
//public class 인구이동 {
//	static int N, L, R;
//	static int[] dx = { 0, 1, 0, -1 };
//	static int[] dy = { -1, 0, 1, 0 };
//	static int[][] arr;
//	static boolean[][] visited;
//	public static void main(String[] args) {
//		Scanner sc = new Scanner(System.in);
//		N = sc.nextInt();
//		L = sc.nextInt();
//		R = sc.nextInt();
//		arr = new int[N][N];
//		for (int i = 0; i < N; i++)
//			for (int j = 0; j < N; j++)
//				arr[i][j] = sc.nextInt();
//
//		int answer = 0;
//		while (true) {
//			visited = new boolean[N][N];
//				break;
//			answer++;
//			for (int[] is : arr) {
//				System.out.println(Arrays.toString(is));
//			}
//			for (int[] is : union) 
//				System.out.println(Arrays.toString(is));
//			System.out.println();
//			for (int i = 0; i < N; i++) {
//				for (int j = 0; j < N; j++) {
//					if (!visited[i][j])
//						bfs(j, i);
//				}
//			}
////			arr = union;
//		}
//
//		System.out.println(answer);
//		sc.close();
//	}
//
//
//	static void bfs(int sx, int sy) {
//		//union을 가지고 arr를 바꿈
//		Queue<int[]> queue = new LinkedList<>();
//
//		queue.add(new int[] { sx, sy });
//		visited[sy][sx] = true;
//		List<int[]> allPos = new ArrayList<>();
//
//		int unionSum = 0;
//		int unionSize = 0;
//		while (!queue.isEmpty()) {
//			int[] cur = queue.poll();
//			int cx = cur[0];
//			int cy = cur[1];
//			unionSum += arr[cy][cx];
//			unionSize++;
//			allPos.add(new int[] { cx, cy });
//
//			for (int i = 0; i < 4; i++) {
//				int nx = cx + dx[i];
//				int ny = cy + dy[i];
//				if (nx < 0 || N <= nx || ny < 0 || N <= ny)
//					continue;
//				if (union[ny][nx] == unionNum) {
//					queue.add(new int[] { nx, ny });
//					union[ny][nx] = -1;
//				}
//			}
//		}
//
//		int unionAvg = unionSum / unionSize;
//		for (int[] cur : visited) {
//			int x = cur[0];
//			int y = cur[1];
//			arr[y][x] = unionAvg;
//		}
//
//	}
//
//	static boolean check(int a, int b) {
//		int diff = Math.abs(a - b);
//		return L <= diff && diff <= R;
//	}
//}
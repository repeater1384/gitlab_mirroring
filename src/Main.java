import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

class BlockGroup implements Comparable<BlockGroup> {
	int cnt, rainbowCnt, mainR, mainC;

	public BlockGroup(int cnt, int rainbowCnt, int mainR, int mainC) {
		super();
		this.cnt = cnt;
		this.rainbowCnt = rainbowCnt;
		this.mainR = mainR;
		this.mainC = mainC;
	}

	@Override
	public int compareTo(BlockGroup o) {
		if (o.cnt == this.cnt && this.rainbowCnt == o.rainbowCnt && o.mainR == this.mainR)
			return o.mainC - this.mainC;
		if (this.cnt == o.cnt && this.rainbowCnt == o.rainbowCnt)
			return o.mainR - this.mainR;
		if (o.cnt == this.cnt)
			return o.rainbowCnt - this.rainbowCnt;
		return o.cnt - this.cnt;
	}

	@Override
	public String toString() {
		return "BlockGroup [cnt=" + cnt + ", rainbowCnt=" + rainbowCnt + ", mainR=" + mainR + ", mainC=" + mainC + "]";
	}

}

public class Main {
	static int N;
	static int M;
	static int[][] arr;
	static boolean[][] visited;
	static int[] dx = { 1, 0, -1, 0 };
	static int[] dy = { 0, 1, 0, -1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				arr[i][j] = sc.nextInt();

		int answer = 0;
		while (true) {
			visited = new boolean[N][N];
			List<BlockGroup> blockGroupList = new ArrayList<>();
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (arr[i][j] > 0 && !visited[i][j]) {
						BlockGroup b = bfs(j, i, false);
						if (b.cnt >= 2)
							blockGroupList.add(b);
					}
				}
			}

			if (blockGroupList.size() == 0)
				break;

			Collections.sort(blockGroupList);
			
			
//			for (BlockGroup blockGroup : blockGroupList) 
//				System.out.println(blockGroup);
			
			BlockGroup deleteBlockGroup = blockGroupList.get(0);
			int dy = deleteBlockGroup.mainR;
			int dx = deleteBlockGroup.mainC;
			bfs(dx, dy, true);
			answer += deleteBlockGroup.cnt * deleteBlockGroup.cnt;
//			printArr();
			gravity();
//			printArr();
			rotate();
//			printArr();
			gravity();
//			printArr();
//			System.out.println("-----------------------------------"+answer);
		}

//		for (BlockGroup blockGroup : blockGroupList) {
//			System.out.println(blockGroup);
//		}
		System.out.println(answer);

		sc.close();
	}

	static BlockGroup bfs(int sx, int sy, boolean option) {

		Queue<int[]> queue = new LinkedList<>();
		Queue<int[]> zeroQueue = new LinkedList<>();
		if (option)
			visited = new boolean[N][N];
		visited[sy][sx] = true;
		queue.add(new int[] { sx, sy });
		
		int mainNum = arr[sy][sx];
		int groupSize = 0;
		int rainbowCnt = 0;
		while (!queue.isEmpty()) {
			groupSize++;
			int[] cur = queue.poll();
			int cx = cur[0];
			int cy = cur[1];
			if (arr[cy][cx] == 0) {
				rainbowCnt++;
				zeroQueue.add(cur);
			}
			if (option)
				arr[cy][cx] = -2;
			for (int i = 0; i < 4; i++) {
				int nx = cx + dx[i];
				int ny = cy + dy[i];
				if (0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx]) {
					if (arr[ny][nx] == mainNum || arr[ny][nx] == 0) {
						queue.add(new int[] { nx, ny });
						visited[ny][nx] = true;
					}
				}
			}
		}
		while(!zeroQueue.isEmpty()) {
			int[] cur = zeroQueue.poll();
			int cx = cur[0];
			int cy = cur[1];
			visited[cy][cx]=false;
		}
		return new BlockGroup(groupSize, rainbowCnt, sy, sx);
	}

	static void gravity() {
		int[][] gravityArr = new int[N][N];
		for (int i = 0; i < N; i++)
			Arrays.fill(gravityArr[i], -2);
		for (int j = 0; j < N; j++) {

			int temp = N - 1;
			Queue<Integer> queue = new LinkedList<>();
			for (int i = N - 1; i >= 0; i--) {
				if (arr[i][j] >= 0) {
					queue.add(arr[i][j]);
					continue;
				}

				if (arr[i][j] == -1) {
					while (!queue.isEmpty()) {
						int cur = queue.poll();
						gravityArr[temp--][j] = cur;
					}
					gravityArr[i][j] = -1;
					temp = i - 1;
				}
			}
			while (!queue.isEmpty()) {
				int cur = queue.poll();
				gravityArr[temp--][j] = cur;
			}
		}
		arr = gravityArr;
	}

	static void rotate() {

		int[][] temp = new int[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				temp[i][j] = arr[j][N - 1 - i];
		arr = temp;
	}
	
	static void printArr() {
		for(int i = 0 ;i<N;i++) {
			for(int j = 0 ;j<N;j++) {
				if(arr[i][j]>=0)System.out.print(arr[i][j]);
				if(arr[i][j]==-1)System.out.print('B');
				if(arr[i][j]==-2)System.out.print('X');
			}
			System.out.println();
		}
		System.out.println("***");
	}
}
package solved.series.배열돌리기;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 배열돌리기6 {
	static int[][] arr;
	static int[][] arr2;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int pow = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		N = 1 << pow;
		arr = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++)
				arr[i][j] = Integer.parseInt(st.nextToken());
		}
		while (R-- > 0) {
			st = new StringTokenizer(br.readLine());
			int cmd = Integer.parseInt(st.nextToken());
			int l = Integer.parseInt(st.nextToken());
			switch (cmd) {
			case 1:
				cmd1(pow - 1, l, 0, 0);
				break;
			case 2:
				cmd2(pow - 1, l, 0, 0);
				break;
			case 3:
				cmd3(pow - 1, l, 0, 0);
				break;
			case 4:
				cmd4(pow - 1, l, 0, 0);
				break;
			case 5:
				arr2 = new int[N][N];
				cmd5(pow - 1, l, 0, 0);
				arr = arr2;
				break;
			case 6:
				arr2 = new int[N][N];
				cmd6(pow - 1, l, 0, 0);
				arr = arr2;
				break;
			case 7:
				arr2 = new int[N][N];
				cmd7(pow - 1, l, 0, 0);
				arr = arr2;
				break;
			case 8:
				arr2 = new int[N][N];
				cmd8(pow - 1, l, 0, 0);
				arr = arr2;
				break;
			}
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++)
			sb.append(Arrays.toString(arr[i]).replace("[", "").replace("]", "").replace(",", "")).append('\n');
		System.out.print(sb.toString());
		br.close();
	}

	static void cmd1(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size *= 2;
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[dy][dx] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr[sy + dy][sx + dx] = temp[size - dy - 1][dx];

			return;
		}
		cmd1(level - 1, cur, sx, sy);
		cmd1(level - 1, cur, sx + size, sy);
		cmd1(level - 1, cur, sx, sy + size);
		cmd1(level - 1, cur, sx + size, sy + size);
	}

	static void cmd2(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size *= 2;
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[dy][dx] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr[sy + dy][sx + dx] = temp[dy][size - dx - 1];

			return;
		}
		cmd2(level - 1, cur, sx, sy);
		cmd2(level - 1, cur, sx + size, sy);
		cmd2(level - 1, cur, sx, sy + size);
		cmd2(level - 1, cur, sx + size, sy + size);
	}

	static void cmd3(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size *= 2;
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[dx][size - 1 - dy] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr[sy + dy][sx + dx] = temp[dy][dx];

			return;
		}
		cmd3(level - 1, cur, sx, sy);
		cmd3(level - 1, cur, sx + size, sy);
		cmd3(level - 1, cur, sx, sy + size);
		cmd3(level - 1, cur, sx + size, sy + size);
	}

	static void cmd4(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size *= 2;
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[size - 1 - dx][dy] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr[sy + dy][sx + dx] = temp[dy][dx];

			return;
		}
		cmd4(level - 1, cur, sx, sy);
		cmd4(level - 1, cur, sx + size, sy);
		cmd4(level - 1, cur, sx, sy + size);
		cmd4(level - 1, cur, sx + size, sy + size);
	}
	
	static void cmd5(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size = 1 << (level+1);
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[dy][dx] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr2[N-sy-size + dy][sx + dx] = temp[dy][dx];

			return;
		}
		cmd5(level - 1, cur, sx, sy);
		cmd5(level - 1, cur, sx + size, sy);
		cmd5(level - 1, cur, sx, sy + size);
		cmd5(level - 1, cur, sx + size, sy + size);
	}
	
	static void cmd6(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size = 1 << (level+1);
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[dy][dx] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr2[sy+dy][N-sx-size+dx] = temp[dy][dx];

			return;
		}
		cmd6(level - 1, cur, sx, sy);
		cmd6(level - 1, cur, sx + size, sy);
		cmd6(level - 1, cur, sx, sy + size);
		cmd6(level - 1, cur, sx + size, sy + size);
	}
	
	static void cmd7(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size = 1 << (level+1);
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[dy][dx] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr2[sx+dy][N-size-sy+dx] = temp[dy][dx];

			return;
		}
		cmd7(level - 1, cur, sx, sy);
		cmd7(level - 1, cur, sx + size, sy);
		cmd7(level - 1, cur, sx, sy + size);
		cmd7(level - 1, cur, sx + size, sy + size);
	}
	
	static void cmd8(int level, int cur, int sx, int sy) {
		int size = 1 << level;
		if (level == cur - 1) {
			size = 1 << (level+1);
			int[][] temp = new int[size][size];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					temp[dy][dx] = arr[sy + dy][sx + dx];
			for (int dy = 0; dy < size; dy++)
				for (int dx = 0; dx < size; dx++)
					arr2[N-size-sx+dy][sy+dx] = temp[dy][dx];

			return;
		}
		cmd8(level - 1, cur, sx, sy);
		cmd8(level - 1, cur, sx + size, sy);
		cmd8(level - 1, cur, sx, sy + size);
		cmd8(level - 1, cur, sx + size, sy + size);
	}

}

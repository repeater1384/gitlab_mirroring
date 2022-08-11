package solved.swea;
import java.io.*;
import java.util.*;

public class ladder1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for (int tc = 1; tc <= 10; tc++) {
			br.readLine();
			int cx = 0, cy = 0;
			int[][] map = new int[100][100];
			for (int i = 0; i < 100; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 100; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					if (map[i][j] == 2) {
						cx = j;
						cy = i;
					}
				}
			}

			while (cy > 0) {
				boolean move = false;
				while (cx - 1 >= 0 && map[cy][cx - 1] == 1) {
					cx--;
					move = true;
				}
				if (!move) {
					while (cx + 1 < 100 && map[cy][cx + 1] == 1)
						cx++;
				}
				cy--;
			}

			System.out.printf("#%d %d\n", tc, cx);

		}
	}
}
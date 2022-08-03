package solved;
import java.util.Scanner;

public class 탱크 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// 상우하좌
		int[] dx = { 0, 1, 0, -1 };
		int[] dy = { -1, 0, 1, 0 };

		int T = sc.nextInt();
		for (int tc = 1; tc <= T; tc++) {
			int N = sc.nextInt();
			int M = sc.nextInt();
			char[][] matrix = new char[N][M];
			for (int i = 0; i < N; i++) {
				matrix[i] = sc.next().toCharArray();
			}

			
			int cx = 0;
			int cy = 0;
			int di = 0;

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (matrix[i][j] == '^') {
						cx = j;
						cy = i;
						di = 0;
					} else if (matrix[i][j] == 'v') {
						cx = j;
						cy = i;
						di = 2;
					} else if (matrix[i][j] == '<') {
						cx = j;
						cy = i;
						di = 3;
					} else if (matrix[i][j] == '>') {
						cx = j;
						cy = i;
						di = 1;
					}
				}
			}
			
			
			int n_cmd = sc.nextInt();
			char[] cmd = sc.next().toCharArray();
			for (int i = 0; i < n_cmd; i++) {
				char cur = cmd[i];
				if(cur == 'U') {
					di = 0;
					matrix[cy][cx] = '^';
					if(cy-1>=0 && matrix[cy-1][cx]=='.') {
						matrix[cy--][cx] = '.';
						matrix[cy][cx] = '^';
					}
				}
				
				if(cur == 'R') {
					di = 1;
					matrix[cy][cx] = '>';
					if(cx+1<M && matrix[cy][cx+1]=='.') {
						matrix[cy][cx++] = '.';
						matrix[cy][cx] = '>';
					}
				}
				
				if(cur == 'D') {
					di = 2;
					matrix[cy][cx] = 'v';
					if(cy+1<N && matrix[cy+1][cx]=='.') {
						matrix[cy++][cx] = '.';
						matrix[cy][cx] = 'v';
					}
				}
				
				if(cur == 'L') {
					di = 3;
					matrix[cy][cx] = '<';
					if(cx-1>=0 && matrix[cy][cx-1]=='.') {
						matrix[cy][cx--] = '.';
						matrix[cy][cx] = '<';
					}
				}
				
				if(cur=='S') {
					int bx = cx;
					int by = cy;
					
					while(true) {
						bx += dx[di];
						by += dy[di];
						if(bx<0 || bx>=M || by<0 || by>=N) {
							break;
						}
						if(matrix[by][bx]=='*') {
							matrix[by][bx] = '.';
							break;
						}if(matrix[by][bx]=='#') {
							break;
						}
					}
				}

			}
			
			System.out.print("#"+tc+" ");
			for(char[] row :matrix) {
				for(char e:row) {
					System.out.print(e);
				}
				System.out.println();
			}
		}
		sc.close();
	}
}
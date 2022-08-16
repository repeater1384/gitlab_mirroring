package solved.swea;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Node {
	boolean isOp, hasLeft, hasRight;

	public Node(boolean isOp, boolean hasLeft, boolean hasRight) {
		super();
		this.isOp = isOp;
		this.hasLeft = hasLeft;
		this.hasRight = hasRight;
	}

}

public class 올바른수식트리_계산기능추가 {
	static Node[] nodes;
	static char[] values;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 10;
		for (int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			nodes = new Node[N + 1];
			values = new char[N + 1];
			for (int i = 1; i <= N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				st.nextToken();
				char value = st.nextToken().charAt(0);
				values[i] = value;
				boolean isOp = value == '+' || value == '-' || value == '*' || value == '/';
				boolean hasLeft = false;
				boolean hasRight = false;
				if (st.hasMoreTokens())
					hasLeft = true;
				if (st.hasMoreTokens())
					hasRight = true;
				nodes[i] = new Node(isOp, hasLeft, hasRight);
			}

//			for (int i = 1; i <= N; i++) {
//				Node cur = nodes[i];
//				if (!cur.isOp) {
//					if (cur.hasLeft || cur.hasRight) {
//						answer = 0;
//						break;
//					}
//				}else {
//					if(!cur.hasLeft || !cur.hasRight) {
//						answer = 0;
//						break;
//					}
//				}
//			}
			try {
				System.out.printf("#%d %d\n", tc, dfs(1));
			} catch (Exception e) {
			}
		}
	}

	static int dfs(int idx) throws Exception {
		Node cur = nodes[idx];
		if (cur.isOp) {
			if (nodes[idx].hasLeft && nodes[idx].hasRight) {
				switch (values[idx]) {
				case '+':
					return dfs(idx * 2) + dfs(idx * 2 + 1);
				case '-':
					return dfs(idx * 2) - dfs(idx * 2 + 1);
				case '*':
					return dfs(idx * 2) * dfs(idx * 2 + 1);
				case '/':
					return dfs(idx * 2) / dfs(idx * 2 + 1);
				}
			}
			throw new Exception();
		} else {
			if (!nodes[idx].hasLeft && !nodes[idx].hasRight)
				return values[idx] - '0';
			throw new Exception();
		}
	}

}
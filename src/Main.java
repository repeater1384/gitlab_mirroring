import java.util.Scanner;

public class Main {
	static int COL = 99;
	public static void main(String[] args) {
		check(0,0);
		check(3,0);
		check(1,1);
		check(4,1);
		check(1,2);
		check(3,2);
		check(0,3);
		check(0,4);
		check(2,4);
		check(4,4);
		check(3,0);
		check(0,3);
	}
	static void check(int row, int col) {
		System.out.println((char)(col+'A')+""+(row+1));
	}
	static int convertPos1(int row, int col) {
		return row * COL + col;
	}
	
	static int convertPos2(char[] pos) {
		int row = pos[1]-'0'-1;
		int col = pos[0]-'A';
		return convertPos1(row, col);
	}
	static int convertPos3(char[] pos) {
		int row = Integer.parseInt(new String(new char[] {pos[1],pos[2]}))-1;
		int col = pos[0] - 'A';
		return convertPos1(row, col);
	}
}
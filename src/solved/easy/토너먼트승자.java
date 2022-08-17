package solved.easy;
import java.util.Scanner;

public class 토너먼트승자 {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		double[][] arr = new double[8][8];
		for (int i = 0; i < 8; i++) {
			for (int j = i + 1; j < 8; j++) {
				arr[i][j] = sc.nextInt();
				arr[j][i] = 100 - arr[i][j];
			}

		}

		double[] winRate = new double[8];
		for (int i = 0; i < 8; i += 2) {
			winRate[i] = (double) arr[i][i + 1] / 100;
			winRate[i + 1] = (double) arr[i + 1][i] / 100;
		}
		double[] temp = new double[8];
		for (int i = 0; i < 8; i += 4) {
			temp[i] = winRate[i] * (arr[i][i + 2] * winRate[i + 2] + arr[i][i + 3] * winRate[i + 3])/100;
			temp[i + 1] = winRate[i + 1] * (arr[i + 1][i + 2] * winRate[i + 2] + arr[i + 1][i + 3] * winRate[i + 3])/100;
			temp[i + 2] = winRate[i + 2] * (arr[i + 2][i] * winRate[i] + arr[i + 2][i + 1] * winRate[i + 1])/100;
			temp[i + 3] = winRate[i + 3] * (arr[i + 3][i] * winRate[i] + arr[i + 3][i + 1] * winRate[i + 1])/100;
		}
		
		winRate = temp;
		temp = new double[8];
		temp[0] = winRate[0] * (arr[0][4]*winRate[4] + arr[0][5]*winRate[5] + arr[0][6]*winRate[6] + arr[0][7]*winRate[7])/100;
		temp[1] = winRate[1] * (arr[1][4]*winRate[4] + arr[1][5]*winRate[5] + arr[1][6]*winRate[6] + arr[1][7]*winRate[7])/100;
		temp[2] = winRate[2] * (arr[2][4]*winRate[4] + arr[2][5]*winRate[5] + arr[2][6]*winRate[6] + arr[2][7]*winRate[7])/100;
		temp[3] = winRate[3] * (arr[3][4]*winRate[4] + arr[3][5]*winRate[5] + arr[3][6]*winRate[6] + arr[3][7]*winRate[7])/100;
		temp[4] = winRate[4] * (arr[4][0]*winRate[0] + arr[4][1]*winRate[1] + arr[4][2]*winRate[2] + arr[4][3]*winRate[3])/100;
		temp[5] = winRate[5] * (arr[5][0]*winRate[0] + arr[5][1]*winRate[1] + arr[5][2]*winRate[2] + arr[5][3]*winRate[3])/100;
		temp[6] = winRate[6] * (arr[6][0]*winRate[0] + arr[6][1]*winRate[1] + arr[6][2]*winRate[2] + arr[6][3]*winRate[3])/100;
		temp[7] = winRate[7] * (arr[7][0]*winRate[0] + arr[7][1]*winRate[1] + arr[7][2]*winRate[2] + arr[7][3]*winRate[3])/100;
		
		for (double d : temp) {
			System.out.printf("%.13f ",d);
		}
		
		sc.close();
	}
}
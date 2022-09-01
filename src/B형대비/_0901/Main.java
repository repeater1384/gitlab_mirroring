package B형대비._0901;

import java.util.Scanner;

class Temp{
    String code;
    char K;
    int time;
    Temp(String code,char K,int time){
        this.code = code;
        this.K = K;
        this.time = time;
    }

    public String toString(){
        return String.format("secret code : %s\nmeeting point : %c\ntime : %d\n",code,K,time);
    }
}

public class Main {
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	String code = sc.next();	
    	char K = sc.next().charAt(0);
    	int time = sc.nextInt();
    	System.out.println(new Temp(code,K,time));
    }
}	
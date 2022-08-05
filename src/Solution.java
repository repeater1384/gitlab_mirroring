import java.util.*;
public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int tc=1; tc<=10; tc++) {
            int N=sc.nextInt();
            Stack<Character> s = new Stack<>();
            char[] arr = sc.next().toCharArray();
            for(int i=0; i<N; i++) {
                char cur =  arr[i];
                char val2 = 0;
                if(cur==')') val2 = '(';
                else if(cur==']') val2 = '[';
                else if(cur=='}') val2 = '{';
                else if(cur=='>') val2 = '<';
                if(!s.empty() && s.peek()==val2) s.pop();
                else s.add(cur);
            }
            System.out.printf("#%d %d\n",tc,s.size()>0?0:1);
        }
        sc.close();
    }
}
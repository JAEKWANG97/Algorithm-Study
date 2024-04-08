package ex0315;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int tre = 1000000;
        String n = br.readLine();

        int[] dp = new int[n.length()+1];
        dp[0] = 1;

        for(int i = 1; i < n.length()+1; i++){
            int first = n.charAt(i-1) - '0';
            if(first >= 1 && first <= 9){
                dp[i] += dp[i-1] % tre;
            }

            if(i==1) continue;

            int two = n.charAt(i-2) - '0';

            if(two == 0) continue;

            int ten = two*10+first;

            if(ten >= 10 && ten <= 26){
                dp[i] += dp[i-2] % tre;
            }
        }
        System.out.println(dp[n.length()] % tre);
    }//main end
}//class end

import java.util.*;
import java.io.*;
 
public class Main {
    static int mod = 1000000; //나누는 수
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
 
        int dp[] = new int[str.length() + 1];
        dp[0] = 1;//init
 
        for (int i = 1; i <= str.length(); i++) {
            int one = str.charAt(i-1) - '0';
            if (one >= 1 && one <= 9) { //10 미만
                dp[i] += dp[i - 1]; 
                dp[i] %= mod;
            }
 
            if(i == 1) continue;
 
            int two = str.charAt(i - 2) - '0';
 
            if(two == 0) continue;
 
            int ten = two * 10 + one;
 
            if (ten >= 10 && ten <= 26) { //10 이상이면
                dp[i] += dp[i - 2];
                dp[i] %= MOD;
            }
        }
        System.out.println(dp[str.length()]);
    }
}

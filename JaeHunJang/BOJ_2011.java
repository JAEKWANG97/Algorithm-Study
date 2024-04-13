import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 암호코드 - 60분
public class BOJ_2011 {
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MOD = 1000000;
        char[] N = br.readLine().toCharArray();
        int len = N.length;
        int[] dp = new int[len+1];
        dp[0] = 1;
        dp[1] = 1;
        if (N[0] == '0') {
            sb.append(0);
            return;
        }

        for (int i = 2; i <= len; i++) {
            int n = N[i-1]-'0';
            if (n >= 1 && n <= 9) {
                dp[i] = (dp[i] + dp[i-1]) % MOD;
            }
            if(N[i-2]-'0' == 0) continue;
            n += (N[i-2]-'0') * 10;
            if (n >= 10 && n <= 26) {
                dp[i] = (dp[i] + dp[i-2]) % MOD;
            }
        }

        sb.append(dp[len] % MOD);
    }
}
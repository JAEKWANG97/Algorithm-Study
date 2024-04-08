import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2011 {

    private static final int MOD = 1000000;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String pwd;

    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();

        if (pwd.startsWith("0")) {
            System.out.println(0);
            return;
        }

        dp();

        printAnswer();
    }

    private static void dp() {
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= pwd.length(); i++) {
            int now = Integer.parseInt(pwd.substring(i - 2, i));

            if (now == 10 || now == 20) {
                dp[i] = dp[i - 2];
            } else if (now % 10 == 0) {
                dp[pwd.length()] = 0;
                break;
            } else {
                dp[i] = now > 10 && now < 27 ? (dp[i - 1] + dp[i - 2]) % MOD : dp[i - 1];
            }
        }
    }

    private static void init() throws IOException {
        pwd = br.readLine();
        dp = new int[pwd.length() + 1];
    }

    private static void printAnswer() {
        System.out.println(dp[pwd.length()]);
    }
}

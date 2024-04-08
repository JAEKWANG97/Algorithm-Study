import java.io.*;

public class BOJ_2011 {
    private static final int MOD = 1_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        char[] word = br.readLine().toCharArray();
        if (word[0] == '0') {
            bw.write("0");
            return;
        }

        int[] dp = new int[word.length + 1];
        dp[0] = dp[1] = 1;

        for (int i = 2; i <= word.length; i++) {
            if (word[i - 1] != '0') {
                dp[i] += dp[i - 1] % MOD;
            }

            int temp = ((word[i - 2] - '0') * 10) + word[i - 1] - '0';
            if (temp >= 10 && temp <= 26) {
                dp[i] += dp[i - 2] % MOD;
            }
        }
        bw.write(String.valueOf(dp[word.length] % MOD));
    }
}

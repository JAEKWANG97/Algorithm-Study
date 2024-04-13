package hellomatia;

import java.io.*;

public class BOJ_2011 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main (String[] args) throws IOException {
        new BOJ_2011().solution();
    }

    private void solution() throws IOException {

        String str = bf.readLine();

        int dp[] = new int[str.length() + 1];
        dp[0] = 1;

        for (int i = 1; i <= str.length(); i++) {
            int one = str.charAt(i-1) - '0';
            if (one >= 1 && one <= 9) {
                dp[i] += dp[i - 1];
                dp[i] %= 1_000_000;
            }

            if(i == 1) continue;

            int two = str.charAt(i - 2) - '0';

            if(two == 0) continue;

            int ten = two * 10 + one;

            if (ten >= 10 && ten <= 26) {
                dp[i] += dp[i - 2];
                dp[i] %=  1_000_000;
            }
        }

        bw.write(dp[str.length()] + "\n");

        bw.flush();
        bw.close();
    }
}
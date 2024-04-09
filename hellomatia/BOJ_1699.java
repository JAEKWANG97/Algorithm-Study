package hellomatia;

import java.io.*;

public class BOJ_1699 {

    public static void main (String[] args) throws IOException {
        new BOJ_1699().solution();
    }

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(bf.readLine());
        int[] dp = new int[num+1];

        for (int i = 1; i <= num; i++) {
            dp[i] = Integer.MAX_VALUE;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        bw.write(dp[num]+"\n");

        bw.flush();
        bw.close();
    }
}
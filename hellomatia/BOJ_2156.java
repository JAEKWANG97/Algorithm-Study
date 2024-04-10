package hellomatia;

import java.io.*;

public class BOJ_2156 {
    int n; // 포도주의 갯수
    long[] dp;
    long[] wine;

    public static void main(String[] args) throws IOException {
        new BOJ_2156().solution();
    }

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(bf.readLine());
        dp = new long[n];
        wine = new long[n];

        for (int i = 0; i < n; i++) {
            wine[i] = Integer.parseInt(bf.readLine());
        }

        if (n >= 3) {
            bottomUp();
            bw.write(Math.max(dp[n - 2], dp[n - 1]) + "\n");
        } else if (n == 1) {
            bw.write(wine[0] + "\n");
        } else if (n == 2) {
            bw.write((wine[0] + wine[1]) + "\n");
        }

        bw.flush();
        bw.close();
    }

    public void bottomUp() {
        /*
        포도주 잔을 선택하면 그 잔에 들어있는 포도주는 모두 마셔야 하고, 마신 후에는 원래 위치에 다시 놓아야 한다.
        연속으로 놓여 있는 3잔을 모두 마실 수는 없다.
         */
        dp[0] = wine[0];
        dp[1] = dp[0] + wine[1];
        dp[2] = Math.max(dp[0], wine[1]) + wine[2];
        if (n >= 4) dp[3] = Math.max(dp[0] + wine[2], dp[1]) + wine[3];


        for (int i = 4; i < n; i++) {
            dp[i] = Math.max(dp[i - 2], Math.max(dp[i - 3], dp[i - 4]) + wine[i - 1]) + wine[i];
        }
    }
}
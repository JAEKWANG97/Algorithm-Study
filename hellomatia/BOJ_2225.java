package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2225 {

    public static void main(String[] args) throws IOException {
        new BOJ_2225().solution();
    }

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[K + 1][N + 1];

        for (int i = 1; i <= K; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
                dp[i][j] %= 1_000_000_000;
            }
        }

        bw.write(dp[K][N] + "\n");
        bw.flush();
        bw.close();
    }
}
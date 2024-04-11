package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_11051 {

    public static void main(String[] args) throws IOException {
        new BOJ_11051().solution();
    }

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][N + 1];

        dp[0][0] = 1;
        dp[1][0] = 1;
        dp[1][1] = 1;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0) dp[i][0] = 1;
                else if (j == i) dp[i][j] = 1;
                else dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
            }
        }

        bw.write(dp[N][K] + "\n");
        bw.flush();
        bw.close();
    }
}
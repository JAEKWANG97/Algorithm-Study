package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2293 {

    public static void main(String[] args) throws IOException {
        new BOJ_2293().solution();
    }

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] coins = new int[n + 1];
        int[] dp = new int[k + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            coins[i] = Integer.parseInt(bf.readLine());
            for (int j = coins[i]; j <= k; j++) {
                dp[j] += dp[j - coins[i]];
            }
        }

        bw.write(dp[k] + "\n");

        bw.flush();
        bw.close();

    }
}
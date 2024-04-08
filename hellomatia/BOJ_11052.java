package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_11052 {
    int N;
    int[] cards;
    int[] dp;

    public static void main(String[] args) throws IOException {
        new BOJ_11052().solution();
    }

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(bf.readLine());
        cards = new int[N + 1];
        dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(bf.readLine());

        for (int i = 1; i <= N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
            dp[i] = cards[i];
        }

        bottomUp();

        bw.write(dp[N] + "\n");
        bw.flush();
        bw.close();
    }

    public void bottomUp() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j < i; j++) {
                dp[i] = Math.max(dp[i], (dp[i - j] + dp[j]));
            }
        }
    }
}
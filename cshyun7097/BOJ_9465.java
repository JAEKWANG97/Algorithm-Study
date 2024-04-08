package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] sticker = new int[2][N];
            int[][] dp = new int[2][N];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if (N == 1) {
                sb.append(Math.max(sticker[0][0], sticker[1][0])).append("\n");
                continue;
            }
            dp[0][0] = sticker[0][0];
            dp[1][0] = sticker[1][0];
            dp[0][1] = sticker[0][1] + sticker[1][0];
            dp[1][1] = sticker[0][0] + sticker[1][1];
            for (int i = 2; i < N; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        dp[j][i] = Math.max(sticker[j][i] + dp[j + 1][i - 1], sticker[j][i] + dp[j + 1][i - 2]);
                    } else {
                        dp[j][i] = Math.max(sticker[j][i] + dp[j - 1][i - 1], sticker[j][i] + dp[j - 1][i - 2]);
                    }
                }
            }
            sb.append(Math.max(dp[1][N - 1], dp[0][N - 1])).append("\n");
        }
        System.out.println(sb);
    }
}

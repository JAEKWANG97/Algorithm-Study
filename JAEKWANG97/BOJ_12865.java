package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static class Item {
        int w; // 물건의 무게
        int v; // 물건의 가치

        public Item(int w, int v) {
            this.w = w;
            this.v = v;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 물건의 개수
        int K = Integer.parseInt(st.nextToken()); // 가방의 최대 무게

        Item[] items = new Item[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[i] = new Item(w, v);
        }
        System.out.println(dp(N, K, items));
    }

    private static int dp(int N, int K, Item[] items) {
        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            for (int w = 1; w <= K; w++) {
                if (items[i].w <= w) { // 현재 물건을 배낭에 넣을 수 있는 경우
                    dp[i][w] = Math.max(dp[i - 1][w], dp[i - 1][w - items[i].w] + items[i].v);
                } else { // 현재 물건을 배낭에 넣을 수 없는 경우
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[N][K];
    }
}

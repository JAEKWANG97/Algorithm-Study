import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n + 1];
        for (int i = 1; i < n + 1; i++) coin[i] = Integer.parseInt(br.readLine());
        int[][] dp = new int[n + 1][k + 1];

        for (int i = 1; i < n + 1; i++) {
            //for (int w = coin[i]; w < k + 1; w++) {
            for (int w = 0; w < k + 1; w++) {
                // coin i를 사용해서 w를 만들 수 있는 모든 경우의 수를 찾아라!
                int gap = 0;
                while (coin[i] * gap <= w) {
                    if (coin[i] * gap == w) dp[i][w]++;
                    else {
                        dp[i][w] += dp[i - 1][w - coin[i] * gap];
                    }
                    gap++;

                }
            }
        }

        for (int i = 0; i < n + 1; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        System.out.println(dp[n][k]);

    }
}
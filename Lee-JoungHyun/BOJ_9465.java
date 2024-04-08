import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());

            int[][] arr = new int[N][2];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i][0] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            if (N == 1) {
                sb.append(Math.max(arr[0][0], arr[0][1])).append("\n");
                continue;
            }

            // 해당 위치 떼기 + 해당 열 안떼기?
            int[][] dp = new int[N][2];

            dp[0][0] = arr[0][0];
            dp[0][1] = arr[0][1];
            dp[1][0] = arr[1][0] + arr[0][1];
            dp[1][1] = arr[1][1] + arr[0][0];

            for (int i = 2; i < N; i++) {
                dp[i][0] = arr[i][0] + Math.max(Math.max(dp[i - 2][0], dp[i - 2][1]), dp[i - 1][1]);
                dp[i][1] = arr[i][1] + Math.max(Math.max(dp[i - 2][0], dp[i - 2][1]), dp[i - 1][0]);
            }

            sb.append(Math.max(dp[N - 1][0], dp[N - 1][1])).append("\n");
        }
        System.out.println(sb);

    }
}

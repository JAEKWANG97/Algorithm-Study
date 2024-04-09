import java.io.*;
import java.util.StringTokenizer;

public class BOJ_9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[2][N + 1];
            int[][] dp = new int[2][N + 1];
            for (int i = 0; i < 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < N; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];
            dp[0][1] = arr[0][1] + arr[1][0];
            dp[1][1] = arr[1][1] + arr[0][0];

            for (int j = 2; j < N; j++) {
                dp[0][j] = Math.max(dp[1][j - 1], Math.max(dp[1][j - 2], dp[0][j - 2])) + arr[0][j];
                dp[1][j] = Math.max(dp[0][j - 1], Math.max(dp[0][j - 2], dp[1][j - 2])) + arr[1][j];
            }

            sb.append(Math.max(dp[0][N - 1], dp[1][N - 1])).append("\n");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1890 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][N];
        long[][] dp = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    continue;
                }
                if (i + arr[i][j] < N) {
                    dp[i + arr[i][j]][j] += dp[i][j];
                }
                if (j + arr[i][j] < N) {
                    dp[i][j + arr[i][j]] += dp[i][j];
                }
            }
        }
        bw.write(String.valueOf(dp[N - 1][N - 1]));

        br.close();
        bw.close();
    }
}
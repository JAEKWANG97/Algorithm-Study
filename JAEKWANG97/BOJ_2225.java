import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        dp(N, K);

    }

    private static void dp(int N, int K) {
        long[][] dp = new long[K+1][N+1];
        for (int i = 0; i <= K; i++) {
            dp[i][0] = 1; // i개의 정수를 사용해서 합이 0이 되는 경우의 수는 1
        }
        for (int i = 1; i <= K; i++) {
            for (int j = 1; j <= N; j++) {
                dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
            }
        }
     System.out.println(dp[K][N]);   
    }
}

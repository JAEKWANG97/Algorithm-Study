import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // dp[0]은 맨 앞자리 수
        int[][] dp = new int[N][10];
        for (int i = 1; i < 10; i++) {
            dp[0][i] = 1;
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = ((j == 0 ? 0 : dp[i-1][j-1]) + (j == 9 ? 0 : dp[i-1][j+1]))%1000000000;
            }
        }
        int answer = 0;
        for (int i = 0; i < 10; i++) {
            answer = (answer + dp[N-1][i])%1000000000;
        }
        System.out.println(answer);
    }
}

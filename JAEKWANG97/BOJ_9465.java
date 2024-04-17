import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int T;

    static int N;

    static int[][] arr;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            System.out.println(dp());
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[2][N];

        for (int i = 0; i < 2; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static int dp() {
        int[][] dp = new int[2][N + 1];

        dp[0][1] = arr[0][0];
        dp[1][1] = arr[1][0];

        if (N <= 1) {
            return Math.max(dp[0][1], dp[1][1]);
        }

        dp[0][2] = arr[1][0] + arr[0][1];
        dp[1][2] = arr[0][0] + arr[1][1];

        if (N <= 2) {
            return Math.max(dp[0][2], dp[1][2]);
        }

        for (int i = 3; i <= N; i++) {
            for (int j = 0; j < 2; j++) {
                dp[j][i] = Math.max(dp[(j + 1) % 2][i - 1], dp[(j + 1) % 2][i - 2]) + arr[j][i - 1];
            }
        }

        return Math.max(dp[0][N], dp[1][N]);
    }

}

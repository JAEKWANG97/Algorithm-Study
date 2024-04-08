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
            dp();
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

    private static void dp() {
        if (N < 2) {
            System.out.println(Math.max(arr[0][0], arr[1][0]));
            return;
        }

        int[][] dp = new int[2][N];
        int max = 0;
        dp[0][0] = arr[0][0];
        dp[1][0] = arr[1][0];
        dp[0][1] = arr[0][1] + arr[1][0];
        dp[1][1] = arr[1][1] + arr[0][0];
        max = Math.max(dp[0][0], dp[1][0]);
        for (int i = 2; i < N; i++) {
            dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + arr[0][i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + arr[1][i];

        }

        System.out.println(Math.max(dp[0][N - 1], dp[1][N - 1]));
    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2579 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;

    private static int[] stairs;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        dp();
    }

    private static void dp() {
        if (N <= 2) {
            System.out.println(Arrays.stream(stairs).sum());
            return;
        }

        dp[0] = stairs[0];
        dp[1] = stairs[0] + stairs[1];
        dp[2] = Math.max(stairs[0], stairs[1]) + stairs[2];

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 3] + stairs[i - 1] + stairs[i], dp[i - 2] + stairs[i]);
        }

        System.out.println(dp[N - 1]);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        stairs = new int[N];
        for (int i = 0; i < N; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        dp = new int[N];
    }
}

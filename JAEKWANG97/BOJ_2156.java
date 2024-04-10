import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        dp();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void dp() {
        // 2 + 1 || 1 + 2
        int[] dp = new int[N + 1];

        if (N == 1) {
            System.out.println(arr[0]);
            return;
        } else if (N == 2) {
            System.out.println(arr[0] + arr[1]);
            return;
        }

        dp[1] = arr[0];
        dp[2] = arr[1] + dp[1];
        dp[3] = Math.max(arr[1] + arr[2], arr[0] + arr[2]);
        dp[3] = Math.max(dp[3], dp[2]);

        for (int i = 4; i <= N; i++) {
            dp[i] = Math.max(dp[i - 2] + arr[i - 1], dp[i - 3] + arr[i - 2] + arr[i - 1]);
            dp[i] = Math.max(dp[i], dp[i - 1]);
        }

        System.out.println(dp[N]);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        int[] dp = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i] = 10000000;
        }

        if (arr[0] == 0) {
            if (N != 1)
                System.out.println(-1);
            else
                System.out.println(0);
            return;
        }

        int start = arr[0];
        for (int i = 1; i < Math.min(start + 1, arr.length); i++) {
            dp[i] = 1;
        }
        for (int i = 1; i < arr.length - 1; i++) {
            int step = arr[i];
            //if (step == 0) continue;
            for (int j = i + 1; j < Math.min(i + step + 1, arr.length); j++) {
                dp[j] = Math.min(dp[j], dp[i] + 1);
            }
        }

        //System.out.println(Arrays.toString(dp));
        if (dp[N-1] == 10000000)
            System.out.println(-1);
        else
            System.out.println(dp[N - 1]);

    }
}

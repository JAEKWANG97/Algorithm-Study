import java.io.*;

public class BOJ_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[10001];
        int[] arr = new int[10001];
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        dp[2] = arr[1] + arr[2];

        for (int i = 3; i <= N; i++) {
            dp[i] = Math.max(dp[i - 1], arr[i] + Math.max(arr[i - 1] + dp[i - 3], dp[i - 2]));
        }
        bw.write(String.valueOf(dp[N]));

        br.close();
        bw.close();
    }
}

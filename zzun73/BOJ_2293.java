import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2293 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] coin = new int[n];
        int[] dp = new int[k + 1];

        dp[0] = 1;
        for (int i = 0; i < n; i++) {
            coin[i] = Integer.parseInt(br.readLine());
            for (int j = coin[i]; j <= k; j++) {
                dp[j] += dp[j - coin[i]];
            }
        }

        bw.write(String.valueOf(dp[k]));
        br.close();
        bw.close();
    }
}
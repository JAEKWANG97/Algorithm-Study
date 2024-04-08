import java.io.*;
import java.util.StringTokenizer;

public class BOJ_12865 {
    static int N, K, W, V;
    static int[] weight, value, dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        dp = new int[K + 1];
        weight = new int[N];
        value = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            W = Integer.parseInt(st.nextToken());
            V = Integer.parseInt(st.nextToken());
            weight[i] = W;
            value[i] = V;
        }

        for (int i = 0; i < N; i++) {
            for (int j = K; j >= weight[i]; j--) {
                dp[j] = Math.max(dp[j - weight[i]] + value[i], dp[j]);
            }
        }

        bw.write(String.valueOf(dp[K]));
        br.close();
        bw.close();
    }
}
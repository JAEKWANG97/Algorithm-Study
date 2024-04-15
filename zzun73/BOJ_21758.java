import java.io.*;
import java.util.StringTokenizer;

public class BOJ_21758 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N + 1];
        long[] sum = new long[N + 1];
        long answer = 0;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum[i] = arr[i] + sum[i - 1];
        }

        for (int i = 2; i < N; i++) {
            // 벌 벌 꿀
            answer = Math.max(answer, (sum[N] - arr[i] - arr[1]) + (sum[N] - sum[i]));
            // 벌 꿀 벌
            answer = Math.max(answer, (sum[i] - arr[1]) + (sum[N] - sum[i - 1] - arr[N]));
            // 꿀 벌 벌
            answer = Math.max(answer, (sum[i - 1]) + (sum[N] - arr[i] - arr[N]));
        }
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
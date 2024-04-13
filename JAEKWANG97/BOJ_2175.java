import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2175 {
    static int N;
    static int[] honey;
    static long[] prefixSum;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        honey = new int[N];
        prefixSum = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            honey[i] = Integer.parseInt(st.nextToken());
            if (i == 0) {
                prefixSum[i] = honey[i];
            } else {
                prefixSum[i] += prefixSum[i - 1] + honey[i];
            }
        }
        long max = 0;

        for (int i = 1; i < N - 1; i++) {
            // 가장 오른쪽
            max = Math.max(max, prefixSum[N - 1] - honey[0] - honey[i] + prefixSum[N - 1] - prefixSum[i]);
            // 가장 왼쪽
            max = Math.max(max, prefixSum[N - 2] + prefixSum[i - 1] - honey[i]);
            // 벌통이 중간에 있을 때
            max = Math.max(max, prefixSum[i] - honey[0] + prefixSum[N - 2] - prefixSum[i - 1]);
        }
        System.out.println(max);
    }
}

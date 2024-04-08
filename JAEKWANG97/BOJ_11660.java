import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int N, M;
    static int[][] arr, PrefixSum;

    public static void main(String[] args) throws IOException {
        init();
        calculate();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N+1][N+1];
        PrefixSum = new int[N+1][N+1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                PrefixSum[i][j] = arr[i][j] + PrefixSum[i-1][j] + PrefixSum[i][j-1] - PrefixSum[i-1][j-1];
            }
        }
    }

    private static void calculate() throws IOException {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            int result = PrefixSum[x2][y2] - PrefixSum[x2][y1-1] - PrefixSum[x1-1][y2] + PrefixSum[x1-1][y1-1];
            sb.append(result).append('\n');
        }
        System.out.print(sb);
    }
}

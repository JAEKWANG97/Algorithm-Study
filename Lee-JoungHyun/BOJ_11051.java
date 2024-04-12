import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11051 {
    private static final int mod = 10_007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // nCk 구하기
        int[][] nCk = new int[1001][1001];
        nCk[0][0] = 1;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i + 1; j++) {
                if (j == 0 || j == i) {
                    nCk[i][j] = 1;
                } else {
                    nCk[i][j] = (nCk[i - 1][j - 1] + nCk[i - 1][j]) % mod;
                }
            }
        }
        if (K == 0) System.out.println(1);
        else System.out.println((nCk[N - 1][K - 1] + nCk[N - 1][K]) % mod);
    }
}

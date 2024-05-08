import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int[] coins;
    static int[][] dp;
    static int count;

    static int T;
    static int N, M;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            input();
            dfs(M, N - 1);
            bw.write(dp[M][N - 1] + "\n");
        }
        bw.flush();
    }

    private static void input() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        coins = new int[N];

        count = 0;
        for (int i = 0; i < N; i++) {
            coins[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());

        dp = new int[M + 1][N]; // M 값을 받은 후에 dp 배열을 초기화합니다.
    }

    private static int dfs(int m, int index) {
        if (m == 0) {
            return 1;
        }

        if (m < 0) {
            return 0;
        }

        if (index < 0) {
            return 0;
        }

        if (dp[m][index] != 0) {
            return dp[m][index];
        }

        int without = dfs(m, index - 1);
        int with = dfs(m - coins[index], index);

        return dp[m][index] = without + with;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890 {

    private static int N;
    private static int[][] map;

    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        init();

        makeDPTable();

        printAnswer();
    }

    public static void makeDPTable() {
        dp = new long[N][N];

        dp[0][0] = 1;

        for (int x = 0; x < N; x++) {
            for (int i = 0; i < x; i++) {
                if (map[0][i] == x - i) {
                    dp[0][x] += dp[0][i];
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int i = 0; i < y; i++) {
                if (map[i][0] == y - i) {
                    dp[y][0] += dp[i][0];
                }
            }
        }

        for (int y = 1; y < N; y++) {
            for (int x = 1; x < N; x++) {
                for (int i = 0; i < x; i++) {
                    if (map[y][i] == x - i) {
                        dp[y][x] += dp[y][i];
                    }
                }

                for (int i = 0; i < y; i++) {
                    if (map[i][x] == y - i) {
                        dp[y][x] += dp[i][x];
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void printAnswer() {
        System.out.println(dp[N - 1][N - 1]);
    }
}

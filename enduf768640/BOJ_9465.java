import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_9465 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T;
    private static int N;

    private static int[][] scores;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            init();

            getMaxScore();

            printAnswer();
        }
    }

    private static void getMaxScore() {
        dp[0][1] = scores[0][1];
        dp[1][1] = scores[1][1];

        for (int i = 2; i <= N; i++) {
            dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + scores[0][i];
            dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + scores[1][i];
        }
    }

    private static void printAnswer() {
        System.out.println(Math.max(dp[0][N], dp[1][N]));
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        scores = new int[2][N + 1];
        for (int y = 0; y < 2; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                scores[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new int[2][N + 1];
    }
}

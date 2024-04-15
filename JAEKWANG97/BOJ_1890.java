import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1890 {
    static class Location {
        int r;
        int c;
        int value;

        public Location(int r, int c, int value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }
    }

    static int N;
    static int[][] map;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        dp();

    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void dp() {

        long[][] dp = new long[N][N];

        dp[0][0] = 1;

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int jump = map[r][c];

                if (jump == 0) {
                    break;
                }

                if (r + jump < N) {
                    dp[r + jump][c] += dp[r][c];

                }
                if (c + jump < N) {
                    dp[r][c + jump] += dp[r][c];
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}

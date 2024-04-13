import java.io.*;
import java.util.*;

public class BOJ_17070 {
    static int N;
    static int[][][] dp;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        dp = new int[N + 1][N + 1][3]; // 0: 가로, 1: 세로, 2: 대각선

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[1][2][0] = 1; // 시작 위치 경우의 수 1

        for (int i = 1; i <= N; i++) {
            for (int j = 2; j <= N; j++) {
                if (map[i][j] == 0) {
                    updateDp(i, j);
                }
            }
        }

        System.out.println(dp[N][N][0] + dp[N][N][1] + dp[N][N][2]);
    }

    private static void updateDp(int i, int j) {
        // 가로 이동
        dp[i][j][0] += dp[i][j - 1][0] + dp[i][j - 1][2];
        // 세로 이동
        if (i > 1)
            dp[i][j][1] += dp[i - 1][j][1] + dp[i - 1][j][2];
        // 대각선 이동 가능 조건 확인
        if (i > 1 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {
            dp[i][j][2] += dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
        }
    }
}

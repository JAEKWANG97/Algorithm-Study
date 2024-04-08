import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_17070 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int n = Integer.parseInt(br.readLine());
        int[][] map = new int[n+1][n+1];
        // 파이프의 오른쪽 입장으로, 상태, y, x 순
        int[][][] dp = new int[3][n+1][n+1];

        for (int y = 1; y < n + 1; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x < n + 1; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
        dp[0][1][2] = 1;
        for (int y = 1; y < n + 1; y++) {
            for (int x = 3; x < n + 1; x++) {
                for (int state = 0; state < 4; state++) {
                    if (map[y][x] == 0) {
                        // 1. 수평 세팅
                        dp[0][y][x] = dp[0][y][x - 1] + dp[1][y][x - 1];
                        // 2. 수직 세팅
                        dp[2][y][x] = dp[1][y - 1][x] + dp[2][y - 1][x];
                    }
                    // 3. 대각 세팅
                    if (map[y][x] == 0 && map[y - 1][x] == 0 && map[y][x - 1] == 0) {
                        dp[1][y][x] = dp[0][y - 1][x - 1] + dp[2][y - 1][x - 1] + dp[1][y - 1][x - 1];
                    }
                }
            }
        }
        System.out.println(dp[0][n][n] + dp[1][n][n] + dp[2][n][n]);
    }
}

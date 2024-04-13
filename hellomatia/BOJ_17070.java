package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_17070 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    int N;
    int[][] map;
    int[][][] dp;

    public static void main(String[] args) throws IOException {
        new BOJ_17070().solution();
    }

    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());
        map = new int[N][N];
        dp = new int[N][N][3];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][1][0] = 1;  // 최초 상태
        for (int i = 0; i < N; i++) {
            for (int j = 2; j < N; j++) {
                if (map[i][j] == 0) {  // 가로
                    dp[i][j][0] = dp[i][j - 1][0] + dp[i][j - 1][2];
                }
                if (i - 1 >= 0 && map[i][j] == 0) {  // 세로
                    dp[i][j][1] = dp[i - 1][j][1] + dp[i - 1][j][2];
                }
                if (i - 1 >= 0 && map[i][j] == 0 && map[i - 1][j] == 0 && map[i][j - 1] == 0) {  // 대각
                    dp[i][j][2] = dp[i - 1][j - 1][0] + dp[i - 1][j - 1][1] + dp[i - 1][j - 1][2];
                }
            }
        }

        bw.write(dp[N - 1][N - 1][0] + dp[N - 1][N - 1][1] + dp[N - 1][N - 1][2] + "\n");
        bw.flush();
        bw.close();

    }
}
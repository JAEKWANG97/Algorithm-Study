package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1890 {

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    int N;
    int[][] map;
    long[][] dp;

    public static void main (String[] args) throws IOException {
        new BOJ_1890().solution();
    }

    public void solution() throws IOException {

        N = Integer.parseInt(bf.readLine());

        map = new int[N][N];
        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp = new long[N][N];
        dp[0][0] = 1;

        for (int row = 0; row < N; row++) {
            for (int col = 0; col < N; col++) {
                int distance = map[row][col];
                if(distance == 0) continue;
                if (distance + row < N) {
                    dp[distance + row][col] += dp[row][col];
                }
                if (distance + col < N) {
                    dp[row][distance + col] += dp[row][col];
                }
            }
        }

        bw.write(dp[N-1][N-1] + "\n");
        bw.flush();
        bw.close();
    }
}
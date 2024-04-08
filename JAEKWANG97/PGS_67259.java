import java.util.*;
import java.io.*;

class Solution {
    public int solution(int[][] board) {
        int n = board.length;
        final int INF = Integer.MAX_VALUE;
        int[][][] dp = new int[n][n][2]; // 0: 가로, 1: 세로
        for (int[][] layer : dp) {
            for (int[] row : layer) {
                Arrays.fill(row, INF);
            }
        }

        // 초기 방향 설정: 가로(0), 세로(1)
        dp[0][0][0] = 0;
        dp[0][0][1] = 0;

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0, 0, 0}); // x, y, direction, cost
        queue.offer(new int[]{0, 0, 1, 0}); // x, y, direction, cost

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int dir = curr[2];
            int cost = curr[3];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                int nCost = cost;

                if (nx >= 0 && nx < n && ny >= 0 && ny < n && board[nx][ny] == 0) {
                    if ((i == 0 || i == 2) == (dir == 0)) { // 같은 방향
                        nCost += 100;
                    } else { // 방향 전환
                        nCost += 600;
                    }

                    if (dp[nx][ny][(i == 0 || i == 2) ? 0 : 1] > nCost) {
                        dp[nx][ny][(i == 0 || i == 2) ? 0 : 1] = nCost;
                        queue.offer(new int[]{nx, ny, (i == 0 || i == 2) ? 0 : 1, nCost});
                    }
                }
            }
        }

        return Math.min(dp[n-1][n-1][0], dp[n-1][n-1][1]);
    }
}

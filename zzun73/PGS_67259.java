import java.util.*;

public class PGS_67259 {
    static int[][][] dp;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer, size;

    static class Pos {
        int x, y, dir;

        public Pos(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    void bfs(int[][] board) {
        Deque<Pos> deque = new ArrayDeque<>();
        deque.add(new Pos(0, 0, 0));

        while (!deque.isEmpty()) {
            Pos cur = deque.poll();

            if (cur.x == size - 1 && cur.y == size - 1) {
                answer = Math.min(dp[cur.x][cur.y][cur.dir], answer);
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx > size - 1 || ny < 0 || ny > size - 1 || board[nx][ny] == 1) {
                    continue;
                }

                int curCost = dp[cur.x][cur.y][cur.dir];
                if ((cur.x == 0 && cur.y == 0) || i == cur.dir) {
                    curCost += 100;
                } else {
                    curCost += 600;
                }

                if (dp[nx][ny][i] >= curCost) {
                    dp[nx][ny][i] = curCost;
                    deque.add(new Pos(nx, ny, i));
                }

            }
        }
    }

    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        dp = new int[board.length][board.length][4];
        size = board.length;
        for (int[][] row : dp) {
            for (int[] col : row) {
                Arrays.fill(col, Integer.MAX_VALUE);
            }
        }
        dp[0][0][0] = dp[0][0][1] = dp[0][0][2] = dp[0][0][3] = 0;
        bfs(board);

        return answer;
    }
}
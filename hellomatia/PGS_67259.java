package hellomatia;

import java.util.*;

class PGS_67259 {

    class Point {
        int x;
        int y;
        int prevD;
        int cost;

        Point(int x, int y, int prevD, int cost) {
            this.x = x;
            this.y = y;
            this.prevD = prevD;
            this.cost = cost;
        }
    }

    private int[][] board;
    private int N;
    private int[] dirX = {1, -1, 0, 0};
    private int[] dirY = {0, 0, 1, -1};

    public int solution(int[][] board) {
        this.board = board;
        N = board.length;

        return bfs();
    }

    private int bfs() {

        int[][][] visited = new int[2][N][N];
        Queue<Point> queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[0][i], Integer.MAX_VALUE);
            Arrays.fill(visited[1][i], Integer.MAX_VALUE);
        }

        queue.offer(new Point(0, 0, 0, 0));
        queue.offer(new Point(0, 0, 1, 0));
        visited[0][0][0] = 0;
        visited[1][0][0] = 0;


        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (visited[now.prevD][now.x][now.y] < now.cost) continue;

            if (now.prevD == 0) {
                for (int i = 0; i < 2; i++) {
                    int nextX = now.x + dirX[i];
                    int nextY = now.y + dirY[i];

                    if (isIn(nextX, nextY) && board[nextX][nextY] == 0) {
                        if (visited[0][nextX][nextY] <= now.cost + 100) continue;
                        visited[0][nextX][nextY] = now.cost + 100;
                        queue.offer(new Point(nextX, nextY, 0, now.cost + 100));
                    }
                }
                for (int i = 2; i < 4; i++) {
                    int nextX = now.x + dirX[i];
                    int nextY = now.y + dirY[i];

                    if (isIn(nextX, nextY) && board[nextX][nextY] == 0) {
                        if (visited[1][nextX][nextY] <= now.cost + 600) continue;
                        visited[1][nextX][nextY] = now.cost + 600;
                        queue.offer(new Point(nextX, nextY, 1, now.cost + 600));
                    }
                }
            } else {
                for (int i = 0; i < 2; i++) {
                    int nextX = now.x + dirX[i];
                    int nextY = now.y + dirY[i];

                    if (isIn(nextX, nextY) && board[nextX][nextY] == 0) {
                        if (visited[0][nextX][nextY] <= now.cost + 600) continue;
                        visited[0][nextX][nextY] = now.cost + 600;
                        queue.offer(new Point(nextX, nextY, 0, now.cost + 600));
                    }
                }
                for (int i = 2; i < 4; i++) {
                    int nextX = now.x + dirX[i];
                    int nextY = now.y + dirY[i];

                    if (isIn(nextX, nextY) && board[nextX][nextY] == 0) {
                        if (visited[1][nextX][nextY] <= now.cost + 100) continue;
                        visited[1][nextX][nextY] = now.cost + 100;
                        queue.offer(new Point(nextX, nextY, 1, now.cost + 100));
                    }
                }
            }
        }
        return Math.min(visited[0][N - 1][N - 1], visited[1][N - 1][N - 1]);
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }
}

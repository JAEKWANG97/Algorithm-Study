package hellomatia;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class BOJ_2665 {

    class Point {
        int x;
        int y;
        int count;

        Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    private final static int BLACK = 0;
    private final static int WHITE = 1;

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private final int[] dirX = {0, -1, 0, 1};
    private final int[] dirY = {-1, 0, 1, 0};

    private int n;
    private int[][] map;

    public static void main(String[] args) throws IOException {
        new BOJ_2665().solution();
    }

    public void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        n = Integer.parseInt(bf.readLine());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = rowValue.charAt(j) - '0';
            }
        }
    }

    private int calcAns() {
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(new Point(0, 0, 0));

        int[][] visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }

        visited[0][0] = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (visited[now.x][now.y] < now.count) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (isIn(nextX, nextY) && map[nextX][nextY] == WHITE) {
                    if (visited[nextX][nextY] > now.count) {
                        visited[nextX][nextY] = now.count;
                        queue.offer(new Point(nextX, nextY, now.count));
                    }
                } else if (isIn(nextX, nextY) && map[nextX][nextY] == BLACK) {
                    if (isIn(nextX, nextY) && visited[nextX][nextY] > now.count + 1) {
                        visited[nextX][nextY] = now.count + 1;
                        queue.offer(new Point(nextX, nextY, now.count + 1));
                    }
                }
            }
        }

        return visited[n - 1][n - 1];
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }

    private void printAns(int answer) throws IOException {
        bw.write(answer + "\n");
        bw.flush();
    }
}

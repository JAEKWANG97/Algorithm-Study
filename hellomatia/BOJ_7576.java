package hellomatia;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int M, N;
    private int[][] map;

    private Queue<Point> tomatoQ;

    private final int[] dirX = {-1, 0, 1, 0};
    private final int[] dirY = {0, -1, 0, 1};

    private int count;

    public static void main(String[] args) throws IOException {
        new BOJ_7576().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        tomatoQ = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    tomatoQ.offer(new Point(i, j));
                } else if (map[i][j] == 0) {
                    count++;
                }
            }
        }
    }

    private int calcResult() {
        if (count == 0) return 0;
        int day = 0;
        while (oneDayAfter()) {
            day++;
        }
        if (count != 0) return -1;
        return day;
    }

    private boolean oneDayAfter() {
        int tomatoSize = tomatoQ.size();

        for (int i = 0; i < tomatoSize; i++) {
            Point now = tomatoQ.poll();
            for (int j = 0; j < 4; j++) {
                int nextX = now.x + dirX[j];
                int nextY = now.y + dirY[j];
                if (!isIn(nextX, nextY)) continue;
                if (map[nextX][nextY] == 0) {
                    count--;
                    map[nextX][nextY] = 1;
                    tomatoQ.offer(new Point(nextX, nextY));
                }
            }
        }

        if (tomatoQ.isEmpty()) return false;
        return true;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    public void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}

package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_4179 {

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

    private static final char EMPTY = '.';
    private static final char WALL = '#';
    private static final char START = 'J';
    private static final char FIRE = 'F';

    private int[] dirX = {0, 1, 0, -1};
    private int[] dirY = {1, 0, -1, 0};

    private char[][] map;
    private int R, C;
    private Point startPoint;
    private Queue<Point> fireQ;
    private Queue<Point> jQ;
    private boolean[][] visited;

    public static void main(String[] args) throws IOException {
        new BOJ_4179().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        fireQ = new ArrayDeque<>();
        for (int i = 0; i < R; i++) {
            String rowValue = bf.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = rowValue.charAt(j);
                if (map[i][j] == START) {
                    startPoint = new Point(i, j);
                    map[i][j] = EMPTY;
                } else if (map[i][j] == FIRE) {
                    fireQ.offer(new Point(i, j));
                }
            }
        }
    }

    private int calcResult() {
        jQ = new ArrayDeque<>();
        jQ.offer(startPoint);
        visited = new boolean[R][C];
        visited[startPoint.x][startPoint.y] = true;

        int time = 1;
        while (!jQ.isEmpty()) {
            oneMinAfterMap();
            if (oneMinAfterJ()) return time;
            time++;
        }

        return -1;
    }

    private boolean oneMinAfterJ() {
        int size = jQ.size();
        for (int i = 0; i < size; i++) {
            Point now = jQ.poll();
            if (isFinish(now.x, now.y)) return true;
            for (int j = 0; j < 4; j++) {
                int nextX = now.x + dirX[j];
                int nextY = now.y + dirY[j];
                if (isIn(nextX, nextY) && map[nextX][nextY] == EMPTY && !visited[nextX][nextY]) {
                    visited[nextX][nextY] = true;
                    jQ.offer(new Point(nextX, nextY));
                }
            }
        }
        return false;
    }

    private void oneMinAfterMap() {
        int size = fireQ.size();
        for (int i = 0; i < size; i++) {
            Point now = fireQ.poll();
            for (int j = 0; j < 4; j++) {
                int nextX = now.x + dirX[j];
                int nextY = now.y + dirY[j];
                if (isIn(nextX, nextY) && map[nextX][nextY] == EMPTY) {
                    map[nextX][nextY] = FIRE;
                    fireQ.offer(new Point(nextX, nextY));
                }
            }
        }
    }

    private boolean isFinish(int x, int y) {
        return 0 == x || x == R - 1 || 0 == y || y == C - 1;
    }

    private boolean isIn(int x, int y) {
        return 0 <= x && x < R && 0 <= y && y < C;
    }

    public void printResult(int result) throws IOException {
        if (result == -1) {
            bw.write("IMPOSSIBLE\n");
            return;
        }
        bw.write(result + "\n");
        bw.flush();
    }
}
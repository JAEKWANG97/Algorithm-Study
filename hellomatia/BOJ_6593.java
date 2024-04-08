package hellomatia;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {

    class Point {
        int x;
        int y;
        int z;
        int time;

        Point(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private static final char GOLD = '#';
    private static final char EMPTY = '#';
    private static final char START = 'S';
    private static final char EXIT = 'E';

    private final int[] dirX = {0, -1, 0, 1, 0, 0};
    private final int[] dirY = {-1, 0, 1, 0, 0, 0};
    private final int[] dirZ = {0, 0, 0, 0, -1, 1};

    private int L, R, C;
    private char[][][] map;
    private Point startPoint;

    public static void main(String[] args) throws IOException {
        new BOJ_6593().solution();
    }

    private void solution() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        while (L != 0 && R != 0 && C != 0) {
            init();
            printResult(calcResult());
            st = new StringTokenizer(bf.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
        }
        bw.close();
    }

    private void init() throws IOException {
        map = new char[L][R][C];
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < R; j++) {
                String rowValue = bf.readLine();
                for (int k = 0; k < C; k++) {
                    map[i][j][k] = rowValue.charAt(k);
                    if (map[i][j][k] == START) {
                        startPoint = new Point(j, k, i, 0);
                    }
                }
            }
            bf.readLine();
        }
    }

    private int calcResult() {
        boolean[][][] visited = new boolean[L][R][C];
        Queue<Point> queue = new ArrayDeque<>();
        queue.offer(startPoint);
        visited[startPoint.z][startPoint.x][startPoint.y] = true;

        while (!queue.isEmpty()) {

            Point now = queue.poll();

            if (map[now.z][now.x][now.y] == EXIT) {
                return now.time;
            }

            for (int i = 0; i < 6; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];
                int nextZ = now.z + dirZ[i];

                if (isIn(nextX, nextY, nextZ)
                        && map[nextZ][nextX][nextY] != GOLD
                        && !visited[nextZ][nextX][nextY]) {
                    visited[nextZ][nextX][nextY] = true;
                    queue.offer(new Point(nextX, nextY, nextZ, now.time + 1));
                }
            }
        }

        return -1;
    }

    private boolean isIn(int x, int y, int z) {
        return 0 <= x && x < R && 0 <= y && y < C && 0 <= z && z < L;
    }

    private void printResult(int result) throws IOException {
        if (result == -1) {
            bw.write("Trapped!\n");
        } else {
            bw.write("Escaped in " + result + " minute(s).\n");
        }
        bw.flush();
    }
}

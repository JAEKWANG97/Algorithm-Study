package hellomatia;

import java.io.*;
import java.util.*;

class BOJ_3055 {

    class Point {
        int x;
        int y;
        int count;

        public Point(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ_3055().solution();
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int R, C;
    static char[][] map;
    static int[] dirX = {1, -1, 0, 0};
    static int[] dirY = {0, 0, 1, -1};
    static Queue<Point> waterQ = new LinkedList<>();
    static Queue<Point> beaverQ = new LinkedList<>();

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        boolean[][] visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = bf.readLine();
            for (int j = 0; j < C; j++) {

                char ch = str.charAt(j);
                map[i][j] = ch;

                if (ch == '*') {
                    waterQ.offer(new Point(i, j, 0));
                } else if (ch == 'S') {
                    visited[i][j] = true;
                    beaverQ.offer(new Point(i, j, 0));
                }
            }
        }

        int waterMapCount = 0;

        boolean safe = false;

        while (!beaverQ.isEmpty()) {
            Point now = beaverQ.poll();

            if (map[now.x][now.y] == 'D') {
                safe = true;
                bw.write(now.count + "\n");
                break;
            }

            if (waterMapCount == now.count) {
                waterMapCount = waterExtendMap(now.count);
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || R <= nextX || C <= nextY
                        || map[nextX][nextY] == 'X' || map[nextX][nextY] == '*'
                        || visited[nextX][nextY]) {
                    continue;
                }

                visited[nextX][nextY] = true;
                beaverQ.offer(new Point(nextX, nextY, now.count + 1));
            }
        }

        if (!safe) {
            bw.write("KAKTUS\n");
        }

        bw.flush();
        bw.close();
    }

    public int waterExtendMap(int count) {

        while (!waterQ.isEmpty()) {
            Point now = waterQ.poll();

            if (now.count == count + 1) {
                waterQ.offer(now);
                break;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dirX[i];
                int nextY = now.y + dirY[i];

                if (nextX < 0 || nextY < 0 || R <= nextX || C <= nextY || map[nextX][nextY] != '.') {
                    continue;
                }

                map[nextX][nextY] = '*';
                waterQ.offer(new Point(nextX, nextY, count + 1));
            }
        }

        //printMap();

        return count + 1;
    }

    public void printMap() {
        for (int i = 0; i < R; i++) {
            System.out.println(String.valueOf(map[i]) + " ");
        }
        System.out.println();
    }
}
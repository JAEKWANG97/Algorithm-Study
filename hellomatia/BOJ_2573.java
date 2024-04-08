package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2573 {

    class Point {
        int x;
        int y;
        int h;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    int N, M; // 세로 가로
    int[][] map;
    int[] dirR = {0, 0, 1, -1};
    int[] dirC = {1, -1, 0, 0};
    int year = 0;

    public static void main(String[] args) throws IOException {
        new BOJ_2573().solution();
    }

    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simulation();

        bw.write(year + "\n");
        bw.flush();
        bw.close();
    }

    public void simulation() {
        if (isSeparated() == 1) return;
        else if (isSeparated() == -1) {
            year = 0;
            return;
        }

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;

                int waterCount = 0;

                for (int k = 0; k < 4; k++) {
                    int x = i + dirC[k];
                    int y = j + dirR[k];

                    if (x < 0 || y < 0 || N <= x || M <= y || map[x][y] != 0) continue;

                    waterCount++;
                }
                int height = map[i][j] - waterCount;
                if (height < 0) height = 0;

                queue.add(new Point(i, j, height));
            }
        }

        while (!queue.isEmpty()) {
            Point ice = queue.poll();
            int x = ice.x;
            int y = ice.y;
            int h = ice.h;

            map[x][y] = h;
        }

        year++;
        simulation();
    }

    public int isSeparated() {
        int count = 0;
        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    count++;
                    if (queue.isEmpty()) {
                        queue.add(new Point(i, j));
                    }
                }
            }
        }
        if (count == 0) return -1;

        int checkCount = 1;

        boolean[][] isVisited = new boolean[N][M];
        isVisited[queue.peek().x][queue.peek().y] = true;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int x = now.x + dirR[i];
                int y = now.y + dirC[i];
                if (x < 0 || y < 0 || N <= x || M <= y || map[x][y] == 0 || isVisited[x][y]) continue;

                isVisited[x][y] = true;
                checkCount++;
                queue.add(new Point(x, y));
            }
        }
        if (checkCount == count) return 0;
        else return 1;
    }
}
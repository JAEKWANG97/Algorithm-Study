package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2589 {

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int[] dirR = {0, 0, 1, -1};
    int[] dirC = {1, -1, 0, 0};
    int row, col;
    int[][] map;
    int cost;
    ArrayList<Point> land = new ArrayList<>();
    int maxCost = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        new BOJ_2589().solution();
    }

    public void solution() throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());

        map = new int[row][col];

        for (int i = 0; i < row; i++) {
            String str = bf.readLine();
            for (int j = 0; j < col; j++) {
                char ch = str.charAt(j);
                if (ch == 'L') {
                    map[i][j] = 1;
                    land.add(new Point(i, j));
                }
            }
        }

        scanMap();


        bw.write(maxCost + "\n");
        bw.flush();
        bw.close();

    }

    public void scanMap() {
        for (int i = 0; i < land.size(); i++) {
            maxCost = Math.max(maxCost, calculationLongestDistance(i));
        }
    }

    public int calculationLongestDistance(int idx) {
        Queue<Point> q = new LinkedList<>();
        int[][] visit = new int[row][col];

        int max = 0;
        q.add(new Point(land.get(idx).x, land.get(idx).y));
        visit[land.get(idx).x][land.get(idx).y] = 1;

        while (!q.isEmpty()) {
            Point now = q.poll();
            max = Math.max(max, visit[now.x][now.y]);

            for (int i = 0; i < 4; i++) {
                int x = dirR[i] + now.x;
                int y = dirC[i] + now.y;
                if (x < 0 || x >= row || y < 0 || y >= col || visit[x][y] != 0 || map[x][y] == 0) continue;

                visit[x][y] = visit[now.x][now.y] + 1;
                q.add(new Point(x, y));
            }

        }
        return max - 1;
    }
}
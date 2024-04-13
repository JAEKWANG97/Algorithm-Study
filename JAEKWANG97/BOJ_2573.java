package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int[] deltaX = new int[]{0, 1, 0, -1};
    static int[] deltaY = new int[]{-1, 0, 1, 0};


    static int N, M;
    static int[][] map;
    static Queue<Location> iceburgList = new ArrayDeque<>();


    public static void main(String[] args) throws IOException {
        init();
        System.out.println(meltIceburg());
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int item = Integer.parseInt(st.nextToken());
                map[i][j] = item;
                if (item > 0) {
                    iceburgList.offer(new Location(i, j));
                }
            }
        }


    }

    private static int meltIceburg() {

        int time = 0;
        int[][] copyMap = cloneMap(map);
        while (!iceburgList.isEmpty()) {
            int size = iceburgList.size();
            map = cloneMap(copyMap);
            copyMap = cloneMap(map);
            if (confirmIceburg()) {
                return time;
            }
            while (size-- > 0) {
                Location cur = iceburgList.poll();
                int cnt = 0;

                for (int i = 0; i < 4; i++) {
                    int nx = cur.x + deltaX[i];
                    int ny = cur.y + deltaY[i];
                    if (isIn(nx, ny) && map[nx][ny] <= 0) {
                        cnt += 1;
                    }
                }

                copyMap[cur.x][cur.y] -= cnt;
                if (copyMap[cur.x][cur.y] > 0) {
                    iceburgList.add(cur);
                }
            }
            time += 1;
        }
        return 0;
    }


    private static boolean confirmIceburg() {
        Location start = iceburgList.peek();
        Queue<Location> confirmQue = new ArrayDeque<>();
        confirmQue.add(start);
        boolean[][] visited = new boolean[N][M];
        while (!confirmQue.isEmpty()) {
            Location cur = confirmQue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + deltaX[i];
                int ny = cur.y + deltaY[i];
                if (isIn(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0) {
                    confirmQue.add(new Location(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }

        for (Location location : iceburgList) {
            if (!visited[location.x][location.y]) {
                return true;
            }
        }

        return false;
    }

    private static int[][] cloneMap(int[][] map) {
        int[][] copyMap = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                copyMap[i][j] = map[i][j];
            }
        }
        return copyMap;
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && nx < N && ny >= 0 && ny < M;
    }
}

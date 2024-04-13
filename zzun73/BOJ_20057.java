import java.util.*;
import java.io.*;

public class BOJ_20057 {
    static int[][] map;

    static int[][] directionX = {
            {1, 1, 0, 0, -1, -1, 0, 0, -2, -1},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {-1, -1, 0, 0, 1, 1, 0, 0, 2, 1},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0}
    };
    static int[][] directionY = {
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {-1, -1, 0, 0, 1, 1, 0, 0, 2, 1},
            {-1, 1, -1, 1, -1, 1, -2, 2, 0, 0},
            {1, 1, 0, 0, -1, -1, 0, 0, -2, -1}

    };
    static double[] percent = {0.01, 0.01, 0.07, 0.07, 0.1, 0.1, 0.02, 0.02, 0.05, 0};
    static int sum = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());

        map = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                sum += map[i][j];
            }
        }
        tornado();
    }

    public static void countMap() {
        int next = 0;
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map.length; j++) {
                next += map[i][j];
            }
        }
        System.out.println(sum - next);

        System.exit(0);
    }

    public static void tornado() {
        Queue<Integer[]> queue = new ArrayDeque<>();

        queue.add(new Integer[]{map.length / 2, map.length / 2, 0, 1});
        int count = 0;
        int[] dy = {0, 1, 0, -1};
        int[] dx = {-1, 0, 1, 0};
        while (true) {
            Integer[] cur = queue.poll();
            int y = cur[0];
            int x = cur[1];
            int dir = cur[2];
            int cycle = cur[3];
            count++;

            for (int i = 0; i < cycle; i++) {
                int ny = y + dy[dir];
                int nx = x + dx[dir];

                spreadDirt(ny, nx, dir);

                if (ny == 0 && nx == 0)
                    countMap();

                y = ny;
                x = nx;

            }

            if (count % 2 == 0) {
                cycle++;
            }

            dir++;
            if (dir == 4) {
                dir = 0;
            }
            queue.add(new Integer[]{y, x, dir, cycle});
        }


    }

    public static void spreadDirt(int y, int x, int dir) {
        int save = map[y][x];

        for (int i = 0; i < directionX[0].length; i++) {
            int ny = y + directionY[dir][i];
            int nx = x + directionX[dir][i];
            double per = percent[i];
            int dirt = (int) (per * save);
            map[y][x] -= dirt;

            if (ny < 0 || nx < 0 || ny >= map.length || nx >= map.length) {
                continue;
            }

            if (i == 9) {
                map[ny][nx] += map[y][x];
            } else {
                map[ny][nx] += dirt;
            }

        }
        map[y][x] = 0;
    }

}
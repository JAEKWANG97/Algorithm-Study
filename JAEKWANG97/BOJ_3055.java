import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_3055 {

    static class Location {
        int r;
        int c;
        int dist;

        public Location(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + r;
            result = prime * result + c;
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            Location other = (Location) obj;
            if (r != other.r)
                return false;
            if (c != other.c)
                return false;
            return true;
        }

    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int R;
    static int C;

    static char[][] map;

    static Location target;
    static Location start;

    static Queue<Location> water;

    static int minDist;

    static int[] dr = { -1, 0, 1, 0 };
    static int[] dc = { 0, 1, 0, -1 };

    public static void main(String[] args) throws IOException {
        init();
        escape();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        water = new ArrayDeque<>();

        minDist = Integer.MAX_VALUE;

        for (int i = 0; i < R; i++) {
            String input = br.readLine();
            for (int j = 0; j < C; j++) {
                char item = input.charAt(j);
                map[i][j] = item;

                if (item == 'D') {
                    target = new Location(i, j, 0);
                } else if (item == 'S') {
                    start = new Location(i, j, 0);
                } else if (item == '*') {
                    water.add(new Location(i, j, 0));
                }
            }
        }
    }

    private static void escape() {
        Queue<Location> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[R][C];

        que.add(start);
        visited[start.r][start.c] = true;

        while (!que.isEmpty()) {
            int size = que.size();
            waterMove();
            while (size-- > 0) {
                Location cur = que.poll();

                if (map[cur.r][cur.c] == 'D') {
                    System.out.println(cur.dist);
                    return;
                }

                for (int i = 0; i < 4; i++) {
                    int nr = cur.r + dr[i];
                    int nc = cur.c + dc[i];

                    if (nr < 0 || nc < 0 || nr >= R || nc >= C) {
                        continue;
                    }

                    if (visited[nr][nc]) {
                        continue;
                    }

                    if (map[nr][nc] == '*' || map[nr][nc] == 'X' || map[nr][nc] == 'S') {
                        continue;
                    }

                    que.add(new Location(nr, nc, cur.dist + 1));
                    visited[nr][nc] = true;
                }
            }
        }

        System.out.println("KAKTUS");

    }

    private static void waterMove() {
        Queue<Location> nextWater = new ArrayDeque<>();

        while (!water.isEmpty()) {
            Location cur = water.poll();
            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= R || nc >= C)
                    continue;

                if (map[nr][nc] == '*' || map[nr][nc] == 'X' || map[nr][nc] == 'D')
                    continue;
                map[nr][nc] = '*';
                nextWater.add(new Location(nr, nc, cur.dist + 1));
            }
        }

        water = nextWater;
    }

}

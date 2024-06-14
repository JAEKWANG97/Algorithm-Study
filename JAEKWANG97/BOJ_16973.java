package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

class BOJ_16973 {
    static class Location {
        int r;
        int c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public String toString() {
            return "Location [r=" + r + ", c=" + c + "]";
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M, H, W;
    private static int[][] map;
    private static boolean[][] visited;
    private static Location start;
    private static Location end;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        start = new Location(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
        end = new Location(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);

    }

    private static void bfs() {
        Queue<Location> queue = new ArrayDeque<>();
        queue.offer(start);
        visited[start.r][start.c] = true;

        int[] dr = { -1, 0, 1, 0 };
        int[] dc = { 0, 1, 0, -1 };
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int s = 0; s < size; s++) {
                Location cur = queue.poll();

                if (cur.r == end.r && cur.c == end.c) {
                    System.out.println(count);
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = cur.r + dr[d];
                    int nc = cur.c + dc[d];
                    if (isIn(nr, nc) && !visited[nr][nc] && checkMove(cur.r, cur.c, nr, nc, dr[d], dc[d])) {
                        queue.offer(new Location(nr, nc));
                        visited[nr][nc] = true;
                    }
                }
            }
            count++;
        }
        System.out.println(-1);
    }

    private static boolean isIn(int r, int c) {
        return r >= 0 && c >= 0 && r + H <= N && c + W <= M;
    }

    private static boolean checkMove(int oldR, int oldC, int newR, int newC, int dr, int dc) {
        if (dr == -1) {
            for (int j = 0; j < W; j++) {
                if (map[newR][newC + j] == 1)
                    return false;
            }
        } else if (dr == 1) {
            for (int j = 0; j < W; j++) {
                if (map[newR + H - 1][newC + j] == 1)
                    return false;
            }
        } else if (dc == -1) {
            for (int i = 0; i < H; i++) {
                if (map[newR + i][newC] == 1)
                    return false;
            }
        } else if (dc == 1) {
            for (int i = 0; i < H; i++) {
                if (map[newR + i][newC + W - 1] == 1)
                    return false;
            }
        }
        return true;
    }
}

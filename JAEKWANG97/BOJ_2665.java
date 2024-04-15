import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BOJ_2665 {
    static class Location implements Comparable<Location> {
        int r;
        int c;
        int count;

        public Location(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }

        @Override
        public int compareTo(Location o) {
            return this.count - o.count;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int N;

    static int[][] map;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            String input = br.readLine();
            for (int c = 0; c < N; c++) {
                map[r][c] = input.charAt(c) - '0';
            }
        }
    }

    private static void bfs() {
        PriorityQueue<Location> pq = new PriorityQueue<>();
        Location start = new Location(0, 0, 0);
        boolean[][] visited = new boolean[N][N];
        pq.add(start);
        int[] dr = {0, 1, 0, -1};
        int[] dc = {1, 0, -1, 0};
        while (!pq.isEmpty()) {
            Location cur = pq.poll();

            if (cur.r == N - 1 && cur.c == N - 1) {
                System.out.println(cur.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nr = cur.r + dr[i];
                int nc = cur.c + dc[i];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || visited[nr][nc]) {
                    continue;
                }
                visited[nr][nc] = true;
                if (map[nr][nc] == 1) {
                    pq.add(new Location(nr, nc, cur.count));

                } else {
                    pq.add(new Location(nr, nc, cur.count + 1));
                }
            }
        }

    }

}

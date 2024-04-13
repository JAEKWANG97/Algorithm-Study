import java.io.*;
import java.util.PriorityQueue;


public class BOJ_2665 {
    static class Pos implements Comparable<Pos> {
        int x, y, cost;

        public Pos(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pos o) {
            return this.cost - o.cost;
        }
    }

    private static final int INF = 100_000_000;
    static int N;
    static int[][] map, distance;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};
    static PriorityQueue<Pos> pq;

    static void helper() {
        pq = new PriorityQueue<>();
        distance[0][0] = 0;
        pq.add(new Pos(0, 0, 0));

        while (!pq.isEmpty()) {
            Pos cur = pq.poll();

            if (distance[cur.x][cur.y] < cur.cost) {
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                int nc = cur.cost;
                if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1) {
                    continue;
                }
                nc += map[nx][ny] == 0 ? 1 : 0;

                if (nc < distance[nx][ny]) {
                    distance[nx][ny] = nc;
                    pq.add(new Pos(nx, ny, nc));
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        distance = new int[N][N];

        for (int i = 0; i < N; i++) {
            String row = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = row.charAt(j) - '0';
                distance[i][j] = INF;
            }
        }

        helper();
        bw.write(String.valueOf(distance[N - 1][N - 1]));

        br.close();
        bw.close();
    }
}
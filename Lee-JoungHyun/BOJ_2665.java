import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;

public class BOJ_2665 {

    private static class Poz implements Comparable<Poz>{
        int y, x, cost;

        public Poz(int y, int x, int cost) {
            this.y = y;
            this.x = x;
            this.cost = cost;
        }

        @Override
        public int compareTo(Poz o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        int[][] costs = new int[N][N];
        for (int y = 0; y < N; y++) {
            map[y] = br.readLine().toCharArray();
            Arrays.fill(costs[y], Integer.MAX_VALUE);
        }

        PriorityQueue<Poz> pq = new PriorityQueue<>();


        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        costs[0][0] = 0;

        pq.add(new Poz(0, 1, '1' - map[0][1]));
        pq.add(new Poz(1, 0, '1' - map[1][0]));

        int ans = 0;
        while (!pq.isEmpty()) {
            Poz now = pq.poll();
            if (now.y ==N - 1 && now.x == N - 1) {
                ans = now.cost;
                break;
            }
            costs[now.y][now.x] = now.cost;
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (0 <= ny && ny < N && 0 <= nx && nx < N && costs[ny][nx] > now.cost + '1' - map[ny][nx]) {
                    pq.add(new Poz(ny, nx, now.cost + '1' - map[ny][nx]));
                }
            }
        }

        System.out.println(ans);
    }
}

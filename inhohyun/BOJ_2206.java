import java.util.*;

public class BOJ_2206 {
    static class Pair {
        int x, y, z;

        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] graph = new int[n][m];
        int[][][] v = new int[n][m][2];

        v[0][0][0] = 1;

        for (int i = 0; i < n; i++) {
            String line = scanner.next();
            for (int j = 0; j < m; j++) {
                graph[i][j] = line.charAt(j) - '0';
            }
        }

        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        System.out.println(bfs(graph, v, dx, dy, n, m));
    }

    static int bfs(int[][] graph, int[][][] v, int[] dx, int[] dy, int n, int m) {
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 0));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            int a = p.x;
            int b = p.y;
            int c = p.z;

            if (a == n - 1 && b == m - 1) {
                return v[a][b][c];
            }

            for (int i = 0; i < 4; i++) {
                int nx = a + dx[i];
                int ny = b + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (graph[nx][ny] == 1 && c == 0) {
                    v[nx][ny][1] = v[a][b][0] + 1;
                    q.add(new Pair(nx, ny, 1));
                } else if (graph[nx][ny] == 0 && v[nx][ny][c] == 0) {
                    v[nx][ny][c] = v[a][b][c] + 1;
                    q.add(new Pair(nx, ny, c));
                }
            }
        }
        return -1;
    }
}

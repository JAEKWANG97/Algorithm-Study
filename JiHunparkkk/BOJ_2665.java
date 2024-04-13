import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

class Main {

    static int n;
    static int[][] board;
    static int[][] visited;
    static int[][] blocked;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        board = new int[n][n];
        visited = new int[n][n];
        blocked = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                blocked[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i = 0; i < n; i++) {
            String input = br.readLine();
            for (int j = 0; j < n; j++) {
                board[i][j] = input.charAt(j) - '0';
            }
        }

        solution();
        System.out.println(blocked[n - 1][n - 1]);
    }

    static void solution() {
        PriorityQueue<Point> pq = new PriorityQueue<>(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                return blocked[o1.x][o1.y] - blocked[o2.x][o2.y];
            }
        });
        pq.offer(new Point(0, 0));
        blocked[0][0] = 0;

        while (!pq.isEmpty()) {
            Point poll = pq.poll();
            int x = poll.x;
            int y = poll.y;
            int blockCount = blocked[x][y];

            visited[x][y] = 1;

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && ny >= 0 && nx < n && ny < n && visited[nx][ny] == 0) {
                    if (board[nx][ny] == 0) {
                        blocked[nx][ny] = Math.min(blocked[nx][ny], blockCount + 1);
                    } else {
                        blocked[nx][ny] = blockCount;
                    }
                    pq.offer(new Point(nx, ny));
                }
            }
        }
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

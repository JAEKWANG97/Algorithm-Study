import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static int n, m;
    private static int[][] board;
    private static int[] dx = {-1, 0, 1, 0};
    private static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        Queue<Point> queue = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    queue.add(new Point(i, j));
                }
            }
        }

        int answer = melting(queue);
        System.out.println(answer);
    }

    private static int melting(Queue<Point> queue) {
        int year = 0;

        while (!queue.isEmpty()) {
            boolean[][] visited = new boolean[n][m];
            int size = queue.size();

            //종료 조건
            if (getLumpCnt(queue) >= 2) {
                return year;
            }

            //년도 지남
            for (int i = 0; i < size; i++) {
                Point poll = queue.poll();
                visited[poll.x][poll.y] = true;

                for (int j = 0; j < 4; j++) {
                    int nx = dx[j] + poll.x;
                    int ny = dy[j] + poll.y;

                    if (isIn(nx, ny) && board[nx][ny] == 0 && !visited[nx][ny]) {
                        board[poll.x][poll.y] -= 1;
                    }
                }
                if (board[poll.x][poll.y] > 0) {
                    queue.add(new Point(poll.x, poll.y));
                } else {
                    board[poll.x][poll.y] = 0;
                }
            }
            year++;
        }

        return 0;
    }

    private static int getLumpCnt(Queue<Point> queue) {
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;
        for (Point point : queue) {
            int x = point.x;
            int y = point.y;

            if (!visited[x][y]) {
                visited[x][y] = true;
                calLump(x, y, visited);
                cnt++;
            }
        }
        return cnt;
    }

    private static void calLump(int x, int y, boolean[][] visited) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (isIn(nx, ny) && !visited[nx][ny] && board[nx][ny] > 0) {
                visited[nx][ny] = true;
                calLump(nx, ny, visited);
            }
        }
    }

    private static boolean isIn(int nx, int ny) {
        return nx >= 0 && ny >= 0 && nx < n && ny < m;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    private static class Turn {
        int time;
        char dir;

        public Turn(int time, char dif) {
            this.time = time;
            this.dir = dif;
        }
    }

    private static class Tail {
        int x, y;

        public Tail(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static final int APPLE = 1;
    private static final int BODY = 2;

    private static int n, k;
    private static int[][] board;
    private static List<Turn> turn;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        board = new int[n][n];

        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            board[x - 1][y - 1] = APPLE;
        }

        int l = Integer.parseInt(br.readLine());
        turn = new ArrayList<>();
        for (int i = 0; i < l; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            turn.add(new Turn(time, dir));
        }

        int answer = solution();
        System.out.println(answer);
    }

    private static int solution() {
        int[][] dxy = {{0, 1}, {-1, 0}, {0, -1}, {1, 0}};
        Queue<Tail> queue = new ArrayDeque<>();
        int nx, ny, time, nowDir = 0;

        nx = 0;
        ny = 1;
        board[0][0] = BODY;
        queue.add(new Tail(0, 0));

        time = 1;
        while (!gameOver(nx, ny)) {
            if (board[nx][ny] != APPLE) {
                Tail tail = queue.poll();
                board[tail.x][tail.y] = 0;
            }
            board[nx][ny] = BODY;
            queue.add(new Tail(nx, ny));

            //회전 여부
            if (turn.size() > 0 && turn.get(0).time == time) {
                char dir = turn.get(0).dir;
                turn.remove(0);
                if (dir == 'L') {
                    nowDir = (nowDir + 1) % 4;
                } else {
                    nowDir = (nowDir - 1 + 4) % 4;
                }
            }
            nx += dxy[nowDir][0];
            ny += dxy[nowDir][1];
            time++;

        }

        return time;
    }

    private static boolean gameOver(int nx, int ny) {
        return nx < 0 || ny < 0 || nx >= n || ny >= n || board[nx][ny] == BODY;
    }
}


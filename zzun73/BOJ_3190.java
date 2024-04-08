import java.io.*;
import java.util.*;

public class BOJ_3190 {
    private static final int APPLE = 9;
    private static final int VISIT = 1;
    static int N, K, L;
    static int[][] board;
    static Deque<Info> infos;
    static Deque<Pos> snake;
    static int[] dx = new int[]{0, 1, 0, -1};
    static int[] dy = new int[]{1, 0, -1, 0};

    static class Info {
        int time;
        char dir;

        public Info(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int helper() {
        snake = new ArrayDeque<>();
        snake.add(new Pos(0, 0));
        board[0][0] = VISIT;

        int X = infos.peek().time;
        char C = infos.peek().dir;
        int curTime = 0;
        int curDir = 0;
        while (true) {
            curTime++;

            int nx = snake.peekLast().x + dx[curDir];
            int ny = snake.peekLast().y + dy[curDir];

            if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || board[nx][ny] == VISIT) {
                break;
            }

            if (board[nx][ny] == APPLE) {
                board[nx][ny] = VISIT;
                snake.addLast(new Pos(nx, ny));
            } else {
                board[nx][ny] = VISIT;
                snake.addLast(new Pos(nx, ny));

                Pos tail = snake.pollFirst();
                board[tail.x][tail.y] = 0;
            }

            if (curTime == X) {
                if (C == 'L') {
                    curDir = (curDir + 3) % 4;
                } else {
                    curDir = (curDir + 1) % 4;
                }

                infos.poll();
                if (!infos.isEmpty()) {
                    X = infos.peek().time;
                    C = infos.peek().dir;
                }
            }
        }

        return curTime;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        board = new int[N][N];
        K = Integer.parseInt(br.readLine());
        while (K-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            board[r][c] = APPLE;
        }

        L = Integer.parseInt(br.readLine());
        infos = new ArrayDeque<>();
        while (L-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            char c = st.nextToken().charAt(0);
            infos.add(new Info(x, c));
        }

        bw.write(String.valueOf(helper()));

        br.close();
        bw.close();
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14499 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M, K;
    private static Location start;

    private static int[][] map;
    private static int[] commands;

    private static int[][] dice;

    private static int[] dy = {0, 0, -1, 1};
    private static int[] dx = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        init();
        play();
    }

    private static void play() {
        Location current = start;

        for (int command : commands) {
            int Y = current.getY() + dy[command - 1];
            int X = current.getX() + dx[command - 1];

            if (Y < 0 || Y >= N || X < 0 || X >= M) {
                continue;
            }

            current = new Location(Y, X);
            roll(command);

            if (map[Y][X] == 0) {
                map[Y][X] = getBottom();
            } else {
                setBottom(map[Y][X]);
                map[Y][X] = 0;
            }

            System.out.println(getTop());
        }
    }

    private static void roll(int command) {
        if (command == 1) {
            rollLeft();
        } else if (command == 2) {
            rollRight();
        } else if (command == 3) {
            rollUp();
        } else if (command == 4) {
            rollDown();
        }
    }

    private static void rollLeft() {
        int temp = dice[1][2];

        dice[1][2] = dice[1][1];
        dice[1][1] = dice[1][0];
        dice[1][0] = dice[3][1];
        dice[3][1] = temp;
    }

    private static void rollRight() {
        int temp = dice[1][0];

        dice[1][0] = dice[1][1];
        dice[1][1] = dice[1][2];
        dice[1][2] = dice[3][1];
        dice[3][1] = temp;
    }

    private static void rollUp() {
        int temp = dice[0][1];

        dice[0][1] = dice[1][1];
        dice[1][1] = dice[2][1];
        dice[2][1] = dice[3][1];
        dice[3][1] = temp;
    }

    private static void rollDown() {
        int temp = dice[3][1];

        dice[3][1] = dice[2][1];
        dice[2][1] = dice[1][1];
        dice[1][1] = dice[0][1];
        dice[0][1] = temp;
    }

    private static int getTop() {
        return dice[1][1];
    }

    private static int getBottom() {
        return dice[3][1];
    }

    private static void setBottom(int num) {
        dice[3][1] = num;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        start = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        commands = new int[K];
        for (int i = 0; i < K; i++) {
            commands[i] = Integer.parseInt(st.nextToken());
        }

        dice = new int[4][3];
    }

    private static class Location {

        private int y;
        private int x;

        public Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }
    }
}

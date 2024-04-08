import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_20057 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[][] map;

    private static int[] dy = {0, 1, 0, -1};
    private static int[] dx = {-1, 0, 1, 0};

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        tornado();

        System.out.println(answer);
    }

    private static void tornado() {
        int y = N / 2;
        int x = N / 2;

        int length = 1;
        int direction = 0;

        while (!(y == 0 && x == 0)) {
            for (int len = 0; len < length; len++) {
                y += dy[direction];
                x += dx[direction];

                spreadSand(y, x, direction);

                if (y == 0 && x == 0) {
                    break;
                }
            }

            direction = (direction + 1) % 4;

            if (direction == 0 || direction == 2) {
                length++;
            }
        }
    }

    private static void spreadSand(int y, int x, int direction) {
        int dust = map[y][x];
        int sum = 0;

        //2%
        int Y = y + dy[(direction + 3) % 4] * 2;
        int X = x + dx[(direction + 3) % 4] * 2;

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust * 2 / 100;
        } else {
            map[Y][X] += dust * 2 / 100;
        }
        sum += dust * 2 / 100;

        Y = y + dy[(direction + 1) % 4] * 2;
        X = x + dx[(direction + 1) % 4] * 2;

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust * 2 / 100;
        } else {
            map[Y][X] += dust * 2 / 100;
        }
        sum += dust * 2 / 100;

        //7%
        Y = y + dy[(direction + 3) % 4];
        X = x + dx[(direction + 3) % 4];

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust * 7 / 100;
        } else {
            map[Y][X] += dust * 7 / 100;
        }
        sum += dust * 7 / 100;

        Y = y + dy[(direction + 1) % 4];
        X = x + dx[(direction + 1) % 4];

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust * 7 / 100;
        } else {
            map[Y][X] += dust * 7 / 100;
        }
        sum += dust * 7 / 100;

        //10%
        Y = y + dy[direction] + dy[(direction + 3) % 4];
        X = x + dx[direction] + dx[(direction + 3) % 4];

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust * 10 / 100;
        } else {
            map[Y][X] += dust * 10 / 100;
        }
        sum += dust * 10 / 100;

        Y = y + dy[direction] + dy[(direction + 1) % 4];
        X = x + dx[direction] + dx[(direction + 1) % 4];

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust * 10 / 100;
        } else {
            map[Y][X] += dust * 10 / 100;
        }
        sum += dust * 10 / 100;

        //1%
        Y = y + dy[(direction + 2) % 4] + dy[(direction + 3) % 4];
        X = x + dx[(direction + 2) % 4] + dx[(direction + 3) % 4];

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust / 100;
        } else {
            map[Y][X] += dust / 100;
        }
        sum += dust / 100;

        Y = y + dy[(direction + 2) % 4] + dy[(direction + 1) % 4];
        X = x + dx[(direction + 2) % 4] + dx[(direction + 1) % 4];

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust / 100;
        } else {
            map[Y][X] += dust / 100;
        }
        sum += dust / 100;

        //5%
        Y = y + dy[direction] * 2;
        X = x + dx[direction] * 2;

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust * 5 / 100;
        } else {
            map[Y][X] += dust * 5 / 100;
        }
        sum += dust * 5 / 100;

        Y = y + dy[direction];
        X = x + dx[direction];

        if (Y < 0 || Y >= N || X < 0 || X >= N) {
            answer += dust - sum;
        } else {
            map[Y][X] += dust - sum;
        }

        map[y][x] = 0;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
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

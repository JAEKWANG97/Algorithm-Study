import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17144 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R, C, T;
    private static int[][] map;

    private static Location up;
    private static Location down;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();

        for (int t = 0; t < T; t++) {
            spreadDust();
            purifyAir();
        }

        printAnswer();
    }

    private static void spreadDust() {
        int[][] dusts = new int[R][C];

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (map[y][x] == 0) {
                    continue;
                }

                int dust = map[y][x] / 5;

                for (int i = 0; i < 4; i++) {
                    int Y = y + dy[i];
                    int X = x + dx[i];

                    if (Y < 0 || Y >= R || X < 0 || X >= C || map[Y][X] == -1) {
                        continue;
                    }

                    dusts[Y][X] += dust;
                    map[y][x] -= dust;
                }
            }
        }

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                map[y][x] = map[y][x] + dusts[y][x];
            }
        }
    }

    private static void purifyAir() {
        for (int y = up.getY() - 1; y >= 1; y--) {
            map[y][0] = map[y - 1][0];
        }

        for (int x = 0; x < C - 1; x++) {
            map[0][x] = map[0][x + 1];
        }

        for (int y = 0; y <= up.getY() - 1; y++) {
            map[y][C - 1] = map[y + 1][C - 1];
        }

        for (int x = C - 1; x >= 2; x--) {
            map[up.getY()][x] = map[up.getY()][x - 1];
        }

        map[up.getY()][1] = 0;

        for (int y = down.getY() + 1; y < R - 1; y++) {
            map[y][0] = map[y + 1][0];
        }

        for (int x = 0; x < C - 1; x++) {
            map[R - 1][x] = map[R - 1][x + 1];
        }

        for (int y = R - 1; y >= down.getY() + 1; y--) {
            map[y][C - 1] = map[y - 1][C - 1];
        }

        for (int x = C - 1; x >= 2; x--) {
            map[down.getY()][x] = map[down.getY()][x - 1];
        }

        map[down.getY()][1] = 0;
    }


    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        for (int y = 0; y < R; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < C; x++) {
                int input = Integer.parseInt(st.nextToken());

                if (input == -1) {
                    if (up == null) {
                        up = new Location(y, x);
                    } else {
                        down = new Location(y, x);
                    }
                }

                map[y][x] = input;
            }
        }
    }

    private static void printAnswer() {
        int sum = 0;

        for (int y = 0; y < R; y++) {
            for (int x = 0; x < C; x++) {
                if (map[y][x] == -1) {
                    continue;
                }

                sum += map[y][x];
            }
        }

        System.out.println(sum);
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

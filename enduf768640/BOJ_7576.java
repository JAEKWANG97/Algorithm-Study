import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_7576 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static int[][] map;

    private static List<Location> ripeTomatoes = new ArrayList<>();

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        printAnswer();
    }

    private static void bfs() {
        Queue<Location> queue = new LinkedList<>(ripeTomatoes);
        while (!queue.isEmpty()) {
            Location tomato = queue.poll();

            for (int i = 0; i < 4; i++) {
                int Y = tomato.getY() + dy[i];
                int X = tomato.getX() + dx[i];

                if (Y < 0 || Y >= N || X < 0 || X >= M || map[Y][X] != 0) {
                    continue;
                }

                queue.offer(new Location(Y, X));
                map[Y][X] = map[tomato.getY()][tomato.getX()] + 1;
            }
        }
    }

    private static int getAnswer() {
        int day = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0) {
                    throw new IllegalStateException();
                }

                day = Math.max(day, map[y][x]);
            }
        }

        return day - 1;
    }

    private static void printAnswer() {
        try {
            System.out.println(getAnswer());
        } catch (IllegalStateException e) {
            System.out.println(-1);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                int tomato = Integer.parseInt(st.nextToken());

                if (tomato == 1) {
                    ripeTomatoes.add(new Location(y, x));
                }

                map[y][x] = tomato;
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

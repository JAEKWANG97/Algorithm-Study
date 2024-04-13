import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;

    private static int[][] map;
    private static int[][] visited1, visited2;

    private static Location start;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        printAnswer();
    }

    private static void bfs() {
        Queue<Location> queue = new LinkedList<>();

        queue.offer(start);
        visited1[start.getY()][start.getX()] = 1;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            int y = location.getY();
            int x = location.getX();

            for (int i = 0; i < 4; i++) {
                int Y = y + dy[i];
                int X = x + dx[i];

                if (Y < 0 || Y >= N || X < 0 || X >= M || visited1[Y][X] != 0) {
                    continue;
                }

                if (map[Y][X] == 1) {
                    if (location.canBreak()) {
                        queue.offer(new Location(Y, X, false));
                        visited2[Y][X] = visited1[y][x] + 1;
                    }
                } else {
                    if (location.canBreak()) {
                        if (visited1[Y][X] != 0) {
                            continue;
                        }

                        queue.offer(new Location(Y, X, true));
                        visited1[Y][X] = visited1[y][x] + 1;
                    } else {
                        if (visited2[Y][X] != 0) {
                            continue;
                        }

                        queue.offer(new Location(Y, X, false));
                        visited2[Y][X] = visited2[y][x] + 1;
                    }
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            map[y] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        visited1 = new int[N][M];
        visited2 = new int[N][M];

        start = new Location(0, 0, true);
    }

    private static void printAnswer() {
        int answer1 = visited1[N - 1][M - 1];
        int answer2 = visited2[N - 1][M - 1];

        if (answer1 == 0 && answer2 == 0) {
            System.out.println(-1);
            return;
        }

        if (answer1 == 0) {
            System.out.println(answer2);
            return;
        }

        if (answer2 == 0) {
            System.out.println(answer1);
            return;
        }

        System.out.println(Math.min(answer1, answer2));
    }

    private static class Location {

        private int y;
        private int x;
        private boolean canBreak;

        public Location(int y, int x, boolean canBreak) {
            this.y = y;
            this.x = x;
            this.canBreak = canBreak;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public boolean canBreak() {
            return canBreak;
        }
    }
}

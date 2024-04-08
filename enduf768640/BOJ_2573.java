import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] map;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int yearCount;

    public static void main(String[] args) throws IOException {
        init();

        while (countIce() == 1) {
            meltIce();
            yearCount++;

            if (countIce() == 0) {
                System.out.println(0);
                return;
            }
        }

        printAnswer();
    }

    private static void meltIce() {
        Queue<Location> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int[][] meltingLevel = new int[N][M];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] != 0 || visited[y][x]) {
                    continue;
                }

                queue.offer(new Location(y, x));
                visited[y][x] = true;

                while (!queue.isEmpty()) {
                    Location location = queue.poll();

                    for (int i = 0; i < 4; i++) {
                        int Y = location.getY() + dy[i];
                        int X = location.getX() + dx[i];

                        if (Y < 0 || Y >= N || X < 0 || X >= M || visited[Y][X]) {
                            continue;
                        }

                        if (map[location.getY()][location.getX()] == 0 && map[Y][X] != 0) {
                            meltingLevel[Y][X]++;
                            continue;
                        }

                        queue.offer(new Location(Y, X));
                        visited[Y][X] = true;
                    }
                }
            }
        }

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0) {
                    continue;
                }

                if (map[y][x] - meltingLevel[y][x] < 0) {
                    map[y][x] = 0;
                    continue;
                }

                map[y][x] -= meltingLevel[y][x];
            }
        }
    }

    private static int countIce() {
        int count = 0;

        Queue<Location> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0 || visited[y][x]) {
                    continue;
                }

                queue.offer(new Location(y, x));

                while (!queue.isEmpty()) {
                    Location location = queue.poll();

                    for (int i = 0; i < 4; i++) {
                        int Y = location.getY() + dy[i];
                        int X = location.getX() + dx[i];

                        if (Y < 0 || Y >= N || X < 0 || X >= M || visited[Y][X] || map[Y][X] == 0) {
                            continue;
                        }

                        queue.offer(new Location(Y, X));
                        visited[Y][X] = true;
                    }
                }

                count++;
            }
        }

        return count;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            map[y] = Arrays.stream(br.readLine().split("\\s"))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }
    }

    private static void printAnswer() {
        System.out.println(yearCount);
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

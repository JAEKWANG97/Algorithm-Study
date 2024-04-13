import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int L;
    private static int W;

    private static char[][] map;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        searchTreasure();
        printAnswer();
    }

    private static void searchTreasure() {
        for (int y = 0; y < L; y++) {
            for (int x = 0; x < W; x++) {
                if (map[y][x] == 'W') {
                    continue;
                }

                bfs(new Location(y, x));
            }
        }
    }

    private static void bfs(Location start) {
        Queue<Location> queue = new LinkedList<>();
        boolean[][] visited = new boolean[L][W];
        int[][] depth = new int[L][W];

        int y = start.getY();
        int x = start.getX();

        queue.offer(start);
        visited[y][x] = true;
        depth[y][x] = 0;

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            for (int i = 0; i < 4; i++) {
                int Y = location.getY() + dy[i];
                int X = location.getX() + dx[i];

                if (Y < 0 || Y >= L || X < 0 || X >= W || visited[Y][X] || map[Y][X] == 'W') {
                    continue;
                }

                queue.offer(new Location(Y, X));
                visited[Y][X] = true;
                depth[Y][X] = depth[location.getY()][location.getX()] + 1;
            }
        }

        answer = Math.max(answer, getMaxDepth(depth));
    }

    private static int getMaxDepth(int[][] depth) {
        return Arrays.stream(depth)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());

        map = new char[L][W];
        for (int y = 0; y < L; y++) {
            char[] input = br.readLine().toCharArray();
            for (int x = 0; x < W; x++) {
                map[y][x] = input[x];
            }
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
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

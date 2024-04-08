import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, L, R;
    private static int[][] map;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int day;

    public static void main(String[] args) throws IOException {
        init();

        while (setUnionAndSetPopulation()) {
            day++;
        }

        printAnswer();
    }

    private static void printAnswer() {
        System.out.println(day);
    }

    private static boolean setUnionAndSetPopulation() {
        int union = 1;

        Queue<Location> queue = new ArrayDeque<>();
        int[][] unionMap = new int[N][N];
        boolean[][] check = new boolean[N][N];

        Queue<Location> temp = new ArrayDeque<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (check[y][x]) {
                    continue;
                }

                queue.offer(new Location(y, x));
                temp.offer(new Location(y, x));
                unionMap[y][x] = union;
                check[y][x] = true;

                while (!queue.isEmpty()) {
                    Location location = queue.poll();

                    for (int i = 0; i < 4; i++) {
                        int Y = location.getY() + dy[i];
                        int X = location.getX() + dx[i];

                        if (Y < 0 || Y >= N || X < 0 || X >= N || unionMap[Y][X] != 0) {
                            continue;
                        }

                        int diff = Math.abs(map[Y][X] - map[location.getY()][location.getX()]);

                        if (diff >= L && diff <= R) {
                            queue.offer(new Location(Y, X));
                            temp.offer(new Location(Y, X));
                            unionMap[Y][X] = union;
                            check[Y][X] = true;
                        }
                    }
                }

                setPopulation(temp);

                union++;
            }
        }

        if (union == (N * N) + 1) {
            return false;
        }

        return true;
    }

    private static void setPopulation(Queue<Location> temp) {
        int population = (int) temp.stream()
                .mapToInt(location -> map[location.getY()][location.getX()])
                .average()
                .getAsDouble();

        while (!temp.isEmpty()) {
            Location location = temp.poll();
            map[location.getY()][location.getX()] = population;
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

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

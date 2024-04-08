import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class SEA_1949 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T;
    private static int N;
    private static int K;

    private static int[][] map;
    
    private static List<Location> maxHeights;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer;
    
    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            init();
            executeSimulations();
            printAnswer(t);
        }
    }

    private static void executeSimulations() {
        for (Location maxHeight : maxHeights) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (y == maxHeight.getY() && x == maxHeight.getX()) {
                        continue;
                    }

                    for (int k = 0; k <= K; k++) {
                        dfs(createSimulationMap(y, x, k), maxHeight, 1);
                    }
                }
            }
        }
    }

    private static int[][] createSimulationMap(int y, int x, int k) {
        int[][] simulationMap = new int[N][N];

        for (int row = 0; row < N; row++) {
            simulationMap[row] = map[row].clone();
        }

        simulationMap[y][x] -= k;

        return simulationMap;
    }

    private static void dfs(int[][] map, Location location, int depth) {
        int y = location.getY();
        int x = location.getX();

        for (int i = 0; i < 4; i++) {
            int Y = y + dy[i];
            int X = x + dx[i];

            if (Y < 0 || Y >= N || X < 0 || X >= N) {
                continue;
            }

            if (map[Y][X] >= map[y][x]) {
                answer = Math.max(answer, depth);
                continue;
            }

            dfs(map, new Location(Y, X), depth + 1);
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        int maxHeight = Arrays.stream(map)
                .flatMapToInt(Arrays::stream)
                .max()
                .getAsInt();

        maxHeights = new ArrayList<>();
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (map[y][x] == maxHeight) {
                    maxHeights.add(new Location(y, x));
                }
            }
        }

        answer = 0;
    }

    private static void printAnswer(int t) {
        System.out.println("#" + t + " " + answer);
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

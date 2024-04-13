import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21608 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;

    private static int[][] map;
    private static int[] order;

    private static List<Integer>[] preferences;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        arrangeSeats();

        countScore();

        printAnswer();
    }

    private static void arrangeSeats() {
        for (int o = 1; o < order.length; o++) {
            int id = order[o];

            int maxEmptyCount = -1;
            int maxPreferCount = -1;
            Location location = new Location(0, 0);

            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    int emptyCount = 0;
                    int preferCount = 0;

                    if (map[y][x] != 0) {
                        continue;
                    }

                    for (int i = 0; i < 4; i++) {
                        int Y = y + dy[i];
                        int X = x + dx[i];

                        if (Y < 0 || Y >= N || X < 0 || X >= N) {
                            continue;
                        }

                        if (map[Y][X] == 0) {
                            emptyCount++;
                        }

                        if (preferences[id].contains(map[Y][X])) {
                            preferCount++;
                        }
                    }

                    if (maxPreferCount < preferCount) {
                        maxPreferCount = preferCount;
                        maxEmptyCount = emptyCount;
                        location = new Location(y, x);
                        continue;
                    }

                    if (maxPreferCount == preferCount && maxEmptyCount < emptyCount) {
                        maxEmptyCount = emptyCount;
                        location = new Location(y, x);
                    }
                }
            }

            map[location.getY()][location.getX()] = id;
        }
    }

    private static void countScore() {
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                int id = map[y][x];
                int count = 0;

                for (int i = 0; i < 4; i++) {
                    int Y = y + dy[i];
                    int X = x + dx[i];

                    if (Y < 0 || Y >= N || X < 0 || X >= N) {
                        continue;
                    }

                    if (preferences[id].contains(map[Y][X])) {
                        count++;
                    }
                }

                answer += (int) Math.pow(10, count - 1);
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        order = new int[N * N];

        preferences = new List[N * N + 1];
        for (int i = 1; i <= N * N; i++) {
            preferences[i] = new ArrayList<>();
        }

        for (int i = 0; i < N * N; i++) {
            st = new StringTokenizer(br.readLine());

            int id = Integer.parseInt(st.nextToken());
            order[i] = id;

            for (int j = 0; j < 4; j++) {
                preferences[id].add(Integer.parseInt(st.nextToken()));
            }
        }

        Location first = new Location(1, 1);
        map[first.getY()][first.getX()] = order[0];
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

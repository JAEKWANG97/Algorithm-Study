import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11660 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] map;
    private static Range[] ranges;

    private static int[][] dp;

    private static StringBuilder answer;

    public static void main(String[] args) throws IOException {
        init();

        makeDPTable();

        calculate();

        printAnswer();
    }

    private static void calculate() {
        for (int i = 0; i < M; i++) {
            calculate(ranges[i].getStart(), ranges[i].getEnd());
        }
    }

    private static void calculate(Location start, Location end) {
        int endSum = dp[end.getY()][end.getX()];
        int startSum = dp[start.getY() - 1][start.getX() - 1];

        int range1 = dp[end.getY()][start.getX() - 1];
        int range2 = dp[start.getY() - 1][end.getX()];

        int result = endSum - (range1 + range2 - startSum);

        answer.append(result).append("\n");
    }

    private static void makeDPTable() {
        dp[1][1] = map[1][1];
        for (int x = 2; x <= N; x++) {
            dp[1][x] = dp[1][x - 1] + map[1][x];
        }

        for (int y = 2; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                int sum = 0;
                for (int i = 1; i <= x; i++) {
                    sum += map[y][i];
                }

                dp[y][x] = dp[y - 1][x] + sum;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N + 1][N + 1];
        for (int x = 1; x <= N; x++) {
            st = new StringTokenizer(br.readLine());
            for (int y = 1; y <= N; y++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        ranges = new Range[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            ranges[i] = new Range(new Location(y1, x1), new Location(y2, x2));
        }

        dp = new int[N + 1][N + 1];

        answer = new StringBuilder();
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Range {

        private Location start;
        private Location end;

        public Range(Location start, Location end) {
            this.start = start;
            this.end = end;
        }

        public Location getStart() {
            return start;
        }

        public Location getEnd() {
            return end;
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

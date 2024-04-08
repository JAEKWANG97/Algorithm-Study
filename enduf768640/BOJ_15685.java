import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Stack;
import java.util.StringTokenizer;

public class BOJ_15685 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static Curve[] curves;

    private static boolean[][] map;

    private static int[] dx = {1, 0, -1, 0};
    private static int[] dy = {0, -1, 0, 1};

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        drawCurve();

        countSquare();

        printAnswer();
    }

    private static void countSquare() {
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1]) {
                    answer++;
                }
            }
        }
    }

    private static void drawCurve() {
        Arrays.stream(curves).forEach(Curve::drawCurve);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        curves = new Curve[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());
            int generation = Integer.parseInt(st.nextToken());

            curves[i] = new Curve(new Location(x, y), direction, generation);
        }

        map = new boolean[101][101];
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Curve {

        private LinkedList<Location> locations;
        private LinkedList<Integer> directions;

        private int generation;

        public Curve(Location location, int direction, int generation) {
            this.locations = new LinkedList<>();
            this.locations.add(location);
            this.locations.add(new Location(location.getX() + dx[direction], location.getY() + dy[direction]));

            this.directions = new LinkedList<>();
            this.directions.add(direction);

            this.generation = generation;
        }

        public void drawCurve() {
            locations.forEach(location -> map[location.getX()][location.getY()] = true);

            Stack<Integer> stack = new Stack<>();

            for (int gen = 0; gen < generation; gen++) {
                for (int i = 0; i < directions.size(); i++) {
                    stack.push(directions.get(i));
                }

                while (!stack.isEmpty()) {
                    Location location = locations.getLast();
                    int direction = (stack.pop() + 1) % 4;

                    int X = location.getX() + dx[direction];
                    int Y = location.getY() + dy[direction];

                    locations.add(new Location(X, Y));
                    directions.add(direction);

                    map[X][Y] = true;
                }
            }
        }
    }

    private static class Location {

        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_4179 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R, C;
    private static char[][] map;

    private static Set<Location> moveLocations;
    private static Set<Location> fireLocations;

    private static boolean[][] moveVisited;
    private static boolean[][] fireVisited;

    private static int[][] moveCount;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer;

    private static boolean possibleFlag;
    private static boolean impossibleFlag;

    public static void main(String[] args) throws IOException {
        init();
        escape();
        printAnswer();
    }

    private static void escape() {
        while (true) {
            fire();
            move();
            if (possibleFlag || impossibleFlag) {
                break;
            }
        }
    }

    private static void move() {
        Queue<Location> queue = new LinkedList<>(moveLocations);
        moveLocations.forEach(move -> moveVisited[move.getY()][move.getX()] = true);

        Set<Location> newMoveLocations = new HashSet<>();

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Location location = queue.poll();

            for (int d = 0; d < 4; d++) {
                int Y = location.getY() + dy[d];
                int X = location.getX() + dx[d];

                if (Y < 0 || Y >= R || X < 0 || X >= C) {
                    answer = moveCount[location.getY()][location.getX()] + 1;
                    possibleFlag = true;
                    return;
                }

                if (moveVisited[Y][X] || map[Y][X] == '#' || map[Y][X] == 'F') {
                    continue;
                }

                newMoveLocations.add(new Location(Y, X));
                map[Y][X] = 'J';
                moveVisited[Y][X] = true;
                moveCount[Y][X] = moveCount[location.getY()][location.getX()] + 1;
            }
        }

        if (newMoveLocations.isEmpty()) {
            impossibleFlag = true;
            return;
        }

        moveLocations = newMoveLocations;
    }

    private static void fire() {
        Queue<Location> queue = new LinkedList<>(fireLocations);
        fireLocations.forEach(fire -> fireVisited[fire.getY()][fire.getX()] = true);

        Set<Location> newFireLocations = new HashSet<>();

        int size = queue.size();
        for (int i = 0; i < size; i++) {
            Location location = queue.poll();

            for (int d = 0; d < 4; d++) {
                int Y = location.getY() + dy[d];
                int X = location.getX() + dx[d];

                if (Y < 0 || Y >= R || X < 0 || X >= C || fireVisited[Y][X] || map[Y][X] == '#') {
                    continue;
                }

                if (map[Y][X] != '#') {
                    map[Y][X] = 'F';
                    newFireLocations.add(new Location(Y, X));
                    fireVisited[Y][X] = true;
                }
            }
        }

        fireLocations = newFireLocations;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        moveLocations = new HashSet<>();
        fireLocations = new HashSet<>();

        map = new char[R][C];
        for (int y = 0; y < R; y++) {
            char[] input = br.readLine().toCharArray();
            for (int x = 0; x < C; x++) {
                if (input[x] == 'J') {
                    moveLocations.add(new Location(y, x));
                }

                if (input[x] == 'F') {
                    fireLocations.add(new Location(y, x));
                }

                map[y][x] = input[x];
            }
        }

        moveVisited = new boolean[R][C];
        fireVisited = new boolean[R][C];

        moveCount = new int[R][C];
    }

    private static void printAnswer() {
        if (impossibleFlag) {
            System.out.println("IMPOSSIBLE");
            return;
        }

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

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return y == location.y && x == location.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}

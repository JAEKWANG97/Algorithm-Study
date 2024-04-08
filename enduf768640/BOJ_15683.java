import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15683 {

    private final static int CAMERA_TYPE_ONE = 4;
    private final static int CAMERA_TYPE_TWO = 2;
    private final static int CAMERA_TYPE_THREE = 4;
    private final static int CAMERA_TYPE_FOUR = 4;
    private final static int CAMERA_TYPE_FIVE = 1;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] map;

    private static List<Camera> cameras;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int a;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        calculateBlindSpot(new int[cameras.size()], 0, 0);
        printAnswer();
    }

    private static void calculateBlindSpot(int[] directions, int start, int count) {
        if (count == cameras.size()) {
            int[][] cloneMap = new int[N][M];
            for (int y = 0; y < N; y++) {
                cloneMap[y] = map[y].clone();
            }

            answer = Math.min(answer, calculateBlindSpot(cloneMap, directions));
            return;
        }

        for (int idx = start; idx < cameras.size(); idx++) {
            int type = 0;
            if (cameras.get(idx).getType() == 1) {
                type = CAMERA_TYPE_ONE;
            } else if (cameras.get(idx).getType() == 2) {
                type = CAMERA_TYPE_TWO;
            } else if (cameras.get(idx).getType() == 3) {
                type = CAMERA_TYPE_THREE;
            } else if (cameras.get(idx).getType() == 4) {
                type = CAMERA_TYPE_FOUR;
            } else if (cameras.get(idx).getType() == 5) {
                type = CAMERA_TYPE_FIVE;
            }

            for (int i = 0; i < type; i++) {
                directions[count] = i;
                calculateBlindSpot(directions, idx + 1, count + 1);
            }
        }
    }

    private static int calculateBlindSpot(int[][] map, int[] directions) {
        for (int i = 0; i < cameras.size(); i++) {
            Camera camera = cameras.get(i);

            Location location = camera.getLocation();
            int type = camera.getType();
            int direction = directions[i];

            if (type == 1) {
                check(map, location, direction);
            } else if (type == 2) {
                check(map, location, direction);
                check(map, location, direction + 2);
            } else if (type == 3) {
                check(map, location, direction);
                check(map, location, (direction + 1) % 4);
            } else if (type == 4) {
                check(map, location, (direction + 3) % 4);
                check(map, location, direction);
                check(map, location, (direction + 1) % 4);
            } else if (type == 5) {
                check(map, location, direction);
                check(map, location, direction + 1);
                check(map, location, direction + 2);
                check(map, location, direction + 3);
            }
        }

        return countBlindSpot(map);
    }

    private static void check(int[][] map, Location location, int direction) {
        int y = location.getY();
        int x = location.getX();

        map[y][x] = -1;

        while (true) {
            int Y = y + dy[direction];
            int X = x + dx[direction];

            if (Y < 0 || Y >= N || X < 0 || X >= M || map[Y][X] == 6) {
                break;
            }

            map[Y][X] = -1;
            y = Y;
            x = X;
        }
    }

    private static int countBlindSpot(int[][] map) {
        int count = 0;

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < M; x++) {
                if (map[y][x] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        cameras = new ArrayList<>();

        map = new int[N][M];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < M; x++) {
                int input = Integer.parseInt(st.nextToken());

                if (input >= 1 && input <= 5) {
                    cameras.add(new Camera(new Location(y, x), input));
                    map[y][x] = 0;
                } else {
                    map[y][x] = input;
                }
            }
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Camera {

        private Location location;
        private int type;

        public Camera(Location location, int type) {
            this.location = location;
            this.type = type;
        }

        public Location getLocation() {
            return location;
        }

        public int getType() {
            return type;
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

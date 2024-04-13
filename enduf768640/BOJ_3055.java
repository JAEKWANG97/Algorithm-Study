import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int R, C;
    private static char[][] map;

    private static Queue<Location> moveQueue;
    private static boolean[][] moveVisited;
    private static int[][] distance;

    private static Queue<Location> waterQueue;
    private static boolean[][] waterVisited;

    private static Location destination;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        init();

        while (!moveQueue.isEmpty()) {
            spreadWater();
            move();
        }

        printAnswer();
    }

    private static void printAnswer() {
        if (distance[destination.getY()][destination.getX()] == 0) {
            System.out.println("KAKTUS");
        } else {
            System.out.println(distance[destination.getY()][destination.getX()]);
        }
    }

    private static void spreadWater() {
        int size = waterQueue.size();

        for (int i = 0; i < size; i++) {
            Location water = waterQueue.poll();

            for (int direction = 0; direction < 4; direction++) {
                int Y = water.getY() + dy[direction];
                int X = water.getX() + dx[direction];

                if (Y < 0 || Y >= R || X < 0 || X >= C || waterVisited[Y][X] || map[Y][X] == 'X' || map[Y][X] == 'D') {
                    continue;
                }

                waterQueue.offer(new Location(Y, X));
                waterVisited[Y][X] = true;
                map[Y][X] = '*';
            }
        }
    }

    private static void move() {
        int size = moveQueue.size();

        for (int i = 0; i < size; i++) {
            Location bieber = moveQueue.poll();

            for (int direction = 0; direction < 4; direction++) {
                int Y = bieber.getY() + dy[direction];
                int X = bieber.getX() + dx[direction];

                if (Y < 0 || Y >= R || X < 0 || X >= C || moveVisited[Y][X] || map[Y][X] == 'X' || map[Y][X] == '*') {
                    continue;
                }

                moveQueue.offer(new Location(Y, X));
                moveVisited[Y][X] = true;
                distance[Y][X] = distance[bieber.getY()][bieber.getX()] + 1;
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        moveQueue = new LinkedList<>();
        moveVisited = new boolean[R][C];
        distance = new int[R][C];

        waterQueue = new LinkedList<>();
        waterVisited = new boolean[R][C];

        map = new char[R][C];
        for (int y = 0; y < R; y++) {
            char[] input = br.readLine().toCharArray();

            for (int x = 0; x < C; x++) {
                if (input[x] == '*') {
                    waterQueue.offer(new Location(y, x));
                    waterVisited[y][x] = true;
                } else if (input[x] == 'S') {
                    moveQueue.offer(new Location(y, x));
                    moveVisited[y][x] = true;
                } else if (input[x] == 'D') {
                    destination = new Location(y, x);
                }

                map[y][x] = input[x];
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

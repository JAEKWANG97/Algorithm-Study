import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ_2665 {

    private static int N;

    private static int[][] map;

    private static Queue<Location> changeColorQueue;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        while (!canGo()) {
            changeRoomColor();
        }

        printAnswer();
    }

    private static boolean canGo() {
        boolean[][] visited = new boolean[N][N];

        Queue<Location> queue = new LinkedList<>();
        queue.offer(new Location(0, 0));
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Location location = queue.poll();
            int y = location.getY();
            int x = location.getX();

            if (y == N - 1 && x == N - 1) {
                return true;
            }

            for (int i = 0; i < 4; i++) {
                int Y = y + dy[i];
                int X = x + dx[i];

                if (Y < 0 || Y >= N || X < 0 || X >= N || visited[Y][X]) {
                    continue;
                }

                if (map[Y][X] == 0) {
                    changeColorQueue.offer(new Location(Y, X));
                    continue;
                }

                queue.offer(new Location(Y, X));
                visited[Y][X] = true;
            }
        }

        return false;
    }

    private static void changeRoomColor() {
        while (!changeColorQueue.isEmpty()) {
            Location location = changeColorQueue.poll();
            int y = location.getY();
            int x = location.getX();

            map[y][x] = 1;
        }

        answer++;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            map[y] = Arrays.stream(br.readLine().split(""))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        changeColorQueue = new LinkedList<>();
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

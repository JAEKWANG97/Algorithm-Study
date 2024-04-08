import java.io.*;
import java.util.*;

public class BOJ_3190 {

    static class Snake {
        int x;
        int y;
        int len;

        Queue<Location> tail = new ArrayDeque<>();

        int direction; // 0 --> 상 , 1--> 하 , 2 --> 좌 , 3--> 우

        public Snake(int x, int y, int len, int direction, Location tail) {
            this.x = x;
            this.y = y;
            this.len = len;
            this.direction = direction;
            this.tail.add(tail);
        }
    }

    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class SnakeMove {
        int time;
        String direction;

        public SnakeMove(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }
    }

    static BufferedReader br;
    static StringTokenizer st;

    static int N;
    static int K;
    static int L;

    static int[][] map;
    static Queue<SnakeMove> snakeMoveQueue;
    static Snake snake;

    public static void main(String[] args) throws IOException {
        init();
        simulate();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());
        map = new int[N + 1][N + 1];
        snake = new Snake(1, 1, 1, 3, new Location(1, 1));
        snakeMoveQueue = new ArrayDeque<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            map[x][y] = 1;
        }

        L = Integer.parseInt(br.readLine());
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();
            snakeMoveQueue.add(new SnakeMove(time, direction));
        }
    }

    private static void simulate() {
        boolean[][] visited = new boolean[N + 1][N + 1];
        Queue<Location> queue = new ArrayDeque<>();
        queue.add(new Location(snake.x, snake.y));
        visited[snake.x][snake.y] = true;

        int time = 0;
        SnakeMove nextMove = null;
        if (!snakeMoveQueue.isEmpty()) {
            nextMove = snakeMoveQueue.poll();
        }

        while (!queue.isEmpty()) {
            int size = queue.size();
            if (nextMove != null && time == nextMove.time) {
                if (nextMove.direction.equals("D")) {
                    turnRight();
                } else if (nextMove.direction.equals("L")) {
                    turnLeft();
                }
                if (!snakeMoveQueue.isEmpty()) {
                    nextMove = snakeMoveQueue.poll();
                }
            }
            while (size-- > 0) {
                Location cur = queue.poll();

                int d = snake.direction;

                int nx = 0;
                int ny = 0;

                if (d == 0) {
                    nx = cur.x - 1;
                    ny = cur.y;
                } else if (d == 1) {
                    nx = cur.x + 1;
                    ny = cur.y;
                } else if (d == 2) {
                    nx = cur.x;
                    ny = cur.y - 1;
                } else if (d == 3) {
                    nx = cur.x;
                    ny = cur.y + 1;
                }

                if (nx < 1 || nx > N || ny < 1 || ny > N || visited[nx][ny] || map[nx][ny] == -1) {
                    System.out.println(time + 1);
                    return;
                }

                visited[nx][ny] = true;
                snake.tail.add(new Location(nx, ny));
                if (map[nx][ny] == 0 && snake.tail.size() > 1) {
                    Location remove = snake.tail.poll();
                    visited[remove.x][remove.y] = false;
                }

                if (map[nx][ny] == 1) {
                    snake.len += 1;
                }
                map[nx][ny] = 0;
                snake.x = nx;
                snake.y = ny;
                queue.add(new Location(nx, ny));
                visited[nx][ny] = true;
            }
            time += 1;
        }

    }

    // 0 --> 상 , 1--> 하 , 2 --> 좌 , 3--> 우
    private static void turnLeft() {
        int d = snake.direction;
        if (d == 0) {
            snake.direction = 2;
        } else if (d == 1) {
            snake.direction = 3;
        } else if (d == 2) {
            snake.direction = 1;
        } else if (d == 3) {
            snake.direction = 0;
        }
    }

    private static void turnRight() {
        int d = snake.direction;
        if (d == 0) {
            snake.direction = 3;
        } else if (d == 1) {
            snake.direction = 2;
        } else if (d == 2) {
            snake.direction = 0;
        } else if (d == 3) {
            snake.direction = 1;
        }
    }
}

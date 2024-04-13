import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_20056 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M, K;
    private static Queue<FireBall>[][] map;

    private static Queue<FireBall> fireBalls;

    private static int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    private static int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws IOException {
        init();

        for (int k = 0; k < K; k++) {
            move();
        }

        printAnswer();
    }

    private static void printAnswer() {
        int sum = fireBalls.stream()
                .mapToInt(FireBall::getMass)
                .sum();

        System.out.println(sum);
    }

    private static void move() {
        while (!fireBalls.isEmpty()) {
            FireBall fireBall = fireBalls.poll();
            fireBall.move();

            map[fireBall.getY()][fireBall.getX()].offer(fireBall);
        }

        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                if (map[y][x].isEmpty()) {
                    continue;
                }

                if (map[y][x].size() == 1) {
                    fireBalls.offer(map[y][x].poll());
                    continue;
                }

                int mass = 0;
                int speed = 0;
                int count = map[y][x].size();
                int direction = 0;

                boolean allEven = map[y][x].stream()
                        .mapToInt(FireBall::getDirection)
                        .allMatch(num -> num % 2 == 0);

                boolean allOdd = map[y][x].stream()
                        .mapToInt(FireBall::getDirection)
                        .allMatch(num -> num % 2 == 1);

                if (!allEven && !allOdd) {
                    direction = 1;
                }

                while (!map[y][x].isEmpty()) {
                    FireBall temp = map[y][x].poll();
                    mass += temp.getMass();
                    speed += temp.getSpeed();
                }

                if (mass / 5 == 0) {
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    fireBalls.offer(new FireBall(y, x, mass / 5, speed / count, direction));
                    direction += 2;
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        fireBalls = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int mass = Integer.parseInt(st.nextToken());
            int speed = Integer.parseInt(st.nextToken());
            int direction = Integer.parseInt(st.nextToken());

            fireBalls.offer(new FireBall(y, x, mass, speed, direction));
        }

        map = new Queue[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            for (int x = 1; x <= N; x++) {
                map[y][x] = new ArrayDeque<>();
            }
        }
    }

    private static class FireBall {

        private int y;
        private int x;

        private int mass;
        private int speed;
        private int direction;

        public FireBall(int y, int x, int mass, int speed, int direction) {
            this.y = y;
            this.x = x;
            this.mass = mass;
            this.speed = speed;
            this.direction = direction;
        }

        private void move() {
            y = y + dy[direction] * speed;
            x = x + dx[direction] * speed;

            while (y > N) {
                y -= N;
            }

            while (y < 1) {
                y += N;
            }

            while (x > N) {
                x -= N;
            }

            while (x < 1) {
                x += N;
            }
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getMass() {
            return mass;
        }

        public int getSpeed() {
            return speed;
        }

        public int getDirection() {
            return direction;
        }
    }
}

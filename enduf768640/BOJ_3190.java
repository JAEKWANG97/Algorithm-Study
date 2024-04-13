import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_3190 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, K, L;
    private static Set<Location> apples;
    private static Queue<Move> moves;

    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        play();

        printAnswer();
    }

    private static void play() {
        Deque<Location> snake = new ArrayDeque<>();

        snake.offer(new Location(1, 1));
        int direction = 1;
        int time = 0;

        while (true) {
            time++;

            int Y = snake.peek().getY() + dy[direction];
            int X = snake.peek().getX() + dx[direction];

            if (Y < 1 || Y >= N + 1 || X < 1 || X >= N + 1) {
                break;
            }

            if (snake.contains(new Location(Y, X))) {
                break;
            }

            Location head = new Location(Y, X);
            snake.offerFirst(head);

            if (apples.contains(head)) {
                apples.remove(head);
            } else {
                snake.pollLast();
            }

            if (!moves.isEmpty() && moves.peek().getTime() == time) {
                Move move = moves.poll();

                if (move.getDirection().equals("L")) {
                    direction = (direction + 3) % 4;
                } else if (move.getDirection().equals("D")) {
                    direction = (direction + 1) % 4;
                }
            }
        }

        answer = time;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        K = Integer.parseInt(br.readLine());
        apples = new HashSet<>();
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            apples.add(new Location(y, x));
        }

        L = Integer.parseInt(br.readLine());
        moves = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            String direction = st.nextToken();

            moves.offer(new Move(time, direction));
        }
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

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Location location = (Location) o;
            return y == location.y && x == location.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }

    private static class Move {

        private int time;
        private String direction;

        public Move(int time, String direction) {
            this.time = time;
            this.direction = direction;
        }

        public int getTime() {
            return time;
        }

        public String getDirection() {
            return direction;
        }
    }
}

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class PGS_67259 {

    private static final int UP = 0;
    private static final int RIGHT = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;

    private int answer = Integer.MAX_VALUE;

    private int N;

    private int[][][] price;

    private int[] dy = {-1, 0, 1, 0};
    private int[] dx = {0, 1, 0, -1};

    private Queue<Location> queue;

    public int solution(int[][] board) {
        N = board.length;

        price = new int[N][N][4];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                Arrays.fill(price[y][x], Integer.MAX_VALUE);
            }
        }

        queue = new LinkedList<>();

        if (board[0][1] != 1) {
            price[0][1][RIGHT] = 100;
            queue.offer(new Location(0, 1, RIGHT));
        }

        if (board[1][0] != 1) {
            price[1][0][DOWN] = 100;
            queue.offer(new Location(1, 0, DOWN));
        }

        while (!queue.isEmpty()) {
            Location location = queue.poll();

            int y = location.getY();
            int x = location.getX();
            int direction = location.getDirection();

            for (int nextDirection = 0; nextDirection < 4; nextDirection++) {
                int Y = y + dy[nextDirection];
                int X = x + dx[nextDirection];

                if (Y < 0 || Y >= N || X < 0 || X >= N || board[Y][X] == 1) {
                    continue;
                }

                if (direction != nextDirection) {
                    if (price[Y][X][nextDirection] < price[y][x][direction] + 600) {
                        continue;
                    }

                    queue.offer(new Location(Y, X, nextDirection));
                    price[Y][X][nextDirection] = price[y][x][direction] + 600;
                } else {
                    if (price[Y][X][nextDirection] < price[y][x][direction] + 100) {
                        continue;
                    }

                    queue.offer(new Location(Y, X, nextDirection));
                    price[Y][X][nextDirection] = price[y][x][direction] + 100;
                }
            }

            answer = Arrays.stream(price[N - 1][N - 1])
                    .min()
                    .getAsInt();
        }

        return answer;
    }



    private static class Location {

        private int y;
        private int x;
        private int direction;

        public Location(int y, int x, int direction) {
            this.y = y;
            this.x = x;
            this.direction = direction;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        public int getDirection() {
            return direction;
        }
    }
}
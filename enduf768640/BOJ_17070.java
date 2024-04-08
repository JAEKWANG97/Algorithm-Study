import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_17070 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[][] map;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        dfs(1, 2, Direction.HORIZONTAL);
        printAnswer();
    }

    private static void dfs(int y, int x, Direction direction) {
        if (y < 1 || y >= N + 1 || x < 1 || x >= N + 1) {
            return;
        }

        if (map[y][x] == 1) {
            return;
        }

        if (direction.equals(Direction.HORIZONTAL)) {
            dfs(y, x + 1, Direction.HORIZONTAL);
            dfs(y + 1, x + 1, Direction.DIAGONAL);
        }

        if (direction.equals(Direction.VERTICAL)) {
            dfs(y + 1, x, Direction.VERTICAL);
            dfs(y + 1, x + 1, Direction.DIAGONAL);
        }

        if (direction.equals(Direction.DIAGONAL)) {
            if (map[y - 1][x] == 1 || map[y][x - 1] == 1) {
                return;
            }

            dfs(y, x + 1, Direction.HORIZONTAL);
            dfs(y + 1, x, Direction.VERTICAL);
            dfs(y + 1, x + 1, Direction.DIAGONAL);
        }

        if (y == N && x == N) {
            answer++;
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        for (int y = 1; y <= N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x <= N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private enum Direction {

        HORIZONTAL, // 가로
        VERTICAL, // 세로
        DIAGONAL // 대각선
    }
}

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13549 {

    private static final int MAX_LENGTH = 100001;

    private static Scanner sc = new Scanner(System.in);

    private static int N, K;

    private static boolean[] visited;
    private static int[] dp;

    private static int[] dx = {0, -1, 1};

    public static void main(String[] args) {
        init();

        bfs();

        printAnswer();
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(N);
        visited[N] = true;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            for (int i = 0; i < 3; i++) {
                if (i == 0) {
                    int X = 2 * x;

                    if (X < 0 || X >= MAX_LENGTH || visited[X]) {
                        continue;
                    }

                    queue.offer(X);
                    visited[X] = true;
                    dp[X] = dp[x];
                } else {
                    int X = x + dx[i];

                    if (X < 0 || X >= MAX_LENGTH || visited[X]) {
                        continue;
                    }

                    queue.offer(X);
                    visited[X] = true;
                    dp[X] = dp[x] + 1;
                }
            }
        }
    }

    private static void init() {
        N = sc.nextInt();
        K = sc.nextInt();

        visited = new boolean[MAX_LENGTH];
        dp = new int[MAX_LENGTH];
    }

    private static void printAnswer() {
        System.out.println(dp[K]);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_11060 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int[] map;

    private static boolean[] visited;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        printAnswer();
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(0);
        visited[0] = true;

        while (!queue.isEmpty()) {
            int idx = queue.poll();

            for (int jump = 1; jump <= map[idx]; jump++) {
                int nextIdx = idx + jump;

                if (nextIdx >= N || visited[nextIdx]) {
                    continue;
                }

                queue.offer(nextIdx);
                visited[nextIdx] = true;

                dp[nextIdx] = dp[idx] + 1;
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        map = Arrays.stream(br.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();

        visited = new boolean[N];
        dp = new int[N];
    }

    private static void printAnswer() {
        if (map.length == 1) {
            System.out.println(0);
            return;
        }

        if (dp[N - 1] == 0) {
            System.out.println(-1);
            return;
        }

        System.out.println(dp[N - 1]);
    }
}

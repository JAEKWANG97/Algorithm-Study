import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int K;

    private static int[] map = new int[100001];
    private static Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        queue.offer(N);
        map[N] = 1;

        while (!queue.isEmpty()) {
            int x = queue.poll();

            bfs(x - 1, x);
            bfs(x + 1, x);
            bfs(2 * x, x);
        }

        System.out.println(map[K] - 1);
    }

    private static void bfs(int idx, int x) {
        if (idx < 0 || idx >= 100001 || map[idx] != 0) {
            return;
        }

        queue.offer(idx);
        map[idx] = map[x] + 1;
    }
}

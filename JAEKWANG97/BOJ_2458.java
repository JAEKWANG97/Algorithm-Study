import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N, M;
    static int[][] map;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        solve();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int tall = Integer.parseInt(st.nextToken());
            int small = Integer.parseInt(st.nextToken());
            map[tall][small] = 1;
            map[small][tall] = -1;
        }
    }

    private static void solve() {
        int answer = 0;
        for (int i = 1; i <= N; i++) {
            int count = bfs(i, 1) + bfs(i, -1);
            if (count == N - 1) {
                answer += 1;
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int start, int flag) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        queue.offer(start);
        visited[start] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i = 1; i <= N; i++) {
                if (map[current][i] == flag && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                    count += 1;
                }
            }
        }
        return count;
    }
}

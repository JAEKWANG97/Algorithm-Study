import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n, k;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        System.out.println(bfs(n, k));
        br.close();
    }

    private static int bfs(int n, int k) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);

        int[] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[n] = 0;

        while (!queue.isEmpty()) {
            int poll = queue.poll();

            if (poll == k) {
                return visited[k];
            }

            if (check(poll + 1, poll, visited)) {
                visited[poll + 1] = visited[poll] + 1;
                queue.add(poll + 1);
            }
            if (check(poll - 1, poll, visited)) {
                visited[poll - 1] = visited[poll] + 1;
                queue.add(poll - 1);
            }
            if (check(poll * 2, poll, visited)) {
                visited[poll * 2] = visited[poll] + 1;
                queue.add(poll * 2);
            }
        }

        return visited[k];
    }

    private static boolean check(int prev, int now, int[] visited) {
        if (prev < 0 || prev >= visited.length) {
            return false;
        }

        return visited[prev] > visited[now] + 1;
    }
}

package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        System.out.println(bfs(n, m));
    }

    private static int bfs(int n, int m) {
        boolean[] visited = new boolean[100001];

        Queue<Integer> queue = new ArrayDeque<>();

        visited[n] = true;
        int minCount = Integer.MAX_VALUE;
        queue.add(n);
        int count = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {
                int cur = queue.poll();
                if (cur == m) {
                    minCount = Math.min(minCount, count);
                }
                if (cur - 1 >= 0 && !visited[cur - 1]) {
                    queue.add(cur - 1);
                    visited[cur - 1] = true;
                }
                if (cur + 1 < visited.length && !visited[cur + 1]) {
                    queue.add(cur + 1);
                    visited[cur + 1] = true;
                }
                if (cur * 2 < visited.length && !visited[cur * 2]) {
                    queue.add(cur * 2);
                    visited[cur * 2] = true;
                }
            }
            count += 1;
        }

        return minCount;
    }
}

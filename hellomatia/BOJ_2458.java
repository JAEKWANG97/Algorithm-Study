package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2458 {

    class Count {
        int count;

        public void add(int num) {
            count++;
        }

        public int getValue() {
            return count;
        }
    }

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private List<Integer>[] adj;
    private List<Integer>[] reverseAdj;

    public static void main(String[] args) throws IOException {
        new BOJ_2458().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adj = new List[N + 1];
        reverseAdj = new List[N + 1];

        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            reverseAdj[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            adj[a].add(b);
            reverseAdj[b].add(a);
        }
    }

    private int calcResult() {
        int result = 0;

        for (int i = 1; i <= N; i++) {
            if (bfs(i) == N - 1) {
                result++;
            }
        }
        return result;
    }

    private int bfs(int start) {
        Queue<Integer> queue = new ArrayDeque<>();
        Count count = new Count();
        boolean[] visited = new boolean[N + 1];

        queue.offer(start);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : adj[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    count.add(next);
                    queue.offer(next);
                }
            }
        }

        queue.offer(start);
        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : reverseAdj[now]) {
                if (!visited[next]) {
                    visited[next] = true;
                    count.add(next);
                    queue.offer(next);
                }
            }
        }

        return count.getValue();
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
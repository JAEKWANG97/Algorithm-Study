import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class BOJ_18352 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;
    private static int K;
    private static int X;

    private static List<Integer>[] graph;

    private static int[] answer;

    public static void main(String[] args) throws IOException {
        init();
        searchGraph();
        printAnswer();
    }

    private static void searchGraph() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int[] depth = new int[N + 1];

        queue.offer(X);
        visited[X] = true;
        depth[X] = 0;

        while (!queue.isEmpty()) {
            int start = queue.poll();

            for (int end : graph[start]) {
                if (visited[end]) {
                    continue;
                }

                queue.offer(end);
                visited[end] = true;
                depth[end] = depth[start] + 1;
            }
        }

        answer = IntStream.rangeClosed(1, N)
                .filter(idx -> depth[idx] == K)
                .toArray();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph[start].add(end);
        }
    }

    private static void printAnswer() {
        if (answer.length == 0) {
            System.out.println(-1);
            return;
        }

        for (int i = 0; i < answer.length; i++) {
            System.out.println(answer[i]);
        }
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2458 {

    private static int N, M;

    private static boolean[][] greaterGraph;
    private static boolean[][] lowerGraph;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        countStudent();

        printAnswer();
    }

    private static void countStudent() {
        for (int studentNum = 1; studentNum <= N; studentNum++) {
            if (countGreater(studentNum) + countLower(studentNum) == N - 1) {
                answer++;
            }
        }
    }

    private static int countGreater(int start) {
        int count = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int from = queue.poll();

            for (int to = 1; to <= N; to++) {
                if (from == to || !greaterGraph[from][to] || visited[to]) {
                    continue;
                }

                queue.offer(to);
                visited[to] = true;
                count++;
            }
        }

        return count;
    }

    private static int countLower(int start) {
        int count = 0;

        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int from = queue.poll();

            for (int to = 1; to <= N; to++) {
                if (from == to || !lowerGraph[from][to] || visited[to]) {
                    continue;
                }

                queue.offer(to);
                visited[to] = true;
                count++;
            }
        }

        return count;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        greaterGraph = new boolean[N + 1][N + 1];
        lowerGraph = new boolean[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            greaterGraph[from][to] = true;
            lowerGraph[to][from] = true;
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_13023 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;

    private static List<Integer>[] graph;

    private static boolean answer;

    public static void main(String[] args) throws IOException {
        init();

        searchRelationship();

        printAnswer();
    }

    private static void searchRelationship() {
        for (int i = 0; i < N; i++) {
            dfs(new boolean[N], i, 1);

            if (answer) {
                break;
            }
        }
    }

    private static void dfs(boolean[] visited, int from, int depth) {
        if (depth == 5) {
            answer = true;
            return;
        }

        visited[from] = true;

        for (int to : graph[from]) {
            if (visited[to]) {
                continue;
            }

            dfs(visited, to, depth + 1);
            visited[to] = false;
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            graph[to].add(from);
        }
    }

    private static void printAnswer() {
        if (answer) {
            System.out.println(1);
        } else {
            System.out.println(0);
        }
    }
}

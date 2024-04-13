import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1976 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static boolean[][] graph;

    private static int[] travelOrder;

    private static boolean canGo;
    private static boolean answer = true;

    public static void main(String[] args) throws IOException {
        init();
        travel();
        printAnswer();
    }

    private static void travel() {
        for (int i = 0; i < M - 1; i++) {
            int start = travelOrder[i];
            int destination = travelOrder[i + 1];

            checkRoute(new boolean[N + 1], start, destination);

            if (!canGo) {
                answer = false;
                break;
            }
            canGo = false;
        }
    }

    private static void checkRoute(boolean[] visited, int start, int destination) {
        if (start == destination) {
            canGo = true;
            return;
        }

        visited[start] = true;

        for (int i = 1; i <= N; i++) {
            if (!graph[start][i]) {
                continue;
            }

            if (visited[i]) {
                continue;
            }

            checkRoute(visited, i, destination);
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new boolean[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                String input = st.nextToken();

                if (input.equals("1")) {
                    graph[i][j] = true;
                } else {
                    graph[i][j] = false;
                }
            }
        }

        travelOrder = Arrays.stream(br.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .toArray();
    }
    private static void printAnswer() {
        if (answer) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

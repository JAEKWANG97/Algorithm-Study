import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ_2660 {

    private static final int INF = 100;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static boolean[][] graph;

    private static int[] scores;

    public static void main(String[] args) throws IOException {
        init();

        floydWarshall();

        printAnswer();
    }

    private static void printAnswer() {
        int minScore = Arrays.stream(scores)
                .min()
                .getAsInt();

        List<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            if (scores[i] == minScore) {
                candidates.add(i + 1);
            }
        }

        System.out.println(minScore + " " + candidates.size());
        System.out.println(candidates.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static void floydWarshall() {
        int[][] distances = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            Arrays.fill(distances[i], INF);
        }

        for (int from = 1; from <= N; from++) {
            for (int to = 1; to <= N; to++) {
                if (from == to) {
                    distances[from][to] = 0;
                    continue;
                }

                if (graph[from][to]) {
                    distances[from][to] = 1;
                }
            }
        }

        for (int k = 1; k <= N; k++) {
            for (int from = 1; from <= N; from++) {
                for (int to = 1; to <= N; to++) {
                    distances[from][to] = Math.min(distances[from][to], distances[from][k] + distances[k][to]);
                }
            }
        }

        scores = Arrays.stream(distances)
                .skip(1)
                .mapToInt(arr -> Arrays.stream(arr).filter(num -> num != INF).max().getAsInt())
                .toArray();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        graph = new boolean[N + 1][N + 1];

        String input = "";
        while (!(input = br.readLine()).equals("-1 -1")) {
            st = new StringTokenizer(input);
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from][to] = true;
            graph[to][from] = true;
        }
    }
}

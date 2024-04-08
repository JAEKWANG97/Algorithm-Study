import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static Edge[] graph;

    private static long[] distances;

    public static void main(String[] args) throws IOException {
        init();
        printAnswer(bellmanFord(1));
    }

    private static boolean bellmanFord(int start) {
        distances[start] = 0;

        for (int i = 1; i <= N; i++) {
            for (Edge edge : graph) {
                int currentNode = edge.getStart();
                int nextNode = edge.getEnd();
                int weight = edge.getWeight();

                if (distances[currentNode] != Long.MAX_VALUE && distances[currentNode] + weight < distances[nextNode]) {
                    distances[nextNode] = distances[currentNode] + weight;

                    if (i == N) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[i] = new Edge(start, end, weight);
        }

        distances = new long[N + 1];
        Arrays.fill(distances, Long.MAX_VALUE);
    }

    private static void printAnswer(boolean flag) {
        if (flag) {
            for (int i = 2; i <= N; i++) {
                if (distances[i] == Long.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(distances[i]);
                }
            }
        } else {
            System.out.println(-1);
        }
    }

    private static class Edge {

        private int start;
        private int end;
        private int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}

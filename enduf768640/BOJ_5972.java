import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_5972 {

    private static final long INF = 50000001;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;

    private static List<Edge>[] graph;

    private static long[] distances;

    public static void main(String[] args) throws IOException {
        init();
        dijkstra();
        printAnswer();
    }

    private static void dijkstra() {
        Arrays.fill(distances, INF);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingLong(Edge::getWeight));

        pq.offer(new Edge(1, 0));
        distances[1] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int now = edge.getTo();
            long distance = edge.getWeight();

            if (distances[now] < distance) {
                continue;
            }

            for (Edge e : graph[now]) {
                long cost = distances[now] + e.getWeight();

                if (distances[e.getTo()] > cost) {
                    distances[e.getTo()] = cost;
                    pq.offer(new Edge(e.getTo(), cost));
                }
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
            graph[to].add(new Edge(from, weight));
        }

        distances = new long[N + 1];
    }

    private static void printAnswer() {
        System.out.println(distances[N]);
    }

    private static class Edge {

        private int to;
        private long weight;

        public Edge(int to, long weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public long getWeight() {
            return weight;
        }
    }
}

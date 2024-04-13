import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10282 {

    private static final int INF = 10000001;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T;

    private static int N, D, C;
    private static List<Edge>[] graph;

    private static int[] distances;

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        distances = new int[10000 + 1];

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            init();
            dijkstra();
            makeAnswer();
        }

        printAnswer();
    }

    private static void dijkstra() {
        Arrays.fill(distances, INF);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        pq.offer(new Edge(C, 0));
        distances[C] = 0;

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            int now = edge.getTo();
            int dist = edge.getWeight();

            if (distances[now] < dist) {
                continue;
            }

            for (Edge e : graph[now]) {
                int time = distances[now] + e.getWeight();

                if (distances[e.getTo()] > time) {
                    distances[e.getTo()] = time;
                    pq.offer(new Edge(e.getTo(), time));
                }
            }
        }
    }

    private static void makeAnswer() {
        int count = 0;
        int max = 0;

        for (int i = 0; i < distances.length; i++) {
            if (distances[i] != INF) {
                count++;
                max = Math.max(max, distances[i]);
            }
        }

        answer.append(count).append(" ").append(max).append("\n");
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        graph = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < D; i++) {
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Edge(to, weight));
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Edge {

        private int to;
        private int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }
}

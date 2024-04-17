import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2211 {

    private static final int INF = 100_000;

    private static int N, M;
    private static List<Edge>[] graph;

    private static int answer;
    private static StringBuilder answers = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();

        dijkstra();

        printAnswer();
    }

    private static void dijkstra() {
        int[] previousComputer = new int[N + 1];

        int[] distances = new int[N + 1];
        Arrays.fill(distances, INF);

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));

        pq.offer(new Edge(1, 0));
        distances[1] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();

            if (distances[current.getTo()] < current.getWeight()) {
                continue;
            }

            for (Edge next : graph[current.getTo()]) {
                if (distances[next.getTo()] > distances[current.getTo()] + next.getWeight()) {
                    pq.offer(new Edge(next.getTo(), distances[current.getTo()] + next.getWeight()));
                    distances[next.getTo()] = distances[current.getTo()] + next.getWeight();

                    previousComputer[next.getTo()] = current.getTo();
                }
            }
        }

        boolean[] visited = new boolean[N + 1];
        for (int end = 2; end <= N; end++) {
            checkRoute(end, previousComputer, visited);
        }
    }

    private static void checkRoute(int end, int[] previousComputer, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(end);

        while (stack.peek() != 1) {
            stack.push(previousComputer[stack.peek()]);
        }

        while (stack.size() >= 2) {
            int from = stack.pop();
            int to = stack.peek();

            if (visited[from] && visited[to]) {
                continue;
            }

            visited[from] = true;
            visited[to] = true;

            answers.append(from).append(" ").append(to).append("\n");
            answer++;
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

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
    }

    private static void printAnswer() {
        System.out.println(answer);
        System.out.println(answers);
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

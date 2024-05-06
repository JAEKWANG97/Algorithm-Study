import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1922 {

    private static int N, M;

    private static Computer[] computers;
    private static PriorityQueue<Edge> pq;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        kruskal();

        printAnswer();
    }

    private static void kruskal() {
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (union(computers[edge.getFrom()], computers[edge.getTo()])) {
                answer += edge.getWeight();
            }
        }
    }

    private static Computer findSet(Computer computer) {
        if (computer.getParent() == computer) {
            return computer;
        }

        Computer root = findSet(computer.getParent());
        computer.setParent(root);

        return root;
    }

    private static boolean union(Computer computer1, Computer computer2) {
        if (findSet(computer1) == findSet(computer2)) {
            return false;
        }

        findSet(computer2).setParent(findSet(computer1));
        return true;
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        computers = new Computer[N + 1];
        for (int i = 1; i <= N; i++) {
            computers[i] = new Computer();
        }

        pq = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            pq.offer(new Edge(from, to, weight));
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Computer {

        private Computer parent;

        public Computer() {
            this.parent = this;
        }

        public Computer getParent() {
            return parent;
        }

        public void setParent(Computer parent) {
            this.parent = parent;
        }
    }

    private static class Edge {

        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public int getFrom() {
            return from;
        }

        public int getTo() {
            return to;
        }

        public int getWeight() {
            return weight;
        }
    }
}

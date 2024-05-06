import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int V;
    private static int E;

    private static Node[] nodes;

    private static PriorityQueue<Edge> edges;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        makeMinimumSpanningTree();
        printAnswer();
    }

    private static void makeMinimumSpanningTree() {
        int edgeCount = 0;

        while (true) {
            Edge edge = edges.poll();

            Node from = nodes[edge.getFrom()];
            Node to = nodes[edge.getTo()];

            if (findSet(from) != findSet(to)) {
                union(from, to);
                answer += edge.getWeight();
                edgeCount++;
            }

            if (edgeCount == V - 1) {
                break;
            }
        }
    }

    private static void union(Node node1, Node node2) {
        findSet(node2).setParent(findSet(node1));
    }

    private static Node findSet(Node node) {
        if (node.getParent() == node) {
            return node;
        }

        Node root = findSet(node.getParent());
        node.setParent(root);
        return root;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        makeSet();

        edges = new PriorityQueue<>(Comparator.comparingInt(Edge::getWeight));
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges.offer(new Edge(from, to, weight));
        }
    }

    private static void makeSet() {
        nodes = new Node[V + 1];
        for (int i = 1; i <= V; i++) {
            nodes[i] = new Node();
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
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

    private static class Node {

        private Node parent;

        public Node() {
            this.parent = this;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }
}

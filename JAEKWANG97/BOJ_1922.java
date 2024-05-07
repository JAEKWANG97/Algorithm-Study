package backjoon.MST;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1197 {
    static class Edge implements Comparable<Edge> {

        int from;
        int to;
        int weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static List<List<Edge>> edgeList = new ArrayList<>();
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        prim();
    }

    private static void init() throws IOException {
        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        edgeList = new ArrayList<>();
        for (int i = 0; i < V + 1; i++) {
            edgeList.add(new ArrayList<>());
        }
        visited = new boolean[V + 1];

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList.get(from).add(new Edge(from, to, weight));
            edgeList.get(to).add(new Edge(to, from, weight));
        }
    }

    private static void prim() {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.addAll(edgeList.get(1));
        visited[1] = true;
        int result = 0;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();
            if (visited[edge.to]) {
                continue;
            }

            visited[edge.to] = true;
            pq.addAll(edgeList.get(edge.to));
            result += edge.weight;
        }

        System.out.println(result);

    }
}

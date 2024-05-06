import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1197 {
    static int V, E, parent[];
    static Edge[] edges;

    static class Edge implements Comparable<Edge> {
        int from, to;
        long weight;

        public Edge(int from, int to, long weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Long.compare(this.weight, o.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        parent = new int[V + 1];
        edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }

        Arrays.sort(edges);
        //.집합 만들기
        make();

        int cnt = 0;
        long weight = 0;
        //
        for (Edge edge : edges) {
            if (!union(edge.from, edge.to)) continue;
            weight += edge.weight;
            if (++cnt == V - 1) break;
        }
        System.out.println(weight);
    }//end main

    private static void make() {
        for (int i = 1; i <= V; i++) {
            parent[i] = i;
        }
    }//end make

    private static int find(int a){
        if (parent[a] == a) return a;
        return parent[a] = find(parent[a]);
    }//end find

    private static boolean union(int a, int b){
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parent[aRoot] = bRoot;
        return true;
    }// end union
}

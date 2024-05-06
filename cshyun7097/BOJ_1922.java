import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static class Edge implements Comparable<Edge> {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    static int V, parents[];
    static Edge[] edgeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        V = Integer.parseInt(br.readLine());            //컴퓨터 개수 = 정점의 수
        int M = Integer.parseInt(br.readLine());        //간선 수
        edgeList = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edgeList[i] = new Edge(from, to, weight);
        }
        Arrays.sort(edgeList);

        parents = new int[V + 1];
        make();

        int weight = 0;
        int cnt = 0;
        for (Edge edge : edgeList) {
            if (!union(edge.from, edge.to)) continue;
            weight += edge.weight;
            if (++cnt == V - 1) break;
        }
        System.out.println(weight);
    }

    private static void make() {
        for (int i = 0; i < V; i++) {
            parents[i] = i;
        }
    }

    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    private static boolean union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot == bRoot) return false;
        parents[bRoot] = aRoot;
        return true;
    }
}

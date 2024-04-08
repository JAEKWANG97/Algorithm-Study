import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 택배 배송 - 60분
public class BOJ_5972 {
    static StringBuilder sb = new StringBuilder();
    static int N, M;
    static class Edge {
        int from, to, w;

        public Edge(int from, int to, int w) {
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }
    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Edge>[] list = new List[N+1];

        int from, to, w;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            from = Integer.parseInt(st.nextToken());
            to = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            if (list[from] == null) list[from] = new ArrayList<>();
            list[from].add(new Edge(from, to, w));

            if (list[to] == null) list[to] = new ArrayList<>();
            list[to].add(new Edge(to, from, w));
        }

        solution(list);
    }

    private static void solution(List<Edge>[] list) {
        int[] minWeight = new int[N+1];

        Arrays.fill(minWeight, Integer.MAX_VALUE);
        minWeight[1] = 0;

        PriorityQueue<Edge> pq = new PriorityQueue<>(new Comparator<Edge>() {
            public int compare(Edge o1, Edge o2) {
                return Integer.compare(o1.w, o2.w);
            }
        });
        pq.offer(new Edge(0, 1, 0));

        while(!pq.isEmpty()) {
            Edge cur = pq.poll();

            for (Edge node : list[cur.to]) {
                if (minWeight[node.to] > cur.w + node.w) {
                    minWeight[node.to] = cur.w + node.w;
                    pq.offer(new Edge(0, node.to, minWeight[node.to]));
                }
            }
        }
        sb.append(minWeight[N]);
    }
}
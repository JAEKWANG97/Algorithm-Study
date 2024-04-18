import java.io.*;
import java.util.*;

public class BOJ_2211 {
    static class Edge implements Comparable<Edge> {
        int target, cost;

        public Edge(int target, int cost) {
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    private static final int INF = 1_000_000_000;
    static int N, M;

    static PriorityQueue<Edge> pq;
    static List<Edge>[] edges;
    static int[] distance, answer;

    static void helper() {

        // 슈퍼컴퓨터
        distance[1] = 0;
        pq.add(new Edge(1, 0));

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (distance[cur.target] < cur.cost) {
                continue;
            }

            for (Edge next : edges[cur.target]) {
                if (next.cost + cur.cost < distance[next.target]) {
                    distance[next.target] = next.cost + cur.cost;
                    answer[next.target] = cur.target;
                    pq.add(new Edge(next.target, distance[next.target]));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        pq = new PriorityQueue<>();
        edges = new ArrayList[N + 1];
        distance = new int[N + 1];
        answer = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        Arrays.fill(distance, INF);

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            edges[a].add(new Edge(b, c));
            edges[b].add(new Edge(a, c));
        }

        helper();

        int count = 0;
        for (int i = 0; i <= N; i++) {
            if (answer[i] != 0) {
                count++;
                sb.append(answer[i]).append(" ").append(i).append("\n");
            }
        }
        sb.insert(0, count + "\n");

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
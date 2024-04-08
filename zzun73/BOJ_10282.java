import java.io.*;
import java.util.*;

public class BOJ_10282 {
    private static final int INF = Integer.MAX_VALUE;

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

    static int[] helper(int n, int c, List<Edge>[] edges) {
        int[] distance = new int[n + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(distance, INF);

        distance[c] = 0;
        pq.add(new Edge(c, 0));
        while (!pq.isEmpty()) {
            Edge cur = pq.poll();

            if (cur.cost > distance[cur.target]) {
                continue;
            }

            for (Edge next : edges[cur.target]) {
                int costSum = next.cost + cur.cost;
                if (distance[next.target] > costSum) {
                    distance[next.target] = costSum;
                    pq.add(new Edge(next.target, costSum));
                }
            }
        }

        int[] result = new int[2];
        for (int i = 1; i < distance.length; i++) {
            if (distance[i] != INF) {
                result[1] = Math.max(distance[i], result[1]);
                result[0]++;
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int tc = Integer.parseInt(br.readLine());
        while (tc-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            List[] edges = new ArrayList[n + 1];
            for (int i = 0; i < n + 1; i++) {
                edges[i] = new ArrayList<Edge>();
            }
            while (d-- > 0) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                edges[b].add(new Edge(a, s));
            }
            int[] result = helper(n, c, edges);
            sb.append(result[0]).append(" ").append(result[1]).append("\n");
        }

        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}
import java.io.*;
import java.util.*;

public class BOJ_5972 {
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

    static private final int INF = Integer.MAX_VALUE;

    static int helper(List<Edge>[] edges, int[] distance) {
        int start = 1;
        int end = distance.length - 1;

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(distance, INF);
        pq.add(new Edge(1, 0));
        distance[start] = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (distance[cur.target] < cur.cost) {
                continue;
            }

            for (Edge next : edges[cur.target]) {
                int costSum = cur.cost + next.cost;
                if (distance[next.target] > costSum) {
                    distance[next.target] = costSum;
                    pq.add(new Edge(next.target, costSum));
                }
            }
        }
        return distance[end];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Edge>[] edges = new ArrayList[N + 1];
        int[] distance = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            edges[A].add(new Edge(B, C));
            edges[B].add(new Edge(A, C));
        }
        bw.write(String.valueOf(helper(edges, distance)));

        br.close();
        bw.close();
    }
}
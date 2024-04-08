import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_5972 {
    private static class Edge implements Comparable<Edge> {
        int end, cost;
        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.cost, o.cost);
        }
    };
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] distance = new int[N + 1];
        LinkedList<Edge>[] edges = new LinkedList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            edges[i] = new LinkedList<>();
            distance[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[start].add(new Edge(end, cost));
            edges[end].add(new Edge(start, cost));
        }

        System.out.println(dij(edges, distance, N));

    }

    private static int dij(LinkedList<Edge>[] edges, int[] distance, int target) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        distance[1] = 0;
        for (Edge e : edges[1]) {
            pq.add(e);
        }
        Edge now;

        while (!pq.isEmpty()) {
            now = pq.poll();
            //System.out.println(now.end + " " + now.cost);
            //if (distance[now.end] != Integer.MAX_VALUE) continue;
            if (distance[now.end] <= now.cost) continue;
            if (now.end == target) return now.cost;
            distance[now.end] = now.cost;
            for (Edge e : edges[now.end]) {
                pq.add(new Edge(e.end, e.cost + now.cost));
            }
        }

        return -1;
    }
}

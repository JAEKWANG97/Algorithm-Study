import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2211 {

    private static class Edge implements Comparable<Edge>{
        int start, target, cost;

        public Edge(int start, int target, int cost) {
            this.start = start;
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] edges = new ArrayList[V + 1];

        for (int i = 1; i < V + 1; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            edges[s].add(new Edge(s, e, c));
            edges[e].add(new Edge(e, s, c));
        }

        // 슈퍼 컴퓨터가 start 일때 다익스트라...?
        boolean visited[] = new boolean[V + 1];
        int ans = 0;
        List<Edge> ansList = null;
        int start = 1;
        int findCnt = 0;
        Arrays.fill(visited, false);
        visited[start] = true;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (Edge e : edges[start]) {
            pq.add(e);
        }

        while (!pq.isEmpty() && findCnt < V) {
            Edge now = pq.poll();
            if (visited[now.target]) continue;
            visited[now.target] = true;
            findCnt++;
            ansList.add(now);

            for (Edge e : edges[now.target]) {
                pq.add(new Edge(e.start, e.target, now.cost + e.cost));
            }

        }

        StringBuilder sb = new StringBuilder().append(ans).append("\n");
        for (Edge e : ansList) {
            sb.append(e.start).append(" ").append(e.target).append("\n");
        }
        System.out.println(sb);

    }
}

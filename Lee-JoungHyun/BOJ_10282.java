import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282 {
    public static class Edge implements Comparable<Edge>{
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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < N; tc++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());


            LinkedList<Edge>[] graph = new LinkedList[n + 1];
            for (int i = 1; i < n + 1; i++) {
                graph[i] = new LinkedList<>();
            }
            boolean[] visited = new boolean[n + 1];
            visited[c] = true;
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                int end = Integer.parseInt(st.nextToken());
                int start = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                if (start == c) {
                    pq.add(new Edge(end, cost));
                }
                else
                    graph[start].add(new Edge(end, cost));
            }

            int answer = 0;
            int cnt = 1;
            Edge now;
            while (!pq.isEmpty()) {
                now = pq.poll();
                if (visited[now.target]) continue;
                visited[now.target] = true;
                cnt++;
                answer = now.cost;
                for (Edge nxt : graph[now.target]) {
                    pq.add(new Edge(nxt.target, nxt.cost + now.cost));
                }
            }
            sb.append(cnt).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}

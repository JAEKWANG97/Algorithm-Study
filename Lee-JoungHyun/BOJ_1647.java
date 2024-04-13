import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1647 {
    private static class Edge implements Comparable<Edge>{
        int b1, b2, cost;
        public Edge(int b1, int b2, int cost) {
            this.b1 = b1;
            this.b2 = b2;
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

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parents = new int[N + 1];
        for (int i = 1; i < N + 1; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int b1 = Integer.parseInt(st.nextToken());
            int b2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            pq.add(new Edge(b1, b2, cost));
        }
        // MST 구하기 -> 나누기 = 최소 신장 트리에서 가장 긴(마지막) edge 제거
        int answer = 0;
        int lastCost = 0;
        Edge now;
        while (!pq.isEmpty()) {
            now = pq.poll();
            // 서로 사이클 형성X
            if (union(now.b1, now.b2, parents)) {
                answer += now.cost;
                lastCost = now.cost;
            }
        }

        System.out.println(answer - lastCost);
    }
    static boolean union(int a, int b, int[] parents) {
        a = find(a, parents);
        b = find(b, parents);
        if (a == b) return false;
        parents[a] = b;
        return true;
    }
    static int find(int a, int[] parents) {
        if (parents[a] == a) return a;
        return parents[a] = find(parents[a], parents);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_18352 {
    private static class Edge implements Comparable<Edge>{
        int num, distance;
        public Edge(int num, int distance) {
            this.num = num;
            this.distance = distance;
        }

        @Override
        public int compareTo(Edge o) {
            return this.distance - o.distance;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] edges = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[Integer.parseInt(st.nextToken()) - 1].add(Integer.parseInt(st.nextToken()) - 1);
        }

        PriorityQueue<Integer> answer = new PriorityQueue<>();
        // 다익스트라
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean visited[] = new boolean[N];
        visited[X-1] = true;
        pq.add(new Edge(X - 1, 0));
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            if (now.distance == K) answer.add(now.num + 1);
            else {
                for (int i : edges[now.num]) {
                    if (!visited[i]) {
                        visited[i] = true;
                        pq.add(new Edge(i, now.distance + 1));
                    }
                }
            }
        }
        if (answer.isEmpty())
            System.out.println(-1);
        else {
            while (!answer.isEmpty()) {
                System.out.println(answer.poll());
            }
        }
    }
}

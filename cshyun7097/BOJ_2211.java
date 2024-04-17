import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2211 {
    static int N, M;
    static List<Node>[] arr;
    static HashSet<String> set;

    static class Node implements Comparable<Node> {
        int idx, cost;

        public Node(int idx, int cost) {
            this.idx = idx;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        set = new HashSet<>();

        arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            arr[start].add(new Node(end, cost));
            arr[end].add(new Node(start, cost));
        }

        dijkstra(1);
        System.out.println(set.size());
        for (String s : set) {
            System.out.println(s);
        }
    }

    private static void dijkstra(int start) {
        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        int[] tmp = new int[N + 1];
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[start] = 0;

        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            int idx = pq.poll().idx;
            if (visited[idx]) continue;
            visited[idx] = true;

            for (Node node : arr[idx]) {
                if (dist[node.idx] > dist[idx] + node.cost) {
                    dist[node.idx] = dist[idx] + node.cost;
                    pq.offer(new Node(node.idx, dist[node.idx]));
                    tmp[node.idx] = idx;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            int end = i;
            while (tmp[end] != 0) {
                set.add(new String(end + " " + tmp[end]));
                end = tmp[end];
            }
        }
    }
}

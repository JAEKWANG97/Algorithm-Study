import java.util.*;

class Edge implements Comparable<Edge> {
    int end, cost;
    Edge(int end, int cost) {
        this.end = end;
        this.cost = cost;
    }
    public int compareTo(Edge o) {
        return this.cost - o.cost;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 헛간의 수
        int M = sc.nextInt(); // 길의 수

        // 각 헛간을 연결하는 길의 정보를 저장할 리스트를 초기화
        ArrayList<ArrayList<Edge>> graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<Edge>());
        }

        // 길의 정보 입력 받기
        for (int i = 0; i < M; i++) {
            int A = sc.nextInt();
            int B = sc.nextInt();
            int C = sc.nextInt();
            graph.get(A).add(new Edge(B, C));
            graph.get(B).add(new Edge(A, C)); // 양방향 길
        }

        System.out.println(dijkstra(N, graph));
    }

    public static int dijkstra(int N, ArrayList<ArrayList<Edge>> graph) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[N + 1];
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE); 

        pq.offer(new Edge(1, 0)); 
        dist[1] = 0;

        while (!pq.isEmpty()) {
            Edge current = pq.poll();
            int currentEnd = current.end;

            if (visited[currentEnd]) continue;
            visited[currentEnd] = true;

            for (Edge e : graph.get(currentEnd)) {
                if (dist[e.end] > dist[currentEnd] + e.cost) {
                    dist[e.end] = dist[currentEnd] + e.cost;
                    pq.offer(new Edge(e.end, dist[e.end]));
                }
            }
        }

        return dist[N];
    }
}

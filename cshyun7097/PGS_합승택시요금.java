import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class PGS_합승택시요금 {

    static int N;
    static int E;
    static int[][] arr;
    static final int INF = Integer.MAX_VALUE;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        E = fares.length;
        arr = new int[n][n];

        for (int i = 0; i < E; i++) {
            int u = fares[i][0] - 1;
            int v = fares[i][1] - 1;
            int cost = fares[i][2];
            arr[u][v] = cost;
            arr[v][u] = cost;
        }

        int[] some = dijkstra(s - 1);
        int answer = INF;
        for (int i = 0; i < N; i++) {
            int[] one = dijkstra(i);
            int cost = some[i] + one[a - 1] + one[b - 1];
            if (cost < answer) {
                answer = cost;
            }
        }

        return answer;
    }

    public int[] dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        boolean[] visited = new boolean[N];
        int[] dist = new int[N];
        Arrays.fill(dist, INF);
        dist[start] = 0;
        pq.offer(new int[]{0, start});

        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int u = cur[1];
            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            for (int v = 0; v < N; v++) {
                if (arr[u][v] == 0) {
                    continue;
                }
                if (dist[v] > dist[u] + arr[u][v]) {
                    dist[v] = dist[u] + arr[u][v];
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }

        return dist;
    }
}
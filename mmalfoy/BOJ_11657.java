
import java.util.*;
import java.io.*;

class Edge {
    int from, to, weight;
    Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }
}

public class BOJ_11657 {
    static int N, M;
    static Edge[] edges;
    static long[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            
            edges[i] = new Edge(from, to, weight);
        }
        
        dist = new long[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        if (!bellmanFord()) {
            System.out.println(-1);
        } else {
            for (int i = 2; i <= N; i++) {
                if (dist[i] == Integer.MAX_VALUE) {
                    System.out.println(-1);
                } else {
                    System.out.println(dist[i]);
                }
            }
        }
    }

    static boolean bellmanFord() {
        for (int i = 1; i < N; i++) {
            for (Edge e : edges) {
                if (dist[e.from] < Integer.MAX_VALUE && dist[e.to] > dist[e.from] + e.weight) {
                    dist[e.to] = dist[e.from] + e.weight;
                }
            }
        }
        
        for (Edge e : edges) {
            if (dist[e.from] < Integer.MAX_VALUE && dist[e.to] > dist[e.from] + e.weight) {
                return false; // 음수 사이클 존재
            }
        }
        
        return true; // 음수 사이클 없음
    }
}
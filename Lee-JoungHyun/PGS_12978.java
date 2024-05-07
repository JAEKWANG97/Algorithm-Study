import java.util.*;

class Solution {
    
    public class Edge implements Comparable<Edge> {
        int d, c;
        
        public Edge(int d, int c) {
            this.d = d;
            this.c = c;
        }
        
        public int compareTo(Edge o) {
            return this.c - o.c;
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        
        List<Edge>[] graph = new ArrayList[N + 1];
        
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int[] r : road) {
            graph[r[0]].add(new Edge(r[1], r[2]));
            graph[r[1]].add(new Edge(r[0], r[2]));
        }
        
        int[] distance = new int[N + 1];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[1] = 0;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        pq.add(new Edge(1, 0)); // 시작점
        
        while (!pq.isEmpty()) {
            Edge now = pq.poll();
            
            if (now.c > distance[now.d]) continue; // 이미 더 짧은 경로로 처리된 경우
            
            answer++; // 최단 거리에 포함되는 마을 개수 증가
            
            for (Edge e : graph[now.d]) {
                int newDistance = now.c + e.c;
                if (newDistance <= K && newDistance < distance[e.d]) {
                    distance[e.d] = newDistance;
                    pq.add(new Edge(e.d, newDistance));
                }
            }
        }

        return answer;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282 {

    static class Edge {
        int next;
        int weight;
        
        public Edge(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }    
    }
    
    static class Node implements Comparable<Node> {
        int num;
        int curWeight;
        
        public Node(int num, int curWeight) {
            this.num = num;
            this.curWeight = curWeight;
        }
        
        public int compareTo(Node o) {
            return this.curWeight - o.curWeight;
        }
        
    }
    
    static int t; //테케
    static int n, d, c; //노드 개수, 엣지 개수, 시작 정점
    static ArrayList<Edge>[] graph;
    static int dist[]; //거리
    
    static void dijkstra() {
        PriorityQueue<Node> pq = new PriorityQueue();
        dist[c] = 0;
        pq.offer(new Node(c, 0));
        
        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            
            for (int i = 0; i < graph[cur.num].size(); i++) {
                Edge curEdge = graph[cur.num].get(i);
                
                if (cur.curWeight + curEdge.weight < dist[curEdge.next]) {
                    dist[curEdge.next] = cur.curWeight + curEdge.weight;
                    pq.offer(new Node(curEdge.next, dist[curEdge.next]));
                }
            }
            
            
        }
        
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        t = Integer.parseInt(br.readLine());
        //a <- b
        
        for (int tc = 1; tc <= t; tc++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            graph = new ArrayList[n + 1];
            for (int i = 1; i <= n; i++) {
                graph[i] = new ArrayList<>();
            }
            dist = new int[n + 1];
            Arrays.fill(dist, Integer.MAX_VALUE);
            
            
            for (int i = 0; i < d; i++) {
                int u, v, w;
                st = new StringTokenizer(br.readLine());
                u = Integer.parseInt(st.nextToken());
                v = Integer.parseInt(st.nextToken());
                w = Integer.parseInt(st.nextToken());
                graph[v].add(new Edge(u, w));
            }
            
            dijkstra();
            int computerCnt = 0;
            int time = 0;
            
            for (int i = 1; i <= n; i++) {
            	if (Integer.MAX_VALUE != dist[i]) {
            		computerCnt++;
            		time = Math.max(time, dist[i]);
            	}
            }
            
            System.out.println(computerCnt + " " + time);
        }
        
        
    }    
}
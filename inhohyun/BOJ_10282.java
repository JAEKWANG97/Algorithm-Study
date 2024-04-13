import java.util.*;
 
public class BOJ_10282 {
    
    static int n, d, c;
    static ArrayList<Node>[] list;
    static int count;
    static boolean[] visited;
    static int[] dist;
    
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        int t = scan.nextInt();
        for(int i = 0; i < t; i++) {
            n = scan.nextInt();
            d = scan.nextInt();
            c = scan.nextInt();
            
            list = new ArrayList[n + 1];
            for(int j = 1; j <= n; j++) {
                list[j] = new ArrayList<>();
            }
            
            for(int j = 0; j < d; j++) {
                int a = scan.nextInt();
                int b = scan.nextInt();
                int s = scan.nextInt();
                list[b].add(new Node(a, s));
            }
            
            count = 0;
            dist = new int[n + 1];
            visited = new boolean[n + 1];
            
            Arrays.fill(dist, Integer.MAX_VALUE);
            dist[c] = 0;
            
            dijkstra();
            int time = 0;
            for(int j = 1; j <= n; j++) {
                if(dist[j] != Integer.MAX_VALUE) time = Math.max(time, dist[j]);
            }
            
            System.out.println(count + " " + time);
        }
    }
    
    public static void dijkstra() {
        PriorityQueue<Node> q = new PriorityQueue<>();
        q.offer(new Node(c, 0));
        
        while(!q.isEmpty()) {
            Node current = q.poll();
            
            if(visited[current.n] == false) {
                visited[current.n] = true;
                count++;
            }
            else continue;
            
            for(int i = 0; i < list[current.n].size(); i++) {
                Node next = list[current.n].get(i);
                if(dist[next.n] > dist[current.n] + next.s) {
                    dist[next.n] = dist[current.n] + next.s;
                    q.offer(new Node(next.n, dist[next.n]));
                }
            }
        }
    }
    
    public static class Node implements Comparable<Node> {
        int n;
        int s;
        
        public Node(int n, int s) {
            this.n = n;
            this.s = s;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.s - o.s;
        }
    }
}
 

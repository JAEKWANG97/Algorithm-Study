import java.io.*;
import java.util.*;

public class BOJ_13549 {

    public static class Node implements Comparable<Node>{
        int pos;
        int time;
        
        public Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
        
        @Override
        public int compareTo(Node other) {
            if(other.time > this.time)
                return -1;
            else
                return 1;
        }
    }
    
    public static int START, END;
    public static boolean[] visited;
    public static int min = Integer.MAX_VALUE;
    public static int max = 100000;
    
    
    public static void bfs() {
        PriorityQueue<Node> q = new PriorityQueue<Node>();
        
        q.offer(new Node(START, 0));
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            
            visited[node.pos] = true;
            
            if(node.pos == END) {
                min = Math.min(min, node.time);
            }
            
            if(node.pos * 2 <= max && !visited[node.pos * 2]) {
                q.offer(new Node(node.pos * 2, node.time));
            }
            
            if(node.pos + 1 <= max && !visited[node.pos + 1]) {
                q.offer(new Node(node.pos + 1, node.time + 1));
            }
            
            if(node.pos - 1 >= 0 && !visited[node.pos - 1]) {
                q.offer(new Node(node.pos - 1, node.time + 1));
            }
        }
        
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        START = Integer.parseInt(st.nextToken());
        END = Integer.parseInt(st.nextToken());

        visited = new boolean[max + 1];
        
        bfs();
        
        System.out.println(min);
    }
}

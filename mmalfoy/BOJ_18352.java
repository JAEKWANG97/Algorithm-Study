
import java.util.*;
import java.io.*;

public class BOJ_18352 {
    static List<Integer>[] G;
    static boolean[] visited;
    
    private static class Node {
        int value;
        int depth;

        Node(int value, int depth) {
            this.value = value;
            this.depth = depth;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        G = new ArrayList[N + 1];
        for (int i = 1; i < N + 1; i++) {
            G[i] = new ArrayList<Integer>();
        }
        visited = new boolean[N + 1];
        visited[X] = true;
        Queue<Node> q= new ArrayDeque<>();
        
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            G[a].add(b);
        }
    
        List<Integer> result = new ArrayList<>();
        q.offer(new Node(X,0));
        
        while (!q.isEmpty()) {
            Node node = q.poll();
            
            if (node.depth == K) {
            	result.add(node.value);
                continue;
            }
            
            if (node.depth > K ) {
                continue;
            }
            
            for (int i = 1; i < N+1; i++) {
                if (G[node.value].contains(i) && !visited[i]) {
                    visited[i] = true;
                    q.offer(new Node(i, node.depth + 1));
                }
            }
        }
        
        
        if (result.isEmpty()) {
            System.out.println(-1);
            return;
        }
        
        Collections.sort(result);
        StringBuilder sb = new StringBuilder();
        for (int i : result) {
        	sb.append(i).append('\n');
        }
        System.out.println(sb.toString());
    }
    
}

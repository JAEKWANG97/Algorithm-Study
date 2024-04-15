import java.io.*;
import java.util.*;

public class BOJ_2665 {
    static int N;
    static int[][] G;
    static boolean[][][] visited;
    static class Node implements Comparable<Node> {
        int y, x, depth;
        Node (int y, int x, int depth) {
            this.y = y;
            this.x = x;
            this.depth = depth;
        }
        
        @Override
        public int compareTo(Node o) {
            return this.depth - o.depth;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine().trim());
        G = new int[N][N];
        visited = new boolean[N * N][N][N];
        for (int n = 0; n < N; n++) {
            String str = br.readLine();
            for (int m = 0; m < N; m++) {
                G[n][m] = Character.getNumericValue(str.charAt(m));
            }
        }
        
        // 검은방 -> 못들어감
        // 붙어있는 흰방 -> 들어갈 수 있음
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(0, 0, 0));
        visited[0][0][0] = true;
        
        // 검은 방 서치 갯수로 pq
        System.out.println(bfs(pq, visited));
    }
    
    public static int bfs(PriorityQueue<Node> pq, boolean[][][] visited) {
        int result = -1;
        int[][] d = new int[][] {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if (node.y == N - 1 && node.x == N - 1) {
                result = node.depth;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int ny = node.y + d[i][0];
                int nx = node.x + d[i][1];
                if (ny < 0 || ny > N - 1 || nx < 0 || nx > N - 1 || visited[node.depth][ny][nx]) {
                    continue;
                }
                
                if (G[ny][nx] == 0) {
                    visited[node.depth + 1][ny][nx] = true;
                    pq.offer(new Node(ny, nx, node.depth + 1));
                } else {
                    visited[node.depth][ny][nx] = true;
                    pq.offer(new Node(ny, nx, node.depth));                    
                }
                
            }
            
            
        }
        return result;
    }
}
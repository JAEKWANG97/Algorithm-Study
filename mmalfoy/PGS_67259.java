import java.util.*;

class Solution {
    static int[] dy = new int[] {-1, 1, 0, 0};
    static int[] dx = new int[] {0, 0, -1, 1};
    public int solution(int[][] board) {

        int[][] visited = new int[board.length][board.length];
        Queue<Node> q = new ArrayDeque<Node>();
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        
        int right = Integer.MAX_VALUE;
        if (board[0][1] != 1) {
            q.offer(new Node(0, 1, 100, 3));
            right = bfs(q, visited, board);  
        }
        
        int under = Integer.MAX_VALUE;
        for (int[] row : visited) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        if (board[1][0] != 1) {
            q.offer(new Node(1, 0, 100, 1));
            under = bfs(q, visited, board);  
        }
        
        return Math.min(under, right);
    }
    
    private int bfs (Queue<Node> q, int[][] visited, int[][] board) {
        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Node node = q.poll();
            if (node.y == board.length - 1 && node.x == board.length - 1) {
                answer = Math.min(node.cost, answer);
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int ny = node.y + dy[i];
                int nx = node.x + dx[i];
                
                if (ny < 0 || ny > visited.length - 1 || nx < 0 || nx > visited.length - 1 || board[ny][nx] == 1) {
                    continue;
                }
                
                int cost = node.cost;
                if (node.i / 2 != i / 2) {
                    cost += 500;
                } 
                cost += 100;
                
                
                if (cost <= visited[ny][nx] ) {
                    visited[ny][nx] = cost;
                    q.offer(new Node(ny, nx, cost, i));
                }
                
            }
        }
        return answer;
    }
}

class Node {
    int y, x, cost, i;
    Node (int y, int x, int cost, int i) {
        this.y = y;
        this.x = x;
        this.cost = cost;
        this.i = i;
    }
}


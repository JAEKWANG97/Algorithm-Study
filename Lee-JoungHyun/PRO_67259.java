import java.util.*;
// 직선 100원, 코너 500원
class Poz implements Comparable<Poz> {
    int y, x, cost, dir;
    public Poz(int y, int x, int cost, int dir) {
        this.y = y;
        this.x = x;
        this.cost = cost;
        this.dir = dir;
    }
    
    public int compareTo(Poz o1) {
        return this.cost - o1.cost;
    }
}
class Solution {
    public int solution(int[][] board) {
        int N = board.length;
        int[][][] cost = new int[4][N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[0][i][j] = 1000000;
                cost[1][i][j] = 1000000;
                cost[2][i][j] = 1000000;
                cost[3][i][j] = 1000000;
            }
        }
        cost[0][0][0] = 0;
        cost[1][0][0] = 0;
        cost[2][0][0] = 0;
        cost[3][0][0] = 0;
        PriorityQueue<Poz> pq = new  PriorityQueue<>();
        pq.add(new Poz(0, 0, 0, -1));
        Poz now;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        while (!pq.isEmpty()) {
            now = pq.poll();
            if (now.x == N-1 && now.y == N-1) {
;
            }
            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];
                int myCost = 0;
                if (now.dir == -1 || now.dir == d) {
                    myCost = 100;
                }
                else {
                    myCost = 600;
                }
                if (0 <= ny && ny < N && 0 <= nx && nx < N && board[ny][nx] == 0 && cost[d][ny][nx] >= now.cost + myCost) {
                    cost[d][ny][nx] = now.cost + myCost;
                    pq.add(new Poz(ny, nx, now.cost + myCost, d));
                }
            }
        }
        
        
        return Math.min(Math.min(cost[0][N-1][N-1], cost[1][N-1][N-1]), Math.min(cost[2][N-1][N-1], cost[3][N-1][N-1]));
        
    }
}
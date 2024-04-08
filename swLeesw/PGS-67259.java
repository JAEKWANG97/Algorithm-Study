import java.util.Arrays;
import java.util.PriorityQueue;

class Solution {
    static class Pos {
        int y, x, cost, dir; // 행, 열, 현재 최소 비용, 방향

        public Pos(int y, int x, int cost, int dir) {
            this.y = y;
            this.x = x;
            this.cost = cost;
            this.dir = dir;
        }
    }

    static class Cmp implements java.util.Comparator<Pos> {
        public int compare(Pos a, Pos b) {
            return Integer.compare(a.cost, b.cost);
        }
    }

    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        int[] dy = { -1, 1, 0, 0 };
        int[] dx = { 0, 0, -1, 1 };

        final int INF = Integer.MAX_VALUE;

        int[][][] cost = new int[25][25][4];
        for (int[][] row : cost)
            for (int[] col : row)
                Arrays.fill(col, INF);
        for (int i = 0; i < 4; ++i)
            cost[0][0][i] = 0;

        PriorityQueue<Pos> pq = new PriorityQueue<>(new Cmp());
        pq.add(new Pos(0, 0, 0, -1));
        while (!pq.isEmpty()) {
            // 방문
            Pos now = pq.poll();

            // 예약
            for (int i = 0; i < 4; ++i) {
                int nextY = now.y + dy[i];
                int nextX = now.x + dx[i];
                int nextDir = i;
                int nextCost = now.cost;

                if (nextY < 0 || nextY >= n || nextX < 0 || nextX >= n || board[nextY][nextX] == 1)
                    continue;

                // cost cal
                nextCost += 100;
                if (now.dir == 0 || now.dir == 1)
                    if (nextDir == 2 || nextDir == 3)
                        nextCost += 500;
                if (now.dir == 2 || now.dir == 3)
                    if (nextDir == 0 || nextDir == 1)
                        nextCost += 500;

                if (nextCost < cost[nextY][nextX][nextDir]) {
                    cost[nextY][nextX][nextDir] = nextCost;
                    pq.add(new Pos(nextY, nextX, nextCost, nextDir));
                }
            }
        }
        answer = Arrays.stream(cost[n - 1][n - 1]).min().getAsInt();
        return answer;
    }
}

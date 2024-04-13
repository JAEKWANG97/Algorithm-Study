import java.util.*;

class PRO_42891 {
    private int answer = Integer.MAX_VALUE;
    int[] moveX = {0,1,0,-1};
    int[] moveY = {1,0,-1,0};
    private int[][] visited;

    public int solution(int[][] board) {
        visited = board.clone();

        int res = bfs(board);

        return res;
    }

    public int bfs(int[][] board){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0, 0, 0, -1));
        visited[0][0] = 1;

        while(!q.isEmpty()) {
            Pair pair = q.poll();
            int x = pair.x;
            int y = pair.y;
            int cost = pair.cost;
            int direction = pair.direction;

            if(x == board.length-1 && y == board.length-1){
                answer = Math.min(answer, cost);
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = x + moveX[i];
                int nextY = y + moveY[i];

                if (nextX >= board.length || nextX < 0 || nextY >= board.length || nextY < 0)
                    continue;
                if (visited[nextX][nextY] == 1)
                    continue;

                int nextCost = 0;
                if(direction == -1 || direction == i)
                    nextCost = cost + 100;
                else if(direction != i)
                    nextCost = cost + 600;

                if(visited[nextX][nextY] == 0 || visited[nextX][nextY] >= nextCost){
                    visited[nextX][nextY] = nextCost;
                    q.add(new Pair(nextX, nextY, nextCost, i));
                }

            }
        }
        return answer;
    }

    public class Pair{
        int x;
        int y;
        int cost;
        int direction;

        public Pair(int x, int y, int cost, int direction) {
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direction = direction;
        }
    }
}
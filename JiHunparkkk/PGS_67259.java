import java.util.*;

class Solution {
    
    private static class Point{
        int x, y, dir, cost;
        
        public Point(int x, int y, int dir, int cost){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.cost = cost;
        }
    }
    
    public int solution(int[][] board) {
        
        int answer = bfs(board);
        
        return answer;
    }
    
    private static int bfs(int[][] board){
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};
        int len = board.length;
        boolean[][][] visited = new boolean[len][len][4];
        
        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0, -1, 0)); //dir==-1 ? 기본값
        
        int result = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Point poll = queue.poll();
            
            if(poll.x == len - 1 && poll.y == len - 1){
                System.out.println(poll.cost);
                result = Math.min(result, poll.cost);
            }
            
            for(int dir = 0; dir < 4; dir++){
                int nx = poll.x + dx[dir];
                int ny = poll.y + dy[dir];
                
                if(isIn(nx, ny, len) && board[nx][ny] != 1){
                    int newCost = poll.cost;
                    if(poll.dir == -1 || poll.dir == dir){  //직선
                        newCost += 100;
                    }else{  //곡선
                        newCost += 600; //곡선 가격 + 직선 가격
                    }
                    
                    if(!visited[nx][ny][dir] || board[nx][ny] >= newCost){
                        visited[nx][ny][dir] = true;
                        board[nx][ny] = newCost;
                        queue.add(new Point(nx, ny, dir, newCost));
                    }
                }
            }
        }
        
        return result;
    }
    
    private static boolean isIn(int nx, int ny, int len){
        return nx >= 0 && ny >= 0 && nx < len && ny < len;
    }
}

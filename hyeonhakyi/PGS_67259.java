package ex0310;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/*
* 0비어 있는칸 1벽으로 채워짐
* 출발점(0,0) 도착점(N-1,N-1)
* 직선 도로 상하 or 좌우
* 직선 100원 코너 500원
* 최소비용 bfs
*
*
*/
public class PGS_67259 {
    private static class Car{
        int x;
        int y;
        int dir;
        int value;
        public Car(int x,int y,int dir,int value){
            this.x = x;
            this.y = y;
            this.dir = dir;
            this.value = value;
        }
    }
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static int min,n;
    private static int[][][] arr;
    public int solution(int[][] board){
        min = Integer.MAX_VALUE;
        n = board.length;
        boolean[][] visited = new boolean[n][n];
        arr = new int[4][n][n];

        for(int x = 0; x < 4; x++){
            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    arr[x][i][j] = Integer.MAX_VALUE;
                }
            }
        }

        bfs(visited,board);

        return min;
    }//main end

    private static void bfs(boolean[][] visited,int[][] board){
        Queue<Car> que = new LinkedList<>();
        que.offer(new Car(0,0,-1,0));
        visited[0][0] = true;

        while(!que.isEmpty()){
            Car car = que.poll();
            int nowX = car.x;
            int nowY = car.y;
            int dir = car.dir;
            int value = car.value;

            if(nowX == n-1 && nowY == n-1){
                min = Math.min(min,value);
            }

            for(int d = 0; d < 4; d++){
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];
                int nextValue = value;

                if(dir == -1){
                    nextValue += 100;
                }else if(dir == d){
                    nextValue += 100;
                }else{
                    nextValue += 600;
                }

                if(nx >= 0 && ny >= 0 && nx < n && ny < n && board[nx][ny] != 1){
                    if(!visited[nx][ny] || arr[d][nx][ny] >= nextValue) {
                        visited[nx][ny] = true;
                        arr[d][nx][ny] = nextValue;
                        que.offer(new Car(nx, ny, d, nextValue));
                    }
                }
            }
        }
    }//bfs end
}//class end

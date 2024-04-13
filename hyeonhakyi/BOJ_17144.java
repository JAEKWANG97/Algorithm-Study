package ex0322;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_17144 {
    private static class Dust{
        int x;
        int y;
        int value;
        public Dust(int x,int y,int value){
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    private static int[][] arr;
    private static int r,t,c,upX,downX;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static Queue<Dust> que = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[r][t];
        boolean visited = false;

        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < t; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());

                 if(arr[i][j] == -1 && !visited){
                    upX = i;
                    downX = i + 1;
                    visited = true;
                }
            }
        }

       for(int i = 0 ; i < c; i++){
            check();
            bfs();
            Rotate();
       }

        int result = 0;
        for(int i = 0; i < r; i++){
            for(int j = 0; j < t; j++){
                if(arr[i][j] != -1 && arr[i][j] != 0){
                    result += arr[i][j];
                }
            }
        }
        System.out.println(result);
    }//main end

    private static void bfs(){
        while (!que.isEmpty()){
            Dust dust = que.poll();
            int nowX = dust.x;
            int nowY = dust.y;
            int value = dust.value/5;
            if(dust.value < 5){
                continue;
            }
            int count = 0;
            for(int d = 0; d < 4; d++){
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nx >= 0 && ny >= 0 && nx < r && ny < t){
                    if(arr[nx][ny] != -1){
                        arr[nx][ny] += value;
                        count++;
                    }
                }
            }
            arr[nowX][nowY] -= (value * count);
        }
    }

    private static void Rotate(){
        for(int i = upX-1; i > 0; i--){
            arr[i][0] = arr[i-1][0];
        }
        for(int i = 0; i < t-1; i++){
            arr[0][i] = arr[0][i+1];
        }
        for(int i = 0; i < upX; i++){
            arr[i][t-1] = arr[i+1][t-1];
        }
        for(int i = t-1; i > 1; i--){
            arr[upX][i] = arr[upX][i-1];
        }
        arr[upX][1] = 0;

        for(int i = downX+1; i < r-1; i++){
            arr[i][0] = arr[i+1][0];
        }
        for(int i = 0; i < t-1; i++){
            arr[r-1][i] = arr[r-1][i+1];
        }
        for(int i = r-1; i > downX; i--){
            arr[i][t-1] = arr[i-1][t-1];
        }
        for(int i = t-1; i > 1; i--){
            arr[downX][i] = arr[downX][i-1];
        }
        arr[downX][1] = 0;
    }//Rotate end

    private static void check(){
        for(int i = 0; i < r; i++){
            for(int j = 0; j < t; j++){
                if(arr[i][j] != -1 && arr[i][j] != 0){
                    que.offer(new Dust(i,j,arr[i][j]));
                }
            }
        }
    }//check end
}//class end

package ex0311;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {

    private static class Ice{
        int x;
        int y;
        int value;
        public Ice(int x,int y,int value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }
    private static int n,m,time;
    private static int[][] arr;
    private static boolean[][] visited;
    private static Queue<Ice> que = new LinkedList<>();
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs();
    }//main end

    private static void bfs() {
        Queue<Ice> cur = new LinkedList<>();
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(arr[i][j] != 0) {
                    cur.offer(new Ice(i, j,arr[i][j]));
                }
            }
        }

        while(!cur.isEmpty()) {
            Ice ice = cur.poll();
            int nowX = ice.x;
            int nowY = ice.y;
            int nowValue = ice.value;
            int count = 0;

            for(int d = 0; d < 4; d++) {
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if(arr[nx][ny] == 0) {
                        count++;
                    }
                }
            }
            if(nowValue - count <= 0) {
                que.offer(new Ice(nowX,nowY,0));
            }else {
                que.offer(new Ice(nowX,nowY,nowValue-count));
            }
        }

        time++;
        value();

        if(check() >= 2) {
            System.out.println(time);
        }else if(check() == 0){
            System.out.println(0);
        }else {
            bfs();
        }
    }//bfs end

    private static int check() {
        int count = 0;
        Queue<Ice> check = new LinkedList<>();
        visited = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                if(!visited[i][j] && arr[i][j] != 0) {
                    check.offer(new Ice(i, j,arr[i][j]));
                    search(check);
                    count++;
                }
            }
        }
        return count;
    }//check end

    private static void search(Queue<Ice> check){
        while(!check.isEmpty()){
            Ice ice = check.poll();
            int nowX = ice.x;
            int nowY = ice.y;
            int nowValue = ice.value;

            for(int d = 0; d < 4; d++){
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if(!visited[nx][ny] && arr[nx][ny] != 0){
                        visited[nx][ny] = true;
                        check.offer(new Ice(nx,ny,0));
                    }
                }
            }
        }
    }//search end

    private static void value() {
        while(!que.isEmpty()) {
            Ice ice = que.poll();
            int nowX = ice.x;
            int nowY = ice.y;

            arr[nowX][nowY] = ice.value;
        }
    }//value end
}//class end
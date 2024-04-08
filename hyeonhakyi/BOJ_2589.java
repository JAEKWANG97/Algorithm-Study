package ex0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
    private static class Node{
        int x;
        int y;
        int len;
        public Node(int x,int y,int len){
            this.x = x;
            this.y = y;
            this.len = len;
        }
    }
    private static int l,w,max;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static char[][] arr;
    private static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        w = Integer.parseInt(st.nextToken());
        arr = new char[l][w];

        for(int i = 0; i < l; i++){
            String s = br.readLine();
            for(int j = 0; j < w; j++){
                arr[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < l; i++){
            for(int j = 0; j < w; j++){
                if(arr[i][j] == 'L'){
                    visited = new boolean[l][w];
                    bfs(i,j,0);
                }
            }
        }
        System.out.println(max);
    }//main end;

    private static void bfs(int x,int y,int cnt){
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x,y,cnt));
        visited[x][y] = true;

        while (!que.isEmpty()){
            Node now = que.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowCount = now.len;

            for(int d = 0; d < 4; d++){
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nx >= 0 && ny >= 0 && nx < l && ny < w){
                    if(arr[nx][ny] =='L' && !visited[nx][ny]){
                        visited[nx][ny] = true;
                        que.offer(new Node(nx,ny,nowCount+1));
                        max = Math.max(max,nowCount+1);
                    }
                }
            }
        }
    }//bfs end
}//class end

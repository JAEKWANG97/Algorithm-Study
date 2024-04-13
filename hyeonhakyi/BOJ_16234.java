package ex0321;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16234 {
    private static class Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    private static boolean check;
    private static int n,l,r,diff;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    private static ArrayList<Node> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        int day = 0;
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while (true){
            check = false;
            visited = new boolean[n][n];

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(!visited[i][j]){
                        bfs(i,j);
                    }
                }
            }

            if(!check){
                break;
            }else {
                day++;
            }
        }
        System.out.println(day);
    }//main end

    private static void bfs(int x,int y){
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(x,y));
        list.add(new Node(x,y));
        visited[x][y] = true;

        while(!que.isEmpty()){
            Node now = que.poll();
            int nowX = now.x;
            int nowY = now.y;

            for(int d = 0; d < 4; d++){
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                    diff = Math.abs(arr[nowX][nowY] - arr[nx][ny]);
                    if(l <= diff && diff <= r){
                        if(!visited[nx][ny]){
                            check = true;
                            que.offer(new Node(nx,ny));
                            list.add(new Node(nx,ny));
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
        }
        priceChek();
    }//bfs end

    private static void priceChek(){
        int sum = 0;
        for(int i = 0; i < list.size(); i++){
            Node node = list.get(i);
            sum += arr[node.x][node.y];
        }
        for(int i = 0; i < list.size(); i++){
            Node node = list.get(i);
            arr[node.x][node.y] = sum/list.size();
        }
        list.clear();
    }
}//class end

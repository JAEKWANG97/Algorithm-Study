package ex0412;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class BOJ_2665 {
    private static class Node implements Comparable<Node> {
        int x;
        int y;
        int count;
        int pass;
        public Node(int x,int y,int count,int pass){
            this.x = x;
            this.y = y;
            this.count = count;
            this.pass = pass;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.pass,o.pass);
        }
    }
    private static int n,result;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < n; j++){
                arr[i][j] = s.charAt(j) -'0';
            }
        }
        bfs();
        System.out.println(result);
    }//main end

    private static void bfs(){
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.offer(new Node(0,0,0,0));
        visited[0][0] = true;

        while(!que.isEmpty()){
            Node now = que.poll();

            if(now.x == n-1 && now.y == n-1){
                result = now.pass;
                return;
            }


            for(int d = 0; d < 4; d++){
                int nx = now.x + dx[d];
                int ny = now.y + dy[d];

                if(!check(nx,ny))continue;
                if(visited[nx][ny])continue;
                visited[nx][ny] = true;
                if(arr[nx][ny] == 0){
                    que.offer(new Node(nx,ny,now.count+1,now.pass+1));
                }else{
                    que.offer(new Node(nx,ny,now.count+1,now.pass));
                }
            }
        }
    }//bfs end

    private static boolean check(int x,int y){
        return x >= 0 && y >= 0 && x < n && y < n;
    }
}//class end

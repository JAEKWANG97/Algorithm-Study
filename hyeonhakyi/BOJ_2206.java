package ex0308;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206{
    private static class Node{
        int x;
        int y;
        int z;
        int count;
        public Node(int x,int y,int z,int count){
            this.x = x;
            this.y = y;
            this.z = z;
            this.count = count;
        }
    }
    private static int n,m,min;
    private static int[][] arr;
    private static boolean[][][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m][2];
        min = Integer.MAX_VALUE;

        for(int i = 0; i < n; i++){
            String s = br.readLine();
            for(int j = 0; j < m; j++){
                arr[i][j] = s.charAt(j) - '0';
            }
        }
        dfs(0,0);
        if(min == Integer.MAX_VALUE){
            System.out.println(-1);
        }else {
            System.out.println(min);
        }
    }//main end

    private static void dfs(int x,int y){
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x,y,0,1));
        visited[x][y][0] = true;

        while(!q.isEmpty()) {
            Node now = q.poll();
            int nowX = now.x;
            int nowY = now.y;
            int nowZ = now.z;
            int count = now.count;

            if (nowX == n - 1 && nowY == m - 1) {
                min = Math.min(min, count);
            }

            for (int d = 0; d < 4; d++) {
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if(visited[nx][ny][nowZ])continue;
                    if(arr[nx][ny] == 1){
                        if(nowZ == 1) continue;
                        visited[nx][ny][nowZ] = true;
                        q.offer(new Node(nx,ny,1,count+1));
                    }else{
                        visited[nx][ny][nowZ] = true;
                        q.offer(new Node(nx,ny,nowZ,count+1));
                    }
                }
            }
        }
    }//dfs end
}//class end

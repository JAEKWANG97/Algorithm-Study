package ex0401;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_21609 {
    private static class Block implements Comparable<Block>{
        int x;
        int y;
        int size;
        int rainbow;

        @Override
        public int compareTo(Block o) {
            return 0;
        }
    }
    private static int[] dx = {-1,-1,-1,0,1,1,1,0};
    private static int[] dy = {-1,0,1,1,1,0,-1,-1};
    private static int n,m;
    private static int[][] arr;
    private static PriorityQueue<Block> que = new PriorityQueue<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                if(arr[i][j] != 0 && arr[i][j] != -1) {
                    bfs(i,j);
                }
            }
        }




    }//main end

    private static void bfs(int x,int y) {
        boolean[][] visited = new boolean[n][m];

        while(!que.isEmpty()){
            Block now = que.poll();
            int nowX = now.x;
            int nowY = now.y;
            int size = now.size;
            int rainbowSize = now.rainbow;

            for(int d = 0; d < 8; d++) {
                int nx = nowX + dx[d];
                int ny = nowY + dy[d];

                if(nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if(!visited[nx][ny] && arr[nx][ny] == arr[x][y] && arr[nx][ny] != -1) {

                        visited[nx][ny] = true;
                    }else if(!visited[nx][ny] && arr[nx][ny] == arr[x][y] && arr[nx][ny] != -1 && arr[nx][ny] == 0) {

                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }//bfs end
}

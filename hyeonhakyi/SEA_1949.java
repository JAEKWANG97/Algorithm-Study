package ex0302;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SEA_1949 {
    private static int n,k,max,count;
    private static int[][] arr;
    private static boolean[][] visited;
    private static int[] dx = {-1,1,0,0};
    private static int[] dy = {0,0,-1,1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            arr = new int[n][n];
            visited = new boolean[n][n];
            max = Integer.MIN_VALUE;
            count = 0;

            for(int i = 0; i < n; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < n; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());

                    max = Math.max(max,arr[i][j]);
                }
            }

            for(int i = 0; i < n; i++){
                for(int j = 0; j < n; j++){
                    if(arr[i][j] == max){
                        visited[i][j] = true;
                        dfs(i,j,k,arr[i][j],1);
                        visited[i][j] = false;
                    }
                }
            }
            System.out.println("#" + tc + " " + count);
        }//test_case end
    }//main end

    private static void dfs(int x,int y, int k, int height,int cnt){
        count = Math.max(count,cnt);

        for(int d = 0; d < 4; d++){
            int nx = x + dx[d];
            int ny = y + dy[d];

            if(nx >= 0 && ny >= 0 && nx < n && ny < n){
                if(!visited[nx][ny]){
                    if(k == 0){
                        if(arr[nx][ny] < height) {
                            visited[nx][ny] = true;
                            dfs(nx, ny, k, arr[nx][ny], cnt + 1);
                            visited[nx][ny] = false;
                        }
                    }else{
                        if(arr[nx][ny] < height){
                            visited[nx][ny] = true;
                            dfs(nx,ny,k,arr[nx][ny],cnt+1);
                            visited[nx][ny] = false;
                        }else if(arr[nx][ny] - k < height){
                            visited[nx][ny] = true;
                            dfs(nx,ny,0,height -1 ,cnt+1);
                            visited[nx][ny] = false;
                        }
                    }
                }
            }
        }
    }
}//class end

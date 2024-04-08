package eunsung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
    static int n,m;
    static int[][] map;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {1,0,-1,0};
    static int result = 0;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while(true){
            int result = check();

            if(result>=2){
                break;
            }else if(result==0){
                year = 0;
                break;
            }
            bfs();
            year++;
        }

        System.out.println(year);
    }

    // 빙산 1년마다 높이 바꾸기
    private static void bfs(){
        Queue<int[]> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] > 0) {
                    queue.add(new int[]{i, j});
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()){
            int[] p = queue.poll();
            int sea = 0;

            for (int i = 0; i < 4; i++) {
                int nx = p[0] + dx[i];
                int ny = p[1] + dy[i];

                if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                    if (!visited[nx][ny] && map[nx][ny] == 0) {
                        sea++;
                    }
                }
            }

            if (map[p[0]][p[1]] - sea < 0) {
                map[p[0]][p[1]] = 0; // 각 칸에 저장된 높이는 0보다 더 줄어들지 않기 때문에 0보다 작아지는 경우 0을 저장
            } else {
                map[p[0]][p[1]] -= sea;
            }
        }

    }

    // check (두개로 쪼개지는지)
    private static int check(){
        boolean[][] visited = new boolean[n][m];
        int cnt = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if(!visited[i][j] && map[i][j]>0){ // 연결되지 않은 빙하가 있으면
                    dfs(i,j,visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    // 빙산 덩어리 dfs
    public static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
                if (!visited[nx][ny] && map[nx][ny] > 0) {
                    dfs(nx, ny, visited);
                }
            }
        }
    }

}
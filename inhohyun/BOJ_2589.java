import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ_2589 {
    static class xy{
        int x, y;

        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n, m;

    static Set<Integer> answer = new HashSet<>();

    static int[][] visited;

    static int[] dx = {0,0,-1,1}, dy = {1,-1,0,0};

    public static void main(String[] args) throws IOException {
        init();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new int[n][m];
        char[][] map = new char[n][m];

        for(int i = 0; i <n; i++) {
            map[i] = br.readLine().toCharArray();
        }
//		print(map);

        int maxValue = 0;
        int total = 0;
        for(int i = 0; i <n; i++) {
            for(int j = 0; j <m; j++) {
                if(map[i][j] == 'L') {
                    visited = new int[n][m]; //방문 초기화
                    maxValue = bfs(i,j, map);
                    total = Math.max(maxValue, total);
                }
            }
        }

        System.out.println(total-1);
    }

    static int bfs(int x, int y, char[][] map) {
        int len = 0;
        Queue<xy> q = new ArrayDeque<>();
        q.add(new xy(x,y));
        visited[x][y] = 1;

        while(!q.isEmpty()) {
            xy cicj = q.poll();

            for(int d = 0; d <4; d++) {
                int nx = cicj.x + dx[d];
                int ny = cicj.y + dy[d];

                if(0 > nx || nx >= n || ny < 0 || ny >=m) continue;

                if(visited[nx][ny] == 0 && map[nx][ny] == 'L') {
                    visited[nx][ny] = visited[cicj.x][cicj.y] + 1;
                    len = Math.max(len, visited[nx][ny]);

                    q.add(new xy(nx, ny));
                }
            }
        }
        return len;
    }


    static void print(char[][] map) {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j <m; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }
}

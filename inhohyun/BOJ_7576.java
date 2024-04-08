import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    static class xy{
        int x, y;
        xy(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    static int n, m, map[][];
    static int[] dx = {0,0,1,-1}, dy = {1,-1,0,0};
    static int cnt;
    static Queue<xy> q = new ArrayDeque<>();
    public static void main(String[] args) throws Exception {
        init();

        solution();
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for(int i = 0; i  < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    static void solution() {
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < m; j++) {
                if(map[i][j] == 1) {
                    //큐에 담았다가 한 번에 호출?
                    q.add(new xy(i,j));
                }
            }
        }
        bfs();
        int max = 0;
        // map에 0이 남아있으면 -1출력
        for(int i = 0; i<n; i++) {
            for(int j = 0; j < m; j++) {
                if(max < map[i][j]) {
                    max = map[i][j];
                }

                if(map[i][j] == 0) {
                    System.out.println(-1);
                    System.exit(0);
                }
            }
            cnt = Math.max(cnt, max);
        }

        //아니면 map의 수중 가장 큰 수를 출력
        System.out.println(max - 1);

    }


    static void bfs() {

        while(!q.isEmpty()) {
            xy cicj = q.poll();
            for(int d = 0; d < 4; d++) {
                int nx = cicj.x + dx[d];
                int ny = cicj.y + dy[d];

                if(nx <0 || nx >=n || ny <0 || ny >= m) {
                    continue;
                }

                if(map[nx][ny] ==0) {
                    //누적합 식으로 저장하기
                    map[nx][ny] = map[cicj.x][cicj.y] + 1;
                    q.add(new xy(nx, ny));
                }

            }
        }



    }
}
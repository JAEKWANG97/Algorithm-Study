
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2573 {
    static class xy {
        int x, y;

        xy(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m, map[][];
    static List<xy> ice;
    /*
     * 빙산의 좌표 모두 저장하기
     */
    static int[] dx = { 0, 0, 1, -1 }, dy = { 1, -1, 0, 0 };

    public static void main(String[] args) throws IOException {
        init();

        int answer = solution();
        System.out.println(answer);

    }

    static int solution() {
        int time = 0;
        int cnt = 0;
        while ((cnt = SeparateNum()) < 2) { // 빙산이 2이상이면 종료
            if (cnt == 0) { // 그 전에 빙산이 없어지면 그냥 종료
                time = 0;
                break;
            }

            melt();
            time++;
        }

        return time;

    }

    // 1씩 감소
    static void melt() {
        Queue<xy> q = new ArrayDeque<>();

        boolean[][] visited = new boolean[n][m];
        for (int i = 0; i < n; i++) { // 얼음의 좌표들 저장
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0) {
                    q.offer(new xy(i, j));
                    visited[i][j] = true;
                }
            }
        }

        while (!q.isEmpty()) {
            xy cicj = q.poll();

            int seaNum = 0; // 인접 바다의 수
            for (int d = 0; d < 4; d++) {
                int nx = cicj.x + dx[d];
                int ny = cicj.y + dy[d];

                if (!is(nx, ny))
                    continue;

                if (!visited[nx][ny] && map[nx][ny] == 0) {
                    seaNum++;
                }
            }

            if (map[cicj.x][cicj.y] - seaNum < 0) {
                map[cicj.x][cicj.y] = 0;
            } else {
                map[cicj.x][cicj.y] -= seaNum;
            }

        }

    }

    // 두 빙산이 분리되었는지 파악
    public static int SeparateNum() {
        boolean[][] visited = new boolean[n][m];

        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] != 0 && !visited[i][j]) {
                    dfs(i, j, visited);
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void dfs(int x, int y, boolean[][] visited) {
        visited[x][y] = true;

        int nx, ny;
        for (int i = 0; i < 4; i++) {
            nx = x + dx[i];
            ny = y + dy[i];

            if (!is(nx, ny)) {
                continue;
            }

            if (map[nx][ny] != 0 && !visited[nx][ny]) {
                dfs(nx, ny, visited);
            }
        }
    }

    static void init() throws IOException {
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

        ice = new ArrayList<>();

    }

    static boolean is(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }
        return true;
    }
}

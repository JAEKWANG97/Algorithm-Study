import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_15685 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int cnt = Integer.parseInt(br.readLine());

        boolean[][] visited = new boolean[101][101];
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int t = 0; t < cnt; t++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            int gen = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            visited[y][x] = true;
            x += dx[dir];
            y += dy[dir];
            visited[y][x] = true;
            list.add(dir);
            for (int tc = 0; tc < gen; tc++) {
                for (int idx = list.size() - 1; idx > -1; idx--) {
                    dir = (list.get(idx) + 1) % 4;
                    x += dx[dir];
                    y += dy[dir];
                    visited[y][x] = true;
                    list.add(dir);
                }
            }
            //System.out.println(list);
        }
        int ans = 0;
        for (int y = 0; y < 100; y++) {
            for (int x = 0; x < 100; x++) {
                if (visited[y][x] && visited[y + 1][x] && visited[y][x + 1] && visited[y + 1][x + 1]) {
                    ans++;
                }
            }
        }
        System.out.println(ans);

    }
}

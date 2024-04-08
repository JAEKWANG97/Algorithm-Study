import java.io.*;
import java.util.*;

public class BOJ_15685 {
    static int N;
    static int[] dx = {1, 0, -1, 0};
    static int[] dy = {0, -1, 0, 1};
    static boolean[][] map;

    public static void solve(int x, int y, int d, int g) {
        List<Integer> list = new ArrayList<>();
        list.add(d);
        for (int i = 1; i <= g; i++) {
            for (int j = list.size() - 1; j >= 0; j--) {
                list.add((list.get(j) + 1) % 4);
            }
        }
        map[y][x] = true;
        for (Integer dir : list) {
            y += dy[dir];
            x += dx[dir];
            map[y][x] = true;
        }
    }

    public static int findBox() {
        int result = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                if (visitDot(i, j)) {
                    result++;
                }
            }
        }
        return result;
    }

    public static boolean visitDot(int i, int j) {
        if (map[i][j] && map[i + 1][j] && map[i][j + 1] && map[i + 1][j + 1]) {
            return true;
        } else {
            return false;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        map = new boolean[101][101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            solve(x, y, d, g);
        }

        bw.write(String.valueOf(findBox()));
        br.close();
        bw.close();
    }
}
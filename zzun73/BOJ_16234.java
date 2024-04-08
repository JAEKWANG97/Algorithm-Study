import java.io.*;
import java.util.*;

public class BOJ_16234 {
    static int N, L, R, answer;
    static int[][] map;
    static boolean isMoved;
    static boolean[][] visited;
    static Deque<Pos> deque;
    static List<Pos> list;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void helper() {
        while (search()) {
            answer++;
        }
    }

    static boolean search() {
        visited = new boolean[N][N];
        isMoved = false;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    bfs(i, j);
                }
            }
        }
        return isMoved;
    }

    static void bfs(int x, int y) {
        list = new ArrayList<>();
        deque = new ArrayDeque<>();
        deque.add(new Pos(x, y));
        list.add(new Pos(x, y));
        visited[x][y] = true;

        int teamSum = map[x][y];
        while (!deque.isEmpty()) {
            Pos cur = deque.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > N - 1 || visited[nx][ny] || !isPossibleDiff(cur.x, cur.y, nx, ny)) {
                    continue;
                }
                visited[nx][ny] = true;
                teamSum += map[nx][ny];
                list.add(new Pos(nx, ny));
                deque.add(new Pos(nx, ny));
            }
        }

        if (list.size() > 1) {
            isMoved = true;
        }
        update(teamSum / list.size());
    }

    static void update(int value) {
        for (Pos pos : list) {
            map[pos.x][pos.y] = value;
        }
    }

    static boolean isPossibleDiff(int x1, int y1, int x2, int y2) {
        return (L <= Math.abs(map[x1][y1] - map[x2][y2]) && Math.abs(map[x1][y1] - map[x2][y2]) <= R);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        helper();

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
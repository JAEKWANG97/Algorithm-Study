import java.io.*;
import java.util.*;


public class BOJ_2573 {
    static int N, M;
    static int[][] board, height;
    static boolean[][] visited;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};
    static ArrayDeque<Pos> deque;

    static class Pos {
        int x, y;

        Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean helper() {
        while (!deque.isEmpty()) {
            Pos cur = deque.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || board[nx][ny] > 0) {
                    continue;
                }
                height[cur.x][cur.y]++;
            }
        }

        updateIceHeight();
        return calcIceSet();
    }

    static void updateIceHeight() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                board[i][j] -= height[i][j];
                height[i][j] = 0;
                if (board[i][j] < 0) {
                    board[i][j] = 0;
                }
            }
        }
    }

    static boolean calcIceSet() {
        visited = new boolean[N][M];
        int count = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0 && !visited[i][j]) {
                    markIce(i, j);
                    count++;
                    if (count >= 2) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static void markIce(int x, int y) {
        if (visited[x][y]) {
            return;
        }

        visited[x][y] = true;
        for (int i = 0; i < dx.length; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || visited[nx][ny] || board[x][y] == 0) {
                continue;
            }
            markIce(nx, ny);
        }
    }

    private static void findIce() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] > 0) {
                    deque.add(new Pos(i, j));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        height = new int[N][M];
        visited = new boolean[N][M];
        deque = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            year++;

            findIce();

            if (deque.isEmpty()) {
                year = 0;
                break;
            }

            if (helper()) {
                break;
            }
        }
        bw.write(String.valueOf(year));

        bw.close();
        br.close();
    }
}
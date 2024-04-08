import java.io.*;
import java.util.*;

public class BOJ_7576 {
    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int N, M, day;
    static int[][] arr;
    static int[] dx = new int[]{-1, 1, 0, 0};
    static int[] dy = new int[]{0, 0, -1, 1};
    static Deque<Pos> deque;

    public static int helper() {
        while (!deque.isEmpty()) {
            Pos cur = deque.poll();

            for (int i = 0; i < dx.length; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx > M - 1 || ny < 0 || ny > N - 1 || arr[nx][ny] != 0) {
                    continue;
                }

                arr[nx][ny] = arr[cur.x][cur.y] + 1;
                deque.add(new Pos(nx, ny));
            }
        }

        return calcResult();
    }

    static int calcResult() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0) {
                    return -1;
                }
                day = Math.max(day, arr[i][j]);
            }
        }

        return day - 1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        day = 0;
        arr = new int[M][N];
        deque = new ArrayDeque<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    deque.add(new Pos(i, j));
                }
            }
        }

        bw.write(String.valueOf(helper()));

        br.close();
        bw.close();
    }
}


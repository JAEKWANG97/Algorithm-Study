import java.io.*;
import java.util.*;

class Pos {
    int x, y;

    public Pos() {
    }

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class BOJ_3055 {
    static int R, C;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};
    static Pos hedgehog;
    static char[][] map;
    static int[][] waterTime, hedgehogTime;
    static Deque<Pos> deque;
    static boolean[][] visited;

    static int helper() {
        // 물 먼저 이동
        while (!deque.isEmpty()) {
            Pos cur = deque.poll();
            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || map[nx][ny] != '.' || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                waterTime[nx][ny] = waterTime[cur.x][cur.y] + 1; // 물 도착 시간 최신화
                deque.add(new Pos(nx, ny));
            }
        }

        // 그다음 고슴도치 이동
        visited = new boolean[R][C];
        deque.add(hedgehog);
        visited[hedgehog.x][hedgehog.y] = true;

        while (!deque.isEmpty()) {
            Pos cur = deque.poll();
            if (map[cur.x][cur.y] == 'D') { // 비버 굴 도착
                return hedgehogTime[cur.x][cur.y];
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || visited[nx][ny] || map[nx][ny] == 'X' || map[nx][ny] == '*' || hedgehogTime[cur.x][cur.y] + 1 >= waterTime[nx][ny] && waterTime[nx][ny] != 0) {
                    continue;
                }
                visited[nx][ny] = true;
                hedgehogTime[nx][ny] = hedgehogTime[cur.x][cur.y] + 1; // 고슴도치 도착 시간 최신화
                deque.add(new Pos(nx, ny));
            }
        }

        return -1; // D 도달 못했을 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        hedgehog = new Pos();
        map = new char[R][C];
        waterTime = new int[R][C];
        hedgehogTime = new int[R][C];
        visited = new boolean[R][C];
        deque = new ArrayDeque<>();


        for (int i = 0; i < R; i++) {
            char[] row = br.readLine().toCharArray();

            for (int j = 0; j < C; j++) {
                map[i][j] = row[j];
                if (row[j] == 'S') {
                    hedgehog = new Pos(i, j);
                } else if (row[j] == '*') {
                    deque.add(new Pos(i, j));
                    visited[i][j] = true;
                    waterTime[i][j] = 0;
                }
            }
        }

        int res = helper();
        bw.write(String.valueOf(res == -1 ? "KAKTUS" : res));
        br.close();
        bw.close();
    }
}

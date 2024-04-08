import java.io.*;
import java.util.*;

public class BOJ_4179 {
    static int R, C, answer;
    static int[] dx = new int[]{0, 0, 1, -1};
    static int[] dy = new int[]{1, -1, 0, 0};
    static boolean[][] visited;

    static class Pos {
        int x, y, time;

        public Pos(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void helper(Deque<Pos> fire, Deque<Pos> person, int[][] times, char[][] map) {
        while (!fire.isEmpty()) {
            Pos fireCur = fire.poll();
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + fireCur.x;
                int ny = dy[i] + fireCur.y;
                if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || map[nx][ny] == '#' || visited[nx][ny]) {
                    continue;
                }
                times[nx][ny] = fireCur.time + 1;
                visited[nx][ny] = true;
                fire.add(new Pos(nx, ny, times[nx][ny]));
            }
        }

        visited = new boolean[R][C];
        visited[person.peek().x][person.peek().y] = true;
        while (!person.isEmpty()) {
            Pos personCur = person.poll();
            if (isEscape(personCur.x, personCur.y) && personCur.time < times[personCur.x][personCur.y]) {
                answer = personCur.time + 1;
                return;
            }
            for (int i = 0; i < 4; i++) {
                int nx = dx[i] + personCur.x;
                int ny = dy[i] + personCur.y;
                if (nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || map[nx][ny] != '.' || visited[nx][ny] || personCur.time >= times[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                person.add(new Pos(nx, ny, personCur.time + 1));
            }
        }
    }

    private static boolean isEscape(int x, int y) {
        if (x == 0 || x == R - 1 || y == 0 || y == C - 1) {
            return true;
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        char[][] board = new char[R][C];
        int[][] times = new int[R][C];

        visited = new boolean[R][C];
        Deque<Pos> person = new ArrayDeque<>();
        Deque<Pos> fire = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            Arrays.fill(times[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i < R; i++) {
            char[] row = br.readLine().toCharArray();
            for (int j = 0; j < C; j++) {
                board[i][j] = row[j];

                if (row[j] == 'F') {
                    visited[i][j] = true;
                    times[i][j] = 0;
                    fire.add(new Pos(i, j, 0));
                } else if (row[j] == 'J') {
                    person.add(new Pos(i, j, 0));
                }
            }
        }
        answer = -1;
        helper(fire, person, times, board);
        bw.write(answer == -1 ? "IMPOSSIBLE" : String.valueOf(answer));
        br.close();
        bw.close();
    }
}
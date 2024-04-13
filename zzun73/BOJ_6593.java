import java.io.*;
import java.util.*;

public class BOJ_6593 {
    static int L, R, C;

    static class Pos {
        int z, x, y, time;

        public Pos() {
        }

        public Pos(int z, int x, int y, int time) {
            this.z = z;
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    static int helper(Pos start, Pos end, char[][][] building) {
        int[] dz = new int[]{0, 0, 0, 0, -1, 1};
        int[] dx = new int[]{0, 0, 1, -1, 0, 0};
        int[] dy = new int[]{1, -1, 0, 0, 0, 0};
        boolean[][][] visited = new boolean[L][R][C];
        Deque<Pos> deque = new ArrayDeque<>();
        deque.add(start);
        visited[start.z][start.x][start.y] = true;

        while (!deque.isEmpty()) {
            Pos cur = deque.poll();
            if (cur.z == end.z && cur.x == end.x && cur.y == end.y) {
                return cur.time;
            }
            for (int i = 0; i < dz.length; i++) {
                int nz = cur.z + dz[i];
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nz < 0 || nz > L - 1 || nx < 0 || nx > R - 1 || ny < 0 || ny > C - 1 || visited[nz][nx][ny] || building[nz][nx][ny] == '#') {
                    continue;
                }
                visited[nz][nx][ny] = true;
                deque.add(new Pos(nz, nx, ny, cur.time + 1));
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if (L == 0 && R == 0 && C == 0) {
                break;
            }
            Pos start = new Pos();
            Pos end = new Pos();
            char[][][] building = new char[L][R][C];
            for (int z = 0; z < L; z++) {
                for (int x = 0; x < R; x++) {
                    char[] row = br.readLine().toCharArray();
                    for (int y = 0; y < C; y++) {
                        building[z][x][y] = row[y];

                        if (building[z][x][y] == 'S') {
                            start = new Pos(z, x, y, 0);
                        } else if (building[z][x][y] == 'E') {
                            end = new Pos(z, x, y, 0);
                        }
                    }
                }
                br.readLine();
            }

            int answer = helper(start, end, building);
            if (answer == -1) {
                sb.append("Trapped!").append("\n");
            } else {
                sb.append("Escaped in ").append(answer).append(" minute(s).").append("\n");
            }
        }
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}
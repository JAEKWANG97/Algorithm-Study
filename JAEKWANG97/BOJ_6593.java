package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {

    static class Location {
        int x, y, z, time;

        public Location(int x, int y, int z, int time) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            if (L == 0 && R == 0 && C == 0) {
                break;
            }

            char[][][] map = new char[L][R][C];
            Location start = null;
            for (int i = 0; i < L; i++) {
                for (int j = 0; j < R; j++) {
                    String line = br.readLine();
                    for (int k = 0; k < C; k++) {
                        map[i][j][k] = line.charAt(k);
                        if (map[i][j][k] == 'S') {
                            start = new Location(i, j, k, 0);
                        }
                    }
                }
                br.readLine();
            }

            bfs(map, start, L, R, C, sb);
        }
        System.out.println(sb.toString());
    }

    static void bfs(char[][][] map, Location start, int L, int R, int C, StringBuilder sb) {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][][] visited = new boolean[L][R][C];
        int[] dx = {1, -1, 0, 0, 0, 0};
        int[] dy = {0, 0, 1, -1, 0, 0};
        int[] dz = {0, 0, 0, 0, 1, -1};

        queue.add(start);
        visited[start.x][start.y][start.z] = true;

        while (!queue.isEmpty()) {
            Location current = queue.poll();

            if (map[current.x][current.y][current.z] == 'E') {
                sb.append("Escaped in ").append(current.time).append(" minute(s).\n");
                return;
            }

            for (int i = 0; i < 6; i++) {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];
                int nz = current.z + dz[i];

                if (nx >= 0 && nx < L && ny >= 0 && ny < R && nz >= 0 && nz < C && !visited[nx][ny][nz]
                        && map[nx][ny][nz] != '#') {
                    queue.add(new Location(nx, ny, nz, current.time + 1));
                    visited[nx][ny][nz] = true;
                }
            }
        }
        sb.append("Trapped!\n");
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_6593 {
    private static class Pos {
        int z, y, x;
        public Pos(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        while (true) {
            st = new StringTokenizer(br.readLine());
            int Z = Integer.parseInt(st.nextToken());
            int Y = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            if (Z == 0 && Y == 0 && X == 0) break;
            int ez = 0, ey = 0, ex = 0;
            char[] floor;
            Queue<Pos> queue = new LinkedList<>();
            char[][][] map = new char[Z][Y][X];
            boolean[][][] visited = new boolean[Z][Y][X];
            for (int z = 0; z < Z; z++) {
                for (int y = 0; y < Y; y++) {
                    floor = br.readLine().toCharArray();
                    for (int x = 0; x < X; x++) {
                        if (floor[x] == 'S') {
                            visited[z][y][x] = true;
                            queue.add(new Pos(z, y, x));
                        }else if (floor[x] == 'E'){
                            ez = z;
                            ey = y;
                            ex = x;
                        }
                        map[z][y][x] = floor[x];
                    }
                }
                br.readLine();
            } // 입력 끝

            int[] dz = {0, 0, 1, -1, 0, 0};
            int[] dy = {1, -1, 0, 0, 0, 0};
            int[] dx = {0, 0, 0, 0, 1, -1};

            int depth = 0;
            Pos now;
            boolean flag = false;
            while (!queue.isEmpty()) {
                int qsize = queue.size();
                depth++;
                //System.out.println("depth: " + depth + ", " + qsize);
                while (qsize-- > 0) {
                    now = queue.poll();
                    //System.out.println(now.z + " " + now.y + " " + now.x);
                    if (now.z == ez && now.y == ey && now.x == ex) {
                        flag = true;
                        queue.clear();
                        break;
                    }
                    for (int d = 0; d < 6; d++) {
                        int nz = now.z + dz[d];
                        int ny = now.y + dy[d];
                        int nx = now.x + dx[d];
                        if (isIn(nz, ny, nx, Z, Y, X) && map[nz][ny][nx] != '#' && !visited[nz][ny][nx]) {
                            //System.out.println("nxt: " + nz + " " + ny + " " + nx);
                            visited[nz][ny][nx] = true;
                            queue.add(new Pos(nz, ny, nx));
                        }
                    }
                }
            }
            if (flag)
                sb.append("Escaped in ").append(depth-1).append(" minute(s).").append("\n");
            else
                sb.append("Trapped!").append("\n");
        }
        System.out.println(sb);
    }
    private static boolean isIn(int z, int y, int x, int Z, int Y, int X) {
        if (0 <= z && z < Z && 0 <= y && y < Y && 0 <= x && x < X) {
            return true;
        }
        return false;
    }
}

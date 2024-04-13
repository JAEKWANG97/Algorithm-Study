import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_4179 {

    private static class Poz {
        int y, x;
        public Poz(int y, int x) {
            this.y = y;
            this.x = x;
        }

        @Override
        public String toString() {
            return "위치: " + y + " " + x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        boolean[][] vidited = new boolean[Y][X];
        Queue<Poz> fire = new LinkedList<>();
        Queue<Poz> jihun = new LinkedList<>();
        char[][] map = new char[Y][X];

        for (int y = 0; y < Y; y++) {
            char[] tmp = br.readLine().toCharArray();
            for (int x = 0; x < X; x++) {
                if (tmp[x] == '#') {
                    map[y][x] = '#';
                }
                else if (tmp[x] == 'F') {
                    fire.add(new Poz(y, x));
                    map[y][x] = 'F';
                }
                else if (tmp[x] == 'J') {
                    jihun.add(new Poz(y, x));
                    vidited[y][x] = true;
                    map[y][x] = '.';
                }
                else {
                    map[y][x] = '.';
                }
            }
        }

        if (0 == jihun.peek().x || jihun.peek().x == X - 1 || 0 == jihun.peek().y || jihun.peek().y == Y-1) {
            System.out.println(1);
            return;
        }

        // 초기 지훈이 위치 + 불 + 벽 = 방문 처리, 불과 지훈이 위치는 visited 처리
        int time = 1;
        while (!jihun.isEmpty()) {
            //System.out.println(time);
            // 1. 불 먼저 퍼트리기
            spread(fire, map, vidited, false);
            // 2. 지훈이 탈출시키기
            if (spread(jihun, map, vidited, true)) {
                System.out.println(time+1);
                return;
            }
//            for (int y = 0; y < Y; y++)
//                System.out.println(Arrays.toString(map[y]));
            time++;
        }


        System.out.println("IMPOSSIBLE");

    }
    static boolean spread(Queue<Poz> queue, char[][]map, boolean[][] visited, boolean flag) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int Y = visited.length;
        int X = visited[0].length;
        Poz now;
        int qsize = queue.size();
        while (qsize-- > 0) {
            now = queue.poll();
            //System.out.println(now.y + " " + now.x);
            for (int d = 0; d < 4; d++) {
                int ny = now.y + dy[d];
                int nx = now.x + dx[d];
                if (0 <= ny && ny < Y && 0 <= nx && nx < X && map[ny][nx] == '.') {
                    //지훈이
                    if (flag) {
                        if (visited[ny][nx]) continue;
                        if ((flag && (ny == 0 || ny == Y - 1 || nx == 0 || nx == X - 1))) return true;
                        visited[ny][nx] = true;
                        queue.add(new Poz(ny, nx));
                    }
                    // 불
                    else {
                        map[ny][nx] = 'F';
                        queue.add(new Poz(ny, nx));
                    }

                }
            }
        }
        return false;
    }

}

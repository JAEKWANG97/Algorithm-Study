import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 {
    private static class Poz {
        int y, x;

        public Poz(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());

        char[][] map = new char[Y][X];
        Queue<Poz> water = new LinkedList<>();
        Queue<Poz> hog = new LinkedList<>();
        boolean[][] visited = new boolean[Y][X];

        for (int y = 0; y < Y; y++) {
            char[] tmp = br.readLine().toCharArray();
            for (int x = 0; x < X; x++) {
                if (tmp[x] == '*') {
                    water.add(new Poz(y, x));
                    visited[y][x] = true;
                }
                else if (tmp[x] == 'S') {
                    hog.add(new Poz(y, x));
                    visited[y][x] = true;
                }
                else if (tmp[x] == 'X') {
                    visited[y][x] = true;
                }
                map[y] = tmp;
            }
        }
        int ans = 0;
        boolean flag = true;
        while (!hog.isEmpty()) {
            ans++;
            // 물 퍼트리기
            spreadW(map, visited, water);

            // 고슴도치 이동
            if (moveHog(map, visited, hog)) {
                System.out.println(ans);
                flag = false;
                break;
            }
        }
        if (flag)
            System.out.println("KAKTUS");
    }
    private static void spreadW(char[][] map, boolean[][] visited, Queue<Poz> water) {
        int qsize = water.size();
        int Y = map.length;
        int X = map[0].length;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while (qsize-- > 0) {
            Poz w = water.poll();

            for (int d = 0; d < 4; d++) {
                int ny = w.y + dy[d];
                int nx = w.x + dx[d];

                if (0 <= ny && ny < Y && 0 <= nx && nx < X && !visited[ny][nx] && map[ny][nx] != 'D') {
                    visited[ny][nx] = true;
                    map[ny][nx] = '*';
                    water.add(new Poz(ny, nx));
                }
            }
        }
    }

    private static boolean moveHog(char[][] map, boolean[][] visited, Queue<Poz> hog) {
        int qsize = hog.size();
        int Y = map.length;
        int X = map[0].length;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        while (qsize-- > 0) {
            Poz h = hog.poll();

            for (int d = 0; d < 4; d++) {
                int ny = h.y + dy[d];
                int nx = h.x + dx[d];

                if (0 <= ny && ny < Y && 0 <= nx && nx < X && !visited[ny][nx]) {
                    if (map[ny][nx] == 'D')
                        return true;

                    visited[ny][nx] = true;
                    map[ny][nx] = '*';
                    hog.add(new Poz(ny, nx));
                }
            }
        }

        return false;
    }
}

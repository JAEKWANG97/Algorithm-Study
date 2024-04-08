import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_3055 { //백준 3055 탈출 - 50분
    static int R, C;
    static char[][] map;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static class Point {
        int x, y, dist;

        public Point(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new char[R][C];
        for (int i = 0; i < R; i++) {
            String line = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        int answer = solution();
        if (answer==-1) {
        	System.out.println("KAKTUS");
        } else {
        	System.out.println(answer);
        }
       
    }

    static int solution() {
        Queue<Point> water = new ArrayDeque<>();
        Queue<Point> dochi = new ArrayDeque<>();

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '*') {
                    // 물 차있는 곳
                    water.offer(new Point(i, j, -1));
                } else if (map[i][j] == 'S') {
                    // 고슴도치 위치
                    dochi.offer(new Point(i, j, 0));
                }
            }
        }

        int result = bfs(water, dochi);
        return result > 0 ? result : -1;
    }

    static int bfs(Queue<Point> water, Queue<Point> dochi) {
        Queue<Point> queue = new ArrayDeque<>();
        queue.addAll(water);
        queue.addAll(dochi);

        while (!queue.isEmpty()) {
            Point current = queue.poll();
            int x = current.x;
            int y = current.y;
            int dist = current.dist;

            // 고슴도치가 비버굴에 닿으면 종료
            if (dist > -1 && map[x][y] == 'D') {
                return dist;
            }

            for (int d = 0; d < 4; d++) {
                int nx = x + dx[d];
                int ny = y + dy[d];

                if (0 <= nx && nx < R && 0 <= ny && ny < C && map[nx][ny] != 'X') {
                    // 물이고 다음칸이 돌이 아니고 비버굴도 아니면 이동
                    if (dist == -1 && map[nx][ny] == '.' && map[nx][ny] != 'D') {
                        queue.offer(new Point(nx, ny, -1));
                        map[nx][ny] = '*';
                    } else if (dist > -1 && (map[nx][ny] == '.' || map[nx][ny] == 'D')) {
                        if (map[nx][ny] == 'D') {
                            return dist + 1;
                        }
                        queue.offer(new Point(nx, ny, dist + 1));
                        map[nx][ny] = 'S';
                    }
                }
            }
        }
        return -1;
    }
}

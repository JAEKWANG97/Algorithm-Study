import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2589 {
    private static class Poz  {
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
        for (int y = 0; y < Y; y++) {
            map[y] = br.readLine().toCharArray();
        }
        int answer = -1;
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                if (map[y][x] == 'L') {
                    //System.out.print("시작: " + y + " " + x + " = ");
                    int tmp = findMaxLength(y, x, map, Y, X);
                    //System.out.println(tmp);
                    answer = Math.max(answer, tmp);
                }
            }
        }
        System.out.println(answer);
    }
    private static int findMaxLength(int y, int x, char[][]map, int Y, int X) {
        boolean[][] visited = new boolean[Y][X];
        visited[y][x] = true;
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        Queue<Poz> queue = new LinkedList<>();
        queue.add(new Poz(y, x));
        int depth = 0;
        Poz now;
        while (!queue.isEmpty()) {
            depth++;
            int qsize = queue.size();
            while (qsize-- > 0) {
                now = queue.poll();
                for (int d = 0; d < 4; d++) {
                    int ny = now.y + dy[d];
                    int nx = now.x + dx[d];
                    if (0 <= ny && ny < Y && 0 <= nx && nx < X && !visited[ny][nx] && map[ny][nx] == 'L') {
                        visited[ny][nx] = true;
                        queue.add(new Poz(ny, nx));
                    }
                }
            }
        }
        return depth-1;
    }
}

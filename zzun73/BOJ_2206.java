import java.io.*;
import java.util.*;


public class BOJ_2206 {
    static int N, M;
    static int[][] map;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static class Person {
        int x, y, distance;
        boolean useSmash;

        public Person(int x, int y, int distance, boolean useSmash) {
            this.x = x;
            this.y = y;
            this.distance = distance;
            this.useSmash = useSmash;
        }
    }

    static int helper() {
        Deque<Person> deque = new ArrayDeque<>();
        deque.add(new Person(0, 0, 1, true));
        boolean[][][] visited = new boolean[N][M][2];
        visited[0][0][0] = visited[0][0][1] = true;

        while (!deque.isEmpty()) {
            Person cur = deque.poll();

            if (cur.x == N - 1 && cur.y == M - 1) {
                return cur.distance;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1) {
                    continue;
                }

                if (!visited[nx][ny][0] && map[nx][ny] == 0) {
                    visited[nx][ny][0] = true;
                    deque.add(new Person(nx, ny, cur.distance + 1, cur.useSmash));
                }
                if (!visited[nx][ny][1] && map[nx][ny] == 0 && cur.useSmash) {
                    visited[nx][ny][1] = true;
                    deque.add(new Person(nx, ny, cur.distance + 1, true));
                } else if (map[nx][ny] == 1 && cur.useSmash) {
                    visited[nx][ny][1] = true;
                    deque.add(new Person(nx, ny, cur.distance + 1, false));
                }

            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        bw.write(String.valueOf(helper()));
        br.close();
        bw.close();
    }
}

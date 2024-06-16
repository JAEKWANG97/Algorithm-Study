import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static char[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        init();
        bfs(0, 0);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);
            }
        }
    }

    private static void bfs(int x, int y) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(new Node(x, y));
        visited[x][y] = true;
        int[] dx = { -1, 0, 1, 0 };
        int[] dy = { 0, 1, 0, -1 };
        int count = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int s = 0; s < size; s++) {
                Node node = queue.poll();

                if (node.x == N - 1 && node.y == M - 1) {
                    System.out.println(count);
                    return;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = node.x + dx[d];
                    int ny = node.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == '0' || visited[nx][ny])
                        continue;

                    queue.offer(new Node(nx, ny));
                    visited[nx][ny] = true;
                }
            }
            count++;
        }
    }
}

import java.io.*;
import java.util.*;

public class BOJ_2589 {
    static int N, M;
    static char[][] map;
    static int[] dx = new int[]{1, -1, 0, 0};
    static int[] dy = new int[]{0, 0, 1, -1};

    static class Node {
        int x, y, distance;

        public Node(int x, int y, int distance) {
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }

    static int helper(int x, int y) {
        Deque<Node> deque = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];
        int maxDiff = Integer.MIN_VALUE;
        deque.add(new Node(x, y, 0));
        visited[x][y] = true;

        while (!deque.isEmpty()) {
            Node cur = deque.poll();
            maxDiff = Math.max(maxDiff, cur.distance);

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];
                if (nx < 0 || nx > N - 1 || ny < 0 || ny > M - 1 || visited[nx][ny] || map[nx][ny] == 'W') {
                    continue;
                }

                visited[nx][ny] = true;
                deque.add(new Node(nx, ny, cur.distance + 1));
            }
        }
        return maxDiff;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }

        int answer = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'L') { // 육지인 경우 탐색진행
                    answer = Math.max(answer, helper(i, j));
                }
            }
        }
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
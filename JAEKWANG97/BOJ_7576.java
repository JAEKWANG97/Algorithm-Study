package backjoon.bfsOrDfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_7576 {
    public static void main(String[] args) throws IOException {

        // init
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        int[][] map = new int[n][m];

        //bfs에서 쓸 queue
        Queue<int[]> queue = new ArrayDeque<>();

        //설익은 토마토 갯수
        int target = 0;

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                int item = Integer.parseInt(st.nextToken());
                map[i][j] = item;
                if (item == 1) {
                    queue.add(new int[]{i, j, 0}); // 시간을 추적하는 요소 추가
                } else if (item == 0) {
                    target += 1;
                }
            }
        }

        System.out.println(bfs(queue, map, n, m, target));

    }

    static int bfs(Queue<int[]> queue, int[][] map, int n, int m, int target) {
        int[] deltaX = new int[]{0, 1, 0, -1};
        int[] deltaY = new int[]{-1, 0, 1, 0};
        int lastTime = 0;
        while (!queue.isEmpty()) {

            int[] cur = queue.poll();

            int curX = cur[0];
            int curY = cur[1];
            int curTime = cur[2];

            lastTime = Math.max(lastTime, curTime);

            for (int i = 0; i < 4; i++) {
                int nx = curX + deltaX[i];
                int ny = curY + deltaY[i];

                if (isIn(nx, ny, n, m) && map[nx][ny] == 0) {
                    map[nx][ny] = 1;
                    queue.add(new int[]{nx, ny, curTime + 1});
                    target -= 1;
                }

            }
        }

        if (target > 0) {
            return -1;
        }
        return lastTime;
    }

    static boolean isIn(int x, int y, int n, int m) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }
}

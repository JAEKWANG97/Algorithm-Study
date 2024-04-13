package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_16928 {

    class Point {
        int x;
        int count = 0;

        public Point(int x) {
            this.x = x;
        }

        public Point(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        new BOJ_16928().solution();
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] ladders = new int[101];
        int[] snakes = new int[101];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());

            int ladderNum = Integer.parseInt(st.nextToken());
            int targetNum = Integer.parseInt(st.nextToken());

            ladders[ladderNum] = targetNum;
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int snakeNum = Integer.parseInt(st.nextToken());
            int targetNum = Integer.parseInt(st.nextToken());

            snakes[snakeNum] = targetNum;
        }

        boolean[] visited = new boolean[101];

        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(1));
        visited[1] = true;

        while (!queue.isEmpty()) {

            Point now = queue.poll();

            if (now.x == 100) {
                bw.write(now.count + "\n");
                break;
            }

            for (int i = 1; i <= 6; i++) {

                Point next = new Point(now.x + i, now.count + 1);

                if (100 < next.x || visited[next.x]) {
                    continue;
                }

                visited[next.x] = true;

                if (ladders[next.x] != 0) {
                    next.x = ladders[next.x];
                    visited[next.x] = true;
                }
                if (snakes[next.x] != 0) {
                    next.x = snakes[next.x];
                    visited[next.x] = true;
                }
                queue.offer(next);
            }
        }
        bw.flush();
        bw.close();
    }
}
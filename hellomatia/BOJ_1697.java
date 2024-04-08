package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {

    class Point {
        int x;
        int count;

        Point(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, K;

    public static void main(String[] args) throws IOException {
        new BOJ_1697().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
    }

    private int calcResult() {
        Queue<Point> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[100_001];
        visited[N] = true;
        queue.offer(new Point(N, 0));
        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.x == K) {
                return now.count;
            }

            if (now.x + 1 <= 100_000 && !visited[now.x + 1]) {
                visited[now.x + 1] = true;
                queue.offer(new Point(now.x + 1, now.count + 1));
            }

            if (now.x * 2 <= 100_000 && !visited[now.x * 2]) {
                visited[now.x * 2] = true;
                queue.offer(new Point(now.x * 2, now.count + 1));
            }

            if (0 <= now.x - 1 && !visited[now.x - 1]) {
                visited[now.x - 1] = true;
                queue.offer(new Point(now.x - 1, now.count + 1));
            }
        }
        return -1;
    }

    public void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}

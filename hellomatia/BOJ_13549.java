package hellomatia;
import java.io.*;
import java.util.*;

public class BOJ_13549 {

    class Point {
        int x;
        int count;
        Point(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    static BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[100_001];
        Arrays.fill(dp, Integer.MAX_VALUE);

        Queue<Point> queue = new LinkedList<>();

        dp[N] = 0;
        queue.offer(new Point(N, 0));

        int minCount = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Point now = queue.poll();

            if (now.x == K) {
                minCount = Math.min(now.count, minCount);
            }

            int forward = now.x + 1;
            int backward = now.x - 1;
            int teleport = now.x * 2;

            if (teleport <= 100_000 && dp[teleport] > now.count) {
                dp[teleport] = now.count;
                queue.offer(new Point(teleport, now.count));
            }
            if (forward <= 100_000 && dp[forward] > now.count + 1) {
                dp[forward] = now.count + 1;
                queue.offer(new Point(forward, now.count + 1));
            }
            if (0 <= backward && dp[backward] > now.count + 1) {
                dp[backward] = now.count + 1;
                queue.offer(new Point(backward, now.count + 1));
            }
        }

        bw.write(minCount + "\n");
        bw.flush();
        bw.close();
    }

    public static void main (String[] args) throws IOException {
        new BOJ_13549().solution();
    }
}
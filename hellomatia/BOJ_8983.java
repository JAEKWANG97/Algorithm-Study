package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BOJ_8983 {

    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M, L;
    private TreeSet<Integer> hunter;
    private Queue<Point> queue;

    public static void main(String[] args) throws IOException {
        new BOJ_8983().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        hunter = new TreeSet<>();
        st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < M; i++) {
            hunter.add(Integer.parseInt(st.nextToken()));
        }

        queue = new ArrayDeque<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            queue.offer(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
    }

    private int calcResult() {
        int result = 0;

        while (!queue.isEmpty()) {
            Point now = queue.poll();
            int dist1 = calcDist(hunter.lower(now.x + 1), now);
            int dist2 = calcDist(hunter.higher(now.x - 1), now);

            if (dist1 <= L || dist2 <= L) {
                result++;
            }
        }

        return result;
    }

    private int calcDist(Integer hunter, Point point) {
        if (hunter == null) {
            return L + 1;
        }
        return Math.abs(hunter - point.x) + point.y;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
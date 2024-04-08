package hellomatia;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class BOJ_17471 {

    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] population;
    private boolean[] areaA;

    private int result;

    private List<Integer>[] nearArea;

    public static void main(String[] args) throws IOException {
        new BOJ_17471().solution();
    }

    private void solution() throws IOException {
        init();
        devideArea(0, 1);
        printResult();
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = read();
        population = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = read();
        }

        nearArea = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nearArea[i] = new ArrayList<>();

            int count = read();
            for (int j = 0; j < count; j++) {
                nearArea[i].add(read());
            }
        }

        areaA = new boolean[N + 1];
        result = Integer.MAX_VALUE;
    }

    private void devideArea(int count, int start) {
        if (count > N / 2) return;

        if (canDevide()) {
            calcResult();
        }

        for (int i = start; i <= N; i++) {
            areaA[i] = true;
            devideArea(count + 1, i + 1);
            areaA[i] = false;
        }
    }

    private boolean canDevide() {
        boolean[] visited = new boolean[N + 1];
        // areaA
        Queue<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (areaA[i]) {
                queue.offer(i);
                visited[i] = true;
                break;
            }
        }

        if (queue.isEmpty()) return false;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : nearArea[now]) {
                if (visited[next] || !areaA[next]) continue;
                visited[next] = true;
                queue.offer(next);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!areaA[i]) {
                queue.offer(i);
                visited[i] = true;
                break;
            }
        }

        if (queue.isEmpty()) return false;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : nearArea[now]) {
                if (visited[next] || areaA[next]) continue;
                visited[next] = true;
                queue.offer(next);
            }
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }

    private void calcResult() {
        int areaACount = 0;
        int areaBCount = 0;
        for (int i = 1; i <= N; i++) {
            if (areaA[i]) {
                areaACount += population[i];
            } else {
                areaBCount += population[i];
            }
        }

        result = Math.min(result, Math.abs(areaACount - areaBCount));
    }

    private void printResult() throws IOException {
        if (result == Integer.MAX_VALUE) bw.write("-1\n");
        else bw.write(result + "\n");
    }

    private int read() throws IOException {
        int c, n = System.in.read() & 15;
        boolean isNegative = n == 13;
        if (isNegative)
            n = System.in.read() & 15;
        while ((c = System.in.read()) > 32)
            n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13)
            System.in.read();
        return isNegative ? ~n + 1 : n;
    }
}

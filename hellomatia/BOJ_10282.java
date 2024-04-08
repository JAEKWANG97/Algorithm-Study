package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_10282 {

    class Computer implements Comparable<Computer> {
        int num;
        int time;

        Computer(int num, int time) {
            this.num = num;
            this.time = time;
        }

        @Override
        public int compareTo(Computer o) {
            return time - o.time;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int n, d, c;
    private List<Computer>[] child;
    private int computerCount;
    private int lastTime;
    private int[] visited;

    public static void main(String[] args) throws IOException {
        new BOJ_10282().solution();
    }

    private void solution() throws IOException {
        int testCase = Integer.parseInt(bf.readLine());
        for (int i = 0; i < testCase; i++) {
            init();
            calcResult();
            printResult();
        }
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        n = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        child = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            child[i] = new ArrayList<>();
        }

        for (int i = 0; i < d; i++) {
            st = new StringTokenizer(bf.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());
            child[from].add(new Computer(to, time));
        }

        computerCount = 0;
        lastTime = 0;
    }

    private void calcResult() {
        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.offer(new Computer(c, 0));
        dist[c] = 0;

        while (!pq.isEmpty()) {
            Computer now = pq.poll();
            if (visited[now.num]) continue;
            computerCount++;
            visited[now.num] = true;
            for (Computer next : child[now.num]) {
                if (!visited[next.num] && dist[next.num] > now.time + next.time) {
                    dist[next.num] = now.time + next.time;
                    pq.offer(new Computer(next.num, dist[next.num]));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (dist[i] == Integer.MAX_VALUE) continue;
            lastTime = Math.max(dist[i], lastTime);
        }
    }

    private void printResult() throws IOException {
        bw.write(computerCount + " " + lastTime + "\n");
        bw.flush();
    }
}

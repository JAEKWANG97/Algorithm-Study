package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_18352 {

    class City implements Comparable<City> {

        int num;
        int cost;

        City(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(City o) {
            return cost - o.cost;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M, K, X;
    private List<Integer>[] nearCity;

    public static void main(String[] args) throws IOException {
        new BOJ_18352().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        nearCity = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nearCity[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            nearCity[from].add(to);
        }
    }

    private int[] calcResult() {
        PriorityQueue<City> pq = new PriorityQueue<>();

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new City(X, 0));

        while (!pq.isEmpty()) {

            City now = pq.poll();
            if (now.cost > dist[now.num]) continue;

            dist[now.num] = now.cost;

            for (int next : nearCity[now.num]) {
                pq.offer(new City(next, now.cost + 1));
            }
        }

        return dist;
    }

    public void printResult(int[] result) throws IOException {
        boolean find = false;
        for (int i = 1; i <= N; i++) {
            if (result[i] == K) {
                bw.write(i + "\n");
                find = true;
            }
        }
        if (find) return;
        bw.write("-1\n");
        bw.flush();
    }
}

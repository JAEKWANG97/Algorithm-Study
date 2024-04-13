package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_11657 {

    class City {

        int num;
        int cost;

        City(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private List<City>[] nearCity;

    public static void main(String[] args) throws IOException {
        new BOJ_11657().solution();
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

        nearCity = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            nearCity[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            nearCity[from].add(new City(to, cost));
        }
    }

    private long[] calcResult() {

        long[] dist = new long[N + 1];
        Arrays.fill(dist, 10_000_000_000L);

        dist[1] = 0;

        for (int i = 1; i < N; i++) {
            for (int j = 1; j <= N; j++) {
                for (City city : nearCity[j]) {
                    dist[city.num] = Math.min(dist[city.num], dist[j] + city.cost);
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            for (City city : nearCity[i]) {
                if (dist[city.num] > dist[i] + city.cost) {
                    dist[0] = -1;
                }
            }
        }

        return dist;
    }

    public void printResult(long[] result) throws IOException {
        if (nearCity[1].size() == 0) {
            for (int i = 2; i <= N; i++) {
                bw.write("-1\n");
            }
            return;
        }
        if (result[0] == -1) {
            bw.write("-1\n");
        } else {
            for (int i = 2; i <= N; i++) {
                if (result[i] == 10_000_000_000L) {
                    bw.write("-1\n");
                } else {
                    bw.write(result[i] + "\n");
                }
            }
        }
        bw.flush();
    }
}

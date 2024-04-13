package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1647 {

    class Road {
        int house1;
        int house2;
        int cost;

        public Road(int house1, int house2, int cost) {
            this.house1 = house1;
            this.house2 = house2;
            this.cost = cost;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private int N, M;
    private int[] parents;


    public static void main(String[] args) throws IOException {
        new BOJ_1647().solution();
    }

    private void solution() throws IOException {

        StringTokenizer st = new StringTokenizer(bf.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Road> roads = new PriorityQueue<>((o1, o2) -> {
            return o1.cost - o2.cost;
        });

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());

            int house1 = Integer.parseInt(st.nextToken());
            int house2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            roads.offer(new Road(house1, house2, cost));
        }

        parents = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parents[i] = i;
        }
        int minCost = 0;
        int maxCost = 0;

        while (!roads.isEmpty()) {
            Road now = roads.poll();

            if (find(now.house1) == find(now.house2)) {
                continue;
            }

            union(now.house1, now.house2);

            maxCost = Math.max(now.cost, maxCost);

            minCost += now.cost;
        }

        bw.write((minCost - maxCost) + "\n");
        bw.flush();
        bw.close();
    }

    private void union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x == y) return;

        if (x > y) parents[x] = y;
        else parents[y] = x;
    }

    private int find(int x) {
        if (parents[x] == x) return x;
        return find(parents[x]);
    }
}

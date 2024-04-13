package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_5972 {

    class Node implements Comparable<Node> {

        int num;
        int cost;

        Node(int num, int cost) {
            this.num = num;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return cost - o.cost;
        }
    }

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N, M;
    private Map<Integer, Node>[] nearNodes;

    public static void main(String[] args) throws IOException {
        new BOJ_5972().solution();
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

        nearNodes = new Map[N + 1];
        for (int i = 1; i <= N; i++) {
            nearNodes[i] = new HashMap<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            if (!nearNodes[from].containsKey(to) || nearNodes[from].get(to).cost > cost) {
                nearNodes[from].put(to, new Node(to, cost));
                nearNodes[to].put(from, new Node(from, cost));
            }
        }
    }

    private int calcResult() {
        PriorityQueue<Node> pq = new PriorityQueue<>();

        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        pq.offer(new Node(1, 0));

        while (!pq.isEmpty()) {

            Node now = pq.poll();
            if (now.cost >= dist[now.num]) continue;

            dist[now.num] = now.cost;

            for (Node next : nearNodes[now.num].values()) {
                pq.offer(new Node(next.num, now.cost + next.cost));
            }
        }

        return dist[N];
    }

    public void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}

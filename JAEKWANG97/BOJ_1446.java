import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Main {
    static class Node {
        int to;
        int weight;

        Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, D;
    static List<List<Node>> graph;
    static int[] dp;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;

    public static void main(String[] args) throws NumberFormatException, IOException {
        init();
        bw.write(dikstra() + "\n");
        bw.flush();
        bw.close();
    }

    private static void init() throws NumberFormatException, IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < D + 2; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, weight));
        }

    }

    private static int dikstra() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.weight, o2.weight));
        int[] dist = new int[D + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; 
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (cur.to == D) {
                return cur.weight;
            }

            if (cur.to > D) {
                continue;
            }

            if (dist[cur.to] < cur.weight) {
                continue;
            }

            for (Node next : graph.get(cur.to)) {
                if (next.to <= D && dist[next.to] > cur.weight + next.weight) {
                    dist[next.to] = cur.weight + next.weight;
                    pq.offer(new Node(next.to, dist[next.to]));
                }
            }

            if (cur.to + 1 <= D && dist[cur.to + 1] > cur.weight + 1) {
                dist[cur.to + 1] = cur.weight + 1;
                pq.offer(new Node(cur.to + 1, dist[cur.to + 1]));
            }
        }
        return dist[D];
    }

}

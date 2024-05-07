import java.io.*;
import java.util.*;

public class BOJ_1197 {
    static class Edge implements Comparable<Edge> {
        int start, target, cost;

        public Edge(int start, int target, int cost) {
            this.start = start;
            this.target = target;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    static int V, answer;
    static PriorityQueue<Edge> pq;
    static int[] root;

    static void init() {
        for (int i = 0; i < root.length; i++) {
            root[i] = i;
        }
    }

    static int find(int x) {
        if (x == root[x]) {
            return x;
        }
        return root[x] = find(root[x]);
    }

    static void union(int a, int b) {
        int pa = find(a);
        int pb = find(b);

        if (root[pa] < root[pb]) {
            root[pb] = pa;
        } else {
            root[pa] = pb;
        }
    }

    static void helper() {
        init();
        int edgeCount = 0;

        while (!pq.isEmpty()) {
            Edge cur = pq.poll();
            if (find(cur.start) != find(cur.target)) {
                answer += cur.cost;
                edgeCount++;
                union(cur.start, cur.target);
            }

            if (edgeCount == V - 1) {
                return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        pq = new PriorityQueue<>();
        answer = 0;
        st = new StringTokenizer(br.readLine(), " ");
        V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        root = new int[V + 1];

        while (E-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            pq.add(new Edge(A, B, C));
        }

        helper();

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
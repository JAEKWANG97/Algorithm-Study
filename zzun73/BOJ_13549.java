import java.io.*;
import java.util.*;

public class BOJ_13549 {
    private static final int INF = Integer.MAX_VALUE;
    static int N, K;
    static int size;
    static PriorityQueue<Node> pq;
    static int[] distance;

    static class Node implements Comparable<Node> {
        int val, time;

        public Node() {
        }

        public Node(int val, int time) {
            this.val = val;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }

    static void helper() {
        // 초기화 진행
        Arrays.fill(distance, INF);
        distance[N] = 0;
        pq.add(new Node(N, 0));

        while (!pq.isEmpty()) {
            Node cur = pq.poll();

            if (distance[cur.val] < cur.time) { // 연산 전 현재 시간이 더 오래 걸릴 경우 skip
                continue;
            }
            for (int i = 0; i < 3; i++) {
                Node next = new Node();
                switch (i) {
                    case 0: // +1 연산, 1초
                        next.val = cur.val + 1;
                        next.time = cur.time + 1;
                        break;
                    case 1: // -1 연산, 1초
                        next.val = cur.val - 1;
                        next.time = cur.time + 1;
                        break;
                    case 2: // *2 연산 0초
                        next.val = cur.val * 2;
                        next.time = cur.time;
                        break;
                }

                if (next.val < 0 || next.val > size - 1) { // 범위 밖 스킵
                    continue;
                }

                if (distance[next.val] > next.time) { // 최소 시간 갱신
                    distance[next.val] = next.time;
                    pq.add(new Node(next.val, next.time));
                }

            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        size = Math.max(N, K) * 2; // 2배 연산 고려하여 size 설정
        pq = new PriorityQueue<>();
        distance = new int[size];

        helper();

        bw.write(String.valueOf(distance[K]));
        br.close();
        bw.close();
    }
}

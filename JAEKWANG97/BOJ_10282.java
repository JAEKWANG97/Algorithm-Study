package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_10282 {

    static class Node implements Comparable<Node> {
        int to;
        int time;

        public Node(int to, int time) {
            this.to = to;
            this.time = time;
        }

        @Override
        public int compareTo(Node other) {
            return this.time - other.time;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 컴 개수
            int d = Integer.parseInt(st.nextToken()); // 의존성 개수
            int c = Integer.parseInt(st.nextToken()); // 해킹당한 컴퓨터 번호
            List<List<Node>> adjList = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                adjList.add(new ArrayList<>());
            }

            for (int j = 0; j < d; j++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int from = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());
                // 인덱스를 맞추기 위해 from과 to에서 1을 뺍니다.
                adjList.get(from - 1).add(new Node(to - 1, time));
            }

            int[] answer = bfs(adjList, n, c - 1);
            System.out.println(answer[0] + " " + answer[1]);
        }
    }

    private static int[] bfs(List<List<Node>> adjList, int n, int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.add(new Node(start, 0));

        int[] times = new int[n];
        Arrays.fill(times, Integer.MAX_VALUE);
        times[start] = 0;

        boolean[] visited = new boolean[n];
        int cnt = 0, maxTime = 0;

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            if (visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            maxTime = Math.max(maxTime, times[cur.to]);

            cnt += 1;
            for (Node next : adjList.get(cur.to)) {
                if (!visited[next.to] && times[next.to] > times[cur.to] + next.time) {
                    times[next.to] = times[cur.to] + next.time;
                    pq.add(new Node(next.to, times[next.to]));
                }
            }
        }

        return new int[]{cnt, maxTime};


    }
}

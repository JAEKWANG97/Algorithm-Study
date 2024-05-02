package ex0501;

import java.util.*;

public class PGS_72413 {
    static class Node implements Comparable<Node> {
        int idx;
        int weight;

        public Node(int idx, int weight) {
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight, o.weight);
        }
    }

    class Solution {
        static final int max = 20000001;
        static ArrayList<Node>[] list;

        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = max;
            list = new ArrayList[n + 1];

            for (int i = 0; i <= n; i++) {
                list[i] = new ArrayList<>();
            }

            for (int[] fare : fares) {
                list[fare[0]].add(new Node(fare[1], fare[2]));
                list[fare[1]].add(new Node(fare[0], fare[2]));
            }

            int[] startA = dijkstra(a, n);
            int[] startB = dijkstra(b, n);
            int[] startS = dijkstra(s, n);

            for (int i = 1; i <= n; i++) {
                answer = Math.min(answer, startS[i] + startA[i] + startB[i]);
            }

            return answer;
        }

        public static int[] dijkstra(int start, int n) {
            int[] dist = new int[n + 1];
            boolean[] visited = new boolean[n + 1];
            Arrays.fill(dist, max);
            PriorityQueue<Node> q = new PriorityQueue<>();
            q.offer(new Node(start, 0));
            dist[start] = 0;

            while (!q.isEmpty()) {
                Node now = q.poll();

                if (visited[now.idx]) continue;
                visited[now.idx] = true;

                for (Node next : list[now.idx]) {
                    if (dist[next.idx] > dist[now.idx] + next.weight) {
                        dist[next.idx] = dist[now.idx] + next.weight;
                        q.offer(new Node(next.idx, dist[next.idx]));
                    }
                }
            }
            return dist;
        }
    }
}

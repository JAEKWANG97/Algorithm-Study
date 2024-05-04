package ex0504;

import java.util.*;

public class PGS_12978 {
    class Node implements Comparable<Node>{
        int idx;
        int weight;
        public Node(int idx,int weight){
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o){
            return Integer.compare(this.weight,o.weight);
        }
    }

    class Solution {
        public int solution(int N, int[][] road, int K) {
            int answer = 1;

            ArrayList<Node>[] list = new ArrayList[N+1];;
            for(int i = 1; i <= N; i++){
                list[i] = new ArrayList();
            }

            for(int i = 0; i < road.length; i++){
                int from = road[i][0];
                int to = road[i][1];
                int value = road[i][2];

                list[from].add(new Node(to,value));
                list[to].add(new Node(from,value));
            }

            int[] dist = dijkstra(1,N,K,list);

            for(int i = 2; i <= N; i++){
                if(dist[i] <= K){
                    answer++;
                }
            }

            return answer;
        }

        int[] dijkstra(int start, int n, int k, ArrayList<Node>[] list){
            PriorityQueue<Node> que = new PriorityQueue<>();
            que.offer(new Node(start,0));
            boolean[] visited = new boolean[n+1];
            int[] dist = new int[n+1];
            Arrays.fill(dist,Integer.MAX_VALUE);
            dist[start] = 0;

            while(!que.isEmpty()){
                Node now = que.poll();

                if(visited[now.idx])continue;
                visited[now.idx] = true;

                for(Node next : list[now.idx]){
                    if(dist[next.idx] > dist[now.idx] + next.weight){
                        dist[next.idx] = dist[now.idx] + next.weight;
                        que.offer(new Node(next.idx,dist[next.idx]));
                    }
                }
            }
            return dist;
        }
    }
}

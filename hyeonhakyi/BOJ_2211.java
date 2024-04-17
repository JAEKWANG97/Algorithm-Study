package ex0417;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_2211 {
    private static class Node implements Comparable<Node>{
        int idx;
        int weight;
        public Node(int idx,int weight){
            this.idx = idx;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
    private static int n,m;
    private static ArrayList<Node>[] list;
    private static int[] dist;
    private static int[] answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList[n+1];
        for(int i = 1; i<= n; i++){
            list[i] = new ArrayList<>();
        }

        answer = new int[n+1];
        dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);
        dist[1] = 0;

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            list[from].add(new Node(to,value));
            list[to].add(new Node(from,value));
        }

        dijkstra();
        int cnt = 0;
        for(int i = 2; i <= n; i++){
            if(answer[i] != 0){
                sb.append(i + " " + answer[i] + "\n");
                cnt++;
            }
        }
        System.out.println(cnt);
        System.out.println(sb);
    }//main end

    private static void dijkstra(){
        PriorityQueue<Node> que = new PriorityQueue<>();
        boolean[] visited = new boolean[n+1];
        que.offer(new Node(1,0));

        while(!que.isEmpty()){
            Node now = que.poll();

            if(visited[now.idx])continue;
            visited[now.idx] = true;

            for(Node next : list[now.idx]){
                if(dist[next.idx] > dist[now.idx] + next.weight){
                    dist[next.idx] = dist[now.idx] + next.weight;
                    answer[next.idx] = now.idx;
                    que.offer(new Node(next.idx, dist[next.idx]));
                }
            }
        }
    }//dijkstra end
}//class end

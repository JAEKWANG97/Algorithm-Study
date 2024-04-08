import java.io.*;
import java.util.*;

public class BOJ_5972 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static class Node implements Comparable<Node>{
        int id;
        int cost;

        Node(int id, int cost){
            this.id = id;
            this.cost = cost;
        }


        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException{
        int N, M;
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        ArrayList<Node>[] edges = new ArrayList[N + 1];
        for (int i = 0; i < N + 1; i++){
            edges[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++){
            int A, B, C;
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());

            edges[A].add(new Node(B, C));
            edges[B].add(new Node(A, C));
        }

        int[] dist = new int[N+1];
        Arrays.fill(dist, (int) 1e9);
        dist[1] = 0;

        boolean[] visited = new boolean[N+1];
        visited[1] = true;

        for (Node n: edges[1]){
            pq.add(n);
            dist[n.id] = n.cost;
        }

        while(!pq.isEmpty()){
            Node minNode = pq.poll();

            for (Node n: edges[minNode.id]){
                if (visited[n.id]){
                    continue;
                }

                if (dist[n.id] > dist[minNode.id] + n.cost){
                    dist[n.id] = dist[minNode.id] + n.cost;
                    pq.add(new Node(n.id, dist[n.id]));
                }
            }

            visited[minNode.id] = true;
        }

//        System.out.println(Arrays.toString(dist));
        System.out.println(dist[N]);
    }
}

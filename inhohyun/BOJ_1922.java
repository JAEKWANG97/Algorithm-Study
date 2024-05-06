import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1922 {


    static  int parent[];
    static  ArrayList<Edge> graph;
    public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st;
            int total = 0;
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());

            parent = new int[N+1];
            graph = new ArrayList<>();

            for (int i = 1; i <= N; i++) {
                parent[i] = i;
            }

             for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());

                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());

                graph.add( new Edge(start,end,cost));
            }

            Collections.sort(graph,new Comparator<Edge>(){
                @Override
                public int compare(Edge o1, Edge o2) {
                    return o1.cost - o2.cost;
                }
            });

            for (int i = 0; i < graph.size() ; i++) {
                    Edge edge = graph.get(i);

                    if(find(edge.start) != find(edge.end)){
                        total += edge.cost;
                        union(edge.start, edge.end);
                    }
            }

        System.out.println(total);
    }

    public static void union(int x, int y){
            x = find(x);
            y = find(y);

            if(x != y){
                parent[y] = x;
            }
    }
        public static int find(int x){
            if(x == parent[x]){
                return x;
            }
            return parent[x] = find(parent[x]);
        }

    static class Edge{
        public int start;
        public int end;
        public int cost;

        public Edge(int start,int end, int cost){
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }
}

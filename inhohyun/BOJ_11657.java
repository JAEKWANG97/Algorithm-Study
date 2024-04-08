import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_11657 {
    static int N, M;
    static ArrayList<Node>[] adj;
    static long dist[];
    static boolean has_minus_cycle;
    static int INF = 6000000;
    static int Start = 1;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // initialize
        adj = new ArrayList[N + 1];
        dist = new long[N + 1];
        for(int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
            dist[i] = INF;
        }

        // input processing
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            adj[a].add(new Node(b, c));
        }

        bell(Start);

        // output processing
        if(has_minus_cycle) {
            System.out.println(-1);
        }else {
            for(int i = 1; i <= N; i++) {
                if(i != Start) {
                    if(dist[i] == INF) System.out.println(-1);
                    else System.out.println(dist[i]);
                }
            }
        }
    } // end of main

    private static void bell(int start) {
        dist[start] = 0;

        for(int i = 0; i < N - 1; i++) {	// 최대 방문할 수 있는 노드의 개수만큼
            for(int j = 1; j <= N; j++) {	// j점 주변에 있는 점들을 업데이트 할 수 있는지 확인
                for(int k =0; k < adj[j].size(); k++) {		// j점에서 출발할 수 있는 버스 노선
                    Node next = adj[j].get(k);

                    // 현재 버스 노선이 기존 최단 경로를 갱신할 수 있다면
                    if(dist[j] + next.weight < dist[next.dest] && dist[j] != INF) {
                        // 갱신
                        dist[next.dest] = dist[j] + next.weight;
                    }
                }
            }
        }

        // 최단 경로를 구했는데, 한 번 더 갱신이 일어난다면 minus_cycle 존재
        for(int j = 1; j <= N; j++) {	// j점 주변에 있는 점들을 업데이트 할 수 있는지 확인
            for(int k =0; k < adj[j].size(); k++) {		// j점에서 출발할 수 있는 버스 노선
                Node next = adj[j].get(k);

                // 현재 버스 노선이 기존 최단 경로를 갱신할 수 있다면
                if(dist[j] + next.weight < dist[next.dest] && dist[j] != INF) {
                    // 갱신
                    has_minus_cycle = true;
                    return;
                }
            }
        }


    }
}
class Node{
    int dest, weight;

    public Node(int dest, int weight) {
        this.dest = dest;
        this.weight = weight;
    }

}
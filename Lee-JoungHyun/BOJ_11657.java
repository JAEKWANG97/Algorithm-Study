import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657 {

    public static class Edge {
        int start, end, distance;
        public Edge(int start, int end, int distance) {
            this.start = start;
            this.end = end;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        final int Max = Integer.MAX_VALUE;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Edge> edges = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            edges.add(new Edge(s, d, t));
        }

        // 벨만 포드 : n-1만큼 모든 노드 순회해서 찾기
        long[] distance = new long[N + 1];
        Arrays.fill(distance, Max);
        distance[1] = 0;

        for (int i = 0; i < N - 1; i++) {
            for (Edge now : edges) {
                if (distance[now.start] == Max) continue;
                if (distance[now.start] + now.distance < distance[now.end])
                    distance[now.end] = distance[now.start] + now.distance;
            }
        }

        for (Edge now : edges) {
            if (distance[now.start] == Max) continue;
            if (distance[now.start] + now.distance < distance[now.end]) {
                System.out.println(-1);
                return;
            }
        }

        for (int i = 2; i < N+1; i++)
            System.out.println(distance[i] == Max ? -1 : distance[i]);
    }
}

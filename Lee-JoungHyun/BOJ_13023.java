import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_13023 {

    static int N, M;
    static ArrayList<Integer>[] graph;
    static boolean visited[], flag;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        graph = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            graph[s1].add(s2);
            graph[s2].add(s1);
        }
        for (int i = 0; i < N; i++) {
            visited = new boolean[N];
            visited[i] = true;
            //System.out.println("start: " + i);
            DFS(i, 1);
            visited[i] = false;
            if (flag) break;
        }
        System.out.println(flag ? 1 : 0);
    }
    static void DFS(int node, int cnt) {
        //System.out.println("now: " + node);
        if (cnt == 5 || flag) {
            flag = true;
            return;
        }
        for (int nxt : graph[node]) {
            if (!visited[nxt]) {
                visited[nxt] = true;
                DFS(nxt, cnt + 1);
                visited[nxt] = false;
            }
        }
       // System.out.println("짤");
    }
}

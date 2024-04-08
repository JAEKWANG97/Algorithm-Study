import java.io.*;
import java.util.*;

public class BOJ_13023 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static ArrayList<Integer>[] adjList;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        init();
        for (int i = 0; i < N; i++) {
            dfs(i, 1);
        }
        System.out.println(0); // 5명의 친구 관계를 찾지 못한 경우
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjList = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            adjList[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adjList[x].add(y);
            adjList[y].add(x);
        }

        visited = new boolean[N];
    }

    private static void dfs(int current, int depth) {
        if (depth == 5) {
            System.out.println(1);
            System.exit(0); // 프로그램 즉시 종료
        }

        visited[current] = true;

        for (int next : adjList[current]) {
            if (!visited[next]) {
                dfs(next, depth + 1);
            }
        }

        visited[current] = false; // 현재 노드의 방문을 되돌림
    }
}

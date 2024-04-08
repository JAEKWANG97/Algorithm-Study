import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_13023 {
    static ArrayList<Integer>[] arr;
    static int N;
    static boolean[] visited;

    private static void dfs(int start, int cnt) {
        if (cnt == 5) {
            System.out.println(1);
            System.exit(0);
        }
        visited[start] = true;
        for (int i = 0; i < arr[start].size(); i++) {
            int next = arr[start].get(i);
            if (!visited[next]) {
                dfs(next, cnt + 1);
            }
        }
        visited[start] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new ArrayList[N];
        visited = new boolean[N];
        
        for (int i = 0; i < N; i++) {
            arr[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }
        for (int i = 0; i < N; i++) {
            dfs(i,1);
        }
        System.out.println(0);
    }
}

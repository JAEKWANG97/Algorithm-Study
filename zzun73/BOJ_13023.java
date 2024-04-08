import java.io.*;
import java.util.*;

public class BOJ_13023 {
    static List<List<Integer>> list;
    static boolean[] visited;
    static int answer;

    public static void helper(int start, int depth) {
        if (depth >= 4) {
            answer = 1;
            return;
        }
        visited[start] = true;
        for (int next : list.get(start)) {
            if (!visited[next]) {
                helper(next, depth + 1);
            }
        }
        visited[start] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        visited = new boolean[N];
        answer = 0;
        for (int i = 0; i < N; i++) {
            list.add(new ArrayList<>(i));
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(a).add(b);
            list.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            helper(i, 0);
            if (answer == 1) {
                break;
            }
        }
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}

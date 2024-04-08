import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_14889 {
    // 걸린시간 : 30분
    static int n, S[][], min_answer;
    static boolean[] visited; // 방문한 팀과 방문 안한 팀으로나눔

    public static void main(String[] args) throws IOException {
        init(); // 초기화

        dfs(0, 0);

        System.out.println(min_answer);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        n = Integer.parseInt(br.readLine());
        visited = new boolean[n];
        min_answer = Integer.MAX_VALUE;
        S = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                S[i][j] = Integer.parseInt(st.nextToken());
            }
        }

    }

    static void dfs(int start, int depth) {
        if (depth == n / 2) {
            int pow1 = 0;
            int pow2 = 0;

            // 두 팀간의 차이 구하기
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i] && visited[j]) {
                        pow1 += S[i][j];
                    } else if (!visited[i] && !visited[j]) {
                        pow2 += S[i][j];
                    }
                }
            }

            min_answer = Math.min(min_answer, Math.abs(pow1 - pow2));
            return;

        }

        for (int i = start; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                dfs(i + 1, depth + 1);
                visited[i] = false;
            }
        }

    }

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main_1697_인호현 {
    // 50분
    static int n, k, min_answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        min_answer = bfs(n);


        System.out.println(min_answer);
    }

    static int bfs(int start) {
        Queue<Integer> q = new ArrayDeque<>();
        q.add(start);
        int time = 0;
        int[] dp = new int[100_001];

        dp[start] = 1;

        while (!q.isEmpty()) {
            int tmp = q.poll();
            if (tmp == k) { // 찾으면 나가기
                time = dp[tmp] - 1;
                return time;

            }

            // 아직 안 만든 수이고 범위안에 있으면 dp 갱신해주기
            if (tmp + 1 >= 0 && tmp + 1 <= 100_000 && dp[tmp + 1] == 0) {
                dp[tmp + 1] = dp[tmp] + 1;
                q.add(tmp + 1);
            }
            if (tmp - 1 >= 0 && tmp - 1 <= 100_000 && dp[tmp - 1] == 0) {
                dp[tmp - 1] = dp[tmp] + 1;
                q.add(tmp - 1);
            }

            if (tmp * 2 >= 0 && tmp * 2 <= 100_000 && dp[tmp * 2] == 0) {
                dp[tmp * 2] = dp[tmp] + 1;
                q.add(tmp * 2);
            }

        }
        return time;
    }

}

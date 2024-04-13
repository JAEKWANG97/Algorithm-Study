
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 쉬운 계단 수 - 2시간 10분
public class BOJ_10844 {
    static StringBuilder sb = new StringBuilder();
    static int N, count;
    static final int DIVIDE= 1_000_000_000;

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        count = 0;

        solve();
    }

    private static void solve() throws Exception {
        int dp[][] = new int[N+1][10];

        Arrays.fill(dp[1], 1); // 1자리 수는 자신만을 경우의 수로 같는다.
        dp[1][0] = 0;

        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 10; j++) {
                if (j < 9) dp[i][j] = dp[i-1][j+1] % DIVIDE;
                if (j > 0) dp[i][j] = (dp[i][j] + dp[i-1][j-1]) % DIVIDE;
            }
        }

        for (int i = 0; i < 10; i++) {
            count = (count + dp[N][i]) % DIVIDE;
        }

//		for (int i = 1; i <= 9; i++) {
//			dfs(1, i, i);
//		}

        sb.append(count);
    }

    private static void dfs(int cnt, int num2, int num) {
        if (cnt == N) {
            count = (count+1) % DIVIDE;
//			System.out.println(num);
            return;
        }

        if (num2 < 9) dfs(cnt+1, num2+1, num*10+num2+1);
        if (num2 > 0) dfs(cnt+1, num2-1, num*10+num2-1);
    }
}

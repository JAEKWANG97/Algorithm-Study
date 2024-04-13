import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2294 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        boolean[] num = new boolean[100001];
        int cnt = 0;

        List<Integer> coins = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (!num[coin]) {
                num[coin] = true;
                coins.add(coin);
                cnt++;
            }
        }

        int[] dp = new int[k + 1];
        Arrays.fill(dp, 100000);
        dp[0] = 0;
        // 코인 종류
        for (int i = 0; i < cnt; i++) {
            int coin = coins.get(i);
            // 가치
            for (int j = 1; j < k + 1; j++) {
                int use = 0;
                while (coin * use <= j) {
                    int nxt = dp[j - coin * use] + use;
                    if (dp[j] < nxt) {
                        break;
                    }
                    dp[j] = nxt;
                    use++;
                }
            }

        }

        if (dp[k] == 100000)
            System.out.println(-1);
        else
            System.out.println(dp[k]);
    }
}

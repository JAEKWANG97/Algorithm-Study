import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_11052 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            int value = Integer.parseInt(st.nextToken());
            // p 는 사용한 카드 갯수
            for (int p = N; p > -1; p--) {
                // 만들 수 있는 갯수 찾기
                int cnt = 0;
                // 해당 카드를 안쓰는 경우 ~ 다쓰는 경우?
                while (p >= cnt * i) {
                    dp[p] = Math.max(dp[p], dp[p - cnt * i] + value * cnt);
                    cnt++;
                }
            }
        }
        System.out.println(dp[N]);
    }
}

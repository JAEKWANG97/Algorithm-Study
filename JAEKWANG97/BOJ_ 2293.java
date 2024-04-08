import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_ 2293 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, K;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        init();
        dp();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
    }

    private static void dp() {
        int[] dp = new int[K + 1];
        dp[0] = 1;

        // 동전을 순회
        for (int coin : arr) {
            // 가능한 모든 합계 값에 대해
            for (int j = coin; j <= K; j++) {
                // 현재 동전을 사용하는 경우의 수를 업데이트
                dp[j] += dp[j - coin];
            }
        }

        // 최종 결과 출력
        System.out.println(dp[K]);
    }
}

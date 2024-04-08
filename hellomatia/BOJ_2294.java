package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2294 {

    public static void main(String[] args) throws IOException {
        new BOJ_2294().solution();
    }
    public void solution() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken()); // 동전의 종류 수
        int k = Integer.parseInt(st.nextToken()); // k원

        int[] dp = new int[k + 1];

        // 코인의 가치 입력받기
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(bf.readLine());
            // 해당 가치의 최소 코인 개수는 해당 코인이기에 1을 대입
            if (value > k) continue;
            dp[value] = 1;
        }


        for (int i = 1; i <= k; i++) {
            if (dp[i] == 0) dp[i] = Integer.MAX_VALUE;
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] == Integer.MAX_VALUE || dp[i - j] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i], dp[i - j] + dp[j]);
            }
        }

        if (dp[k] == Integer.MAX_VALUE) dp[k] = -1;

        bw.write(dp[k] + "\n");

        bw.flush();
        bw.close();
    }
}
package backjoon;

//계단은 한 번에 한 계단씩 또는 두 계단씩 오를 수 있다.
//즉, 한 계단을 밟으면서 이어서 다음 계단이나, 다음 다음 계단으로 오를 수 있다.
//연속된 세 개의 계단을 모두 밟아서는 안 된다. 단, 시작점은 계단에 포함되지 않는다.
//마지막 도착 계단은 반드시 밟아야 한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//6
//10
//20
//15
//25
//10
//20
public class BOJ_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] stairs = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            stairs[i] = Integer.parseInt(br.readLine());
        }

        // n이 1 또는 2인 경우를 위한 조건 처리
        if (n < 3) {
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += stairs[i];
            }
            System.out.println(sum);
            return;
        }

        int[] dp = new int[n + 1];
        dp[1] = stairs[1];
        dp[2] = stairs[1] + stairs[2];
        // 첫 번째 계단과 세 번째 계단의 합, 두 번째와 세 번째 계단의 합 중 최댓값
        dp[3] = Math.max(stairs[1] + stairs[3], stairs[2] + stairs[3]);
        // 무조건 2칸 오른 후 한칸을 가던 두 칸을 가던 함
        for (int i = 4; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + stairs[i - 1]) + stairs[i];
        }

        System.out.println(dp[n]);
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String num = br.readLine();

        //1. 현재위치의 숫자를 추가하는 경우 + 현재위치 바로 앞을 추가하는 경우
        //2. 내가 0인 경우는 제외 + 현재위치 바로 앞을 추가하는 경우
        //3. 숫자가 26이하인 경우 제외
        int answer = solution(num);
        System.out.println(answer % 1_000_000);
    }

    private static int solution(String num) {
        int len = num.length();
        int[] dp = new int[len + 1];

        dp[0] = 1;
        for (int i = 1; i <= len; i++) {
            int prev = num.charAt(i - 1) - '0';
            if (1 <= prev && prev <= 9) {
                dp[i] += dp[i - 1] % 1_000_000;
            }

            if (i == 1) {
                continue;
            }

            int prev2 = num.charAt(i - 2) - '0';
            if (prev2 == 0) {
                continue;
            }
            prev2 = prev2 * 10 + prev;
            if (1 <= prev2 && prev2 <= 26) {
                dp[i] += dp[i - 2];
            }
        }
        return dp[len];
    }
}

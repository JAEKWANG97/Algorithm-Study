import java.util.Scanner;

public class BOJ_2011 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String number = sc.next();

        if (number.charAt(0) == '0'){
            System.out.println(0);
            return;
        }

        number = '0' + number;

        int[] dp = new int[number.length()];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i < number.length(); i++) {
            int code1 = number.charAt(i) - '0';
            int code2 = (number.charAt(i - 1) - '0') * 10 + code1;
            if (code2 == 0){
                System.out.println(0);
                return;
            }

            if (code1 > 0 && code2 > 9 && code2 <= 26){
                dp[i] = dp[i-1] + dp[i-2];
            } else if (code1 > 0){
                dp[i] = dp[i-1];
            } else if (code2 > 9 && code2 <= 26){
                dp[i] = dp[i-2];
            }

            dp[i] %= 1000000;
        }
        System.out.println(dp[number.length() - 1]);
    }
}
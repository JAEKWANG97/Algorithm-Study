import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2011 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String tmp = br.readLine();
        char[] arr = tmp.toCharArray();
        int[][] dp = new int[arr.length][2];
        if (tmp.length() == 0 || arr[0] == '0') {
            System.out.println(0);
            return;
        }
        if (arr.length == 1) {
            System.out.println(1);
            return;
        }
        if (arr.length == 2) {
            if (makeInt(arr[0], arr[1]) <= 26)
                System.out.println(2);
            else
                System.out.println(1);
            return;
        }


        // 1. dp 0, 1 채우기
        dp[0][0] = 1;
        if (makeInt(arr[0], arr[1]) <= 26)
            dp[0][1] = 1;

        dp[1][0] = arr[1] != '0' ? 1 : 0;
        if (arr[1] != '0' && makeInt(arr[1], arr[2]) <= 26)
            dp[1][1] = 1;

        for (int i = 2; i < arr.length; i++) {
            if (arr[i] != '0') {
                dp[i][0] = (dp[i-1][0] + dp[i-2][1]) % 1000000;
                if (i < arr.length-1 && makeInt(arr[i], arr[i+1]) <= 26)
                    dp[i][1] = dp[i][0];
            }
            if (dp[i][0] == 0 && dp[i-1][1] == 0) {
                System.out.println(0);
                return;
            }
        }
//        for (int i = 0; i < arr.length; i++) {
//            System.out.print(Arrays.toString(dp[i]) + " ");
//        }
//        System.out.println();
        System.out.println(dp[arr.length-1][0] + dp[arr.length-2][1]);

    }
    private static int makeInt(char pre, char post) {
        return post - '0' + (pre - '0') * 10;
    }
}

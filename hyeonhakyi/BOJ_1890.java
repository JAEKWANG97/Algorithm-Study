package ex0413;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1890 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        long[][] dp = new long[n][n];

        for(int i = 0; i < n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dp[0][0] = 1;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                if(dp[i][j] == 0){
                    continue;
                }
                if(i == n-1 && j == n-1){
                    break;
                }

                if(arr[i][j] + i < n){
                    dp[arr[i][j] + i][j] += dp[i][j];
                }

                if(arr[i][j] + j < n){
                    dp[i][arr[i][j] + j] += dp[i][j];
                }
            }
        }
        System.out.println(dp[n-1][n-1]);
    }//main end
}//class end

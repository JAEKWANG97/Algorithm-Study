import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int result, n, k, money[];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        money = new int [n+1];
        for(int i = 1 ; i<=n ;i++) {
            int value = Integer.parseInt(br.readLine());
            money[i] = value;
        }
        int[] dp = new int[k+1];
        dp[0] = 1; // 중요 !!
        for(int i = 1 ; i<=n; i++){
            for(int j = 1; j<=k ;j++){
                if(money[i]<=j){
                    dp[j] += dp[j-money[i]];
                }
            }
        }
        System.out.println(dp[k]);
    }
}

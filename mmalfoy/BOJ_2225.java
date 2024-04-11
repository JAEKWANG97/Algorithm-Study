import java.io.*;

public class BOJ_2225 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int K = Integer.parseInt(s[1]);

        int dp[][] = new int[N+1][K+1];
        for(int i=1; i <= N; i++){
            for(int j=1; j <= K; j++){
                if(j == 1){
                    dp[i][j] = 1;
                } else if(i == 1){
                    dp[i][j] = j;
                } else {
                    dp[i][j] = (dp[i-1][j] + dp[i][j-1]) % 1000000000;
                }
            }
        }
        bw.write(String.valueOf(dp[N][K]));
        bw.flush();
        br.close();
    }
}
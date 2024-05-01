
// 합승 택시 요금 / 60분
class PGS_72413 {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = 100000*n;
        
        long[][] dp = new long[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                dp[i][j] = 100000*n;
            }
        }
        
        for (int[] fare : fares) {
            dp[fare[0]][fare[1]] = fare[2];
            dp[fare[1]][fare[0]] = fare[2];
        }
        
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (i == j) {
                        dp[i][j] = 0;
                        continue;
                    } 
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, (int)(dp[s][i] + dp[i][a] + dp[i][b]));
        }
        
        return answer;
    }
}
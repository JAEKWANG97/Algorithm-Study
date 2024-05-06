
// 배달 / 40분
class PGS_12978 {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[][] dp = new int[N+1][N+1];
        
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if(i == j) continue;
                dp[i][j] = 50 * 500001;
            }
        }
        
        for (int[] r : road) {
            dp[r[0]][r[1]] = Math.min(dp[r[0]][r[1]], r[2]);
            dp[r[1]][r[0]] = Math.min(dp[r[1]][r[0]], r[2]);
        }
        
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        for (int i = 1; i <= N; i++) {
            if (dp[1][i] <= K) answer++;
        }

        return answer;
    }
}
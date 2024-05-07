import java.util.ArrayList;

class Solution {
    static int[][] dp;
    static ArrayList<Integer>[] g;
    static boolean[] visited;
    
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        
        g = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }
        
        for (int[] edge : lighthouse) {
            g[edge[0]].add(edge[1]);
            g[edge[1]].add(edge[0]);
        }
        
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];
        
        dfs(1);
        answer = Math.min(dp[1][0], dp[1][1]);
        
        return answer;
    }
    
    static void dfs(int x) {
        visited[x] = true;
        dp[x][1] = 1;
        
        for (int i : g[x]) {
            if (!visited[i]) {
                dfs(i);
                
                dp[x][0] += dp[i][1]; 
                dp[x][1] += Math.min(dp[i][0], dp[i][1]); 
            }
        }
    }
}

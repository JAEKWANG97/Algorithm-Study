import java.util.*;

public class Solution {
    static List<Integer>[] adj;
    static int[][] dp;

    public static int solution(int n, int[][] lighthouse) {
        adj = new ArrayList[n + 1];
        dp = new int[n + 1][2]; // [][0] 꺼져 있을 때, [][1] 켜져 있을 때
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            Arrays.fill(dp[i], -1);
        }
        for (int[] edge : lighthouse) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }
        return Math.min(dfs(1, -1, 0), dfs(1, -1, 1));
    }

    private static int dfs(int node, int parent, int state) {
        if (dp[node][state] != -1) return dp[node][state];

        int result = (state == 1) ? 1 : 0;
        for (int child : adj[node]) {
            if (child == parent) continue; // 부모 노드로의 역방향 방문 방지
            if (state == 0) {
                // 현재 노드가 꺼져 있다면 자식은 반드시 켜져 있어야 함
                result += dfs(child, node, 1);
            } else {
                // 현재 노드가 켜져 있다면 자식은 켜거나 꺼도 됨 (더 작은 값을 선택)
                result += Math.min(dfs(child, node, 0), dfs(child, node, 1));
            }
        }
        dp[node][state] = result;
        return result;
    }

}

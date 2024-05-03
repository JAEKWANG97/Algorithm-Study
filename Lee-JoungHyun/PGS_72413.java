import java.util.*;

class Solution {

    private static final int INF = 200 * 100000 + 1; // 최대 비용

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] graph = new int[n + 1][n + 1];
        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            Arrays.fill(graph[i], INF);
            graph[i][i] = 0;
        }
        // 비용 입력
        for (int[] fare : fares) {
            int c = fare[0], d = fare[1], f = fare[2];
            graph[c][d] = graph[d][c] = f;
        }
        // 플로이드-와샬 알고리즘
        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (graph[i][j] > graph[i][k] + graph[k][j]) {
                        graph[i][j] = graph[i][k] + graph[k][j];
                    }
                }
            }
        }
        // 합승하는 지점을 변경하며 최소 비용 계산
        int answer = INF;
        for (int i = 1; i <= n; i++) {
            answer = Math.min(answer, graph[s][i] + graph[i][a] + graph[i][b]);
        }
        return answer;
    }
}

package backjoon.backtracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14899 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N;
    static int[][] ability;
    static int minScore = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        combination(0, 0, new boolean[N]);
        System.out.println(minScore);
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        ability = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                ability[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void combination(int start, int depth, boolean[] visited) {
        if (depth == N / 2) {
            simulate(visited);
            return;
        }

        for (int i = start; i < N; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(i + 1, depth + 1, visited);
                visited[i] = false;
            }
        }
    }

    private static void simulate(boolean[] visited) {
        int homeScore = 0;
        int otherScore = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i] && visited[j]) {
                    homeScore += ability[i][j];
                } else if (!visited[i] && !visited[j]) {
                    otherScore += ability[i][j];
                }
            }
        }

        minScore = Math.min(Math.abs(homeScore - otherScore), minScore);
    }

}

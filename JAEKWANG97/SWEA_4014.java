package swexpert;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SWEA_4014 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for (int tc = 1; tc <= T; tc++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken()); // 활주로 건설
            int[][] map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            System.out.println("#" + tc + " " + buildRunway(map, N, X));
        }
    }

    private static int buildRunway(int[][] map, int n, int x) {
        int count = 0;
        boolean[] visited = new boolean[n];

        // 가로 검사
        for (int i = 0; i < n; i++) {
            Arrays.fill(visited, false); // 매 줄 검사 시작 전에 visited 배열 초기화
            if (checkLine(map, visited, n, x, i, true)) {
                count++;
            }
        }

        // 세로 검사
        for (int j = 0; j < n; j++) {
            Arrays.fill(visited, false); // 매 줄 검사 시작 전에 visited 배열 초기화
            if (checkLine(map, visited, n, x, j, false)) {
                count++;
            }
        }

        return count;
    }

    private static boolean checkLine(int[][] map, boolean[] visited, int n, int x, int index, boolean isRow) {
        for (int i = 1; i < n; i++) {
            int prev = isRow ? map[index][i - 1] : map[i - 1][index];
            int cur = isRow ? map[index][i] : map[i][index];

            if (prev == cur) {
                continue; // 높이가 같으면 넘어감
            }

            // 높이 차이가 1보다 크면 경사로 건설 불가
            if (Math.abs(prev - cur) > 1) {
                return false;
            }

            if (prev > cur) { // 내리막
                for (int j = 0; j < x; j++) {
                    int nextIdx = i + j;
                    if (nextIdx >= n || (isRow ? map[index][nextIdx] : map[nextIdx][index]) != cur
                            || visited[nextIdx]) {
                        return false;
                    }
                    visited[nextIdx] = true;
                }
            } else { // 오르막
                for (int j = 1; j <= x; j++) {
                    int prevIdx = i - j;
                    if (prevIdx < 0 || (isRow ? map[index][prevIdx] : map[prevIdx][index]) != prev
                            || visited[prevIdx]) {
                        return false;
                    }
                    visited[prevIdx] = true;
                }
            }
        }
        return true;
    }
}

import java.io.*;
import java.util.*;

class SEA_4014 {
    static int T, N, X;
    static int[][] map;

    static boolean buildAble(int[] heights) {
        boolean[] built = new boolean[N];
        for (int i = 0; i < N - 1; i++) {
            int current = heights[i];
            int next = heights[i + 1];
            if (current == next) continue; // 같은 높이
            if (Math.abs(current - next) > 1) return false; // 높이차 1초과
            if (current > next) { // 내리막길
                for (int j = i + 1; j <= i + X; j++) {
                    if (j >= N || heights[j] != next || built[j]) return false;
                    built[j] = true;
                }
            } else { // 오르막길
                for (int j = i; j > i - X; j--) {
                    if (j < 0 || heights[j] != current || built[j]) return false;
                    built[j] = true;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            X = Integer.parseInt(st.nextToken());

            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int count = 0;
            // 행 검사
            for (int i = 0; i < N; i++) {
                if (buildAble(map[i])) count++;
            }
            // 열 검사
            for (int j = 0; j < N; j++) {
                int[] column = new int[N];
                for (int i = 0; i < N; i++) {
                    column[i] = map[i][j];
                }
                if (buildAble(column)) count++;
            }

            sb.append("#").append(t).append(" ").append(count).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

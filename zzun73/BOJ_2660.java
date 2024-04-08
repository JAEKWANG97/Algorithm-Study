import java.io.*;
import java.util.*;

public class BOJ_2660 {
    private static final int INF = 100_000_000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N + 1][N + 1];
        for (int[] row : arr) {
            Arrays.fill(row, INF);
        }

        while (true) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            if (v1 == -1 && v2 == -1) {
                break;
            }

            arr[v1][v2] = 1;
            arr[v2][v1] = 1;
        }

        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (i != j) {
                        arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                    }
                }
            }
        }

        int candidateScore = N;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 1; i <= N; i++) {
            int count = 0;
            for (int j = 1; j <= N; j++) {
                if (i != j && arr[i][j] != INF) {
                    count = Math.max(arr[i][j], count);
                }
            }
            candidateScore = Math.min(count, candidateScore);
            map.put(i, count);
        }

        List<Integer> candidates = new ArrayList<>();
        for (int key : map.keySet()) {
            if (map.get(key) == candidateScore) {
                candidates.add(key);
            }
        }

        sb.append(candidateScore).append(" ").append(candidates.size()).append("\n");

        for (int val : candidates) {
            sb.append(val).append(" ");
        }
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}
package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2660 {

    private static final int INF = 987654321;

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int count;
    private int[][] cost;
    private Integer[] result;
    private int min;

    public static void main(String[] args) throws IOException {
        new BOJ_2660().solution();
    }

    public void solution() throws IOException {
        init();
        calcResult();
        printResult();
    }

    private void init() throws IOException {
        count = Integer.parseInt(bf.readLine());
        cost = new int[count + 1][count + 1];

        for (int i = 1; i <= count; i++) {
            Arrays.fill(cost[i], INF);
            cost[i][i] = 0;
        }

        while (true) {
            StringTokenizer st = new StringTokenizer(bf.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            if (from == -1 && to == -1) {
                break;
            }

            cost[from][to] = 1;
            cost[to][from] = 1;
        }
    }

    private void calcResult() {
        for (int k = 1; k <= count; k++) {
            for (int i = 1; i <= count; i++) {
                for (int j = 1; j <= count; j++) {
                    cost[i][j] = Math.min(cost[i][j], cost[i][k] + cost[k][j]);
                }
            }
        }

        min = INF;
        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= count; i++) {
            int max = 0;
            for (int j = 1; j <= count; j++) {
                if (i == j) continue;
                max = Math.max(cost[i][j], max);
            }
            if (min > max) {
                min = max;
                queue.clear();
                queue.offer(i);
            } else if (min == max) {
                queue.offer(i);
            }
        }

        result = queue.toArray(new Integer[0]);
        Arrays.sort(result);
    }

    private void printResult() throws IOException {
        bw.write(min + " " + result.length + "\n");
        for (int num : result) {
            bw.write(num + " ");
        }
        bw.flush();
    }
}

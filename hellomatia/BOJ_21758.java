package hellomatia;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_21758 {

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] arr;

    public static void main(String[] args) throws IOException {
        new BOJ_21758().solution();
    }

    public void solution() throws IOException {
        init();
        printAns(calcResult());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        StringTokenizer st = new StringTokenizer(bf.readLine());
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = arr[i - 1] + Integer.parseInt(st.nextToken());
        }
    }

    private long calcResult() {
        long result = 0;

        long bee1 = 0;
        long bee2 = 0;
        for (int i = 2; i <= N - 1; i++) {
            bee1 = sum(2, N) - sum(i, i);
            bee2 = sum(i + 1, N);
            result = Math.max(result, bee1 + bee2);

            bee1 = sum(2, N - 1) + sum(i, i);
            result = Math.max(result, bee1);

            bee1 = sum(1, N - 1) - sum(i, i);
            bee2 = sum(1, i - 1);
            result = Math.max(result, bee1 + bee2);
        }

        return result;
    }

    private int sum(int start, int end) {
        return arr[end] - arr[start - 1];
    }

    private void printAns(long result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}

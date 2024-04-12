package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_2343 {

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int M;
    private int[] time;
    private int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        new BOJ_2343().solution();
    }

    public void solution() throws IOException {
        init();
        printAns(calcResult());
    }

    private void init() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        time = new int[N + 1];
        st = new StringTokenizer(bf.readLine());
        for (int i = 1; i <= N; i++) {
            time[i] = Integer.parseInt(st.nextToken());
            max = Math.max(time[i], max);
        }
    }

    private int calcResult() {
        int start = max;
        int end = 1100000000;

        while (start <= end) {

            int mid = start + end >>> 1;

            int count = 0;
            int sum = 0;
            for (int i = 1; i <= N; i++) {
                if (time[i] + sum > mid) {
                    count++;
                    sum = 0;
                }
                sum += time[i];
            }

            if (count != 0) {
                count++;
            }

            if (count <= M) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return start;
    }

    private void printAns(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}
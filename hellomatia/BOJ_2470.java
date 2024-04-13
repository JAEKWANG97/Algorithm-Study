package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2470 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private int N;
    private int minPoint1, minPoint2;
    private int[] solutions;

    public static void main(String[] args) throws IOException {
        new BOJ_2470().solution();
    }

    private void solution() throws IOException {
        init();
        calcResult();
        printResult();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        solutions = new int[N];
        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            solutions[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(solutions);
    }

    private void calcResult() {
        int point1 = 0;
        int point2 = N - 1;

        int minDiff = Integer.MAX_VALUE;
        minPoint1 = 0;
        minPoint2 = N - 1;

        while (point1 < point2) {
            int diff = solutions[point2] + solutions[point1];

            if (Math.abs(minDiff) > Math.abs(diff)) {
                minDiff = diff;
                minPoint1 = point1;
                minPoint2 = point2;
            }

            if (0 < diff) {
                point2--;
            } else {
                point1++;
            }
        }
    }

    private void printResult() throws IOException {
        bw.write(solutions[minPoint1] + " " + solutions[minPoint2] + "\n");
        bw.flush();
    }
}

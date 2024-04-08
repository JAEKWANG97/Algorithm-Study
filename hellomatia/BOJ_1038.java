package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1038 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private int N;
    private long ans;
    private ArrayList<Long> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        new BOJ_1038().solution();
    }

    private void solution() throws IOException {
        init();
        decreaseNum();
        printResult();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void decreaseNum() {
        if (N <= 10) {
            ans = N;
        } else if (N > 1022) {
            ans = -1;
        } else {
            for (int i = 0; i < 10; i++) {
                initNum(i, 1);
            }
            Collections.sort(list);
            ans = list.get(N);
        }
    }

    private void initNum(long num, int idx) {
        if (idx > 10) return;

        list.add(num);
        for (int i = 0; i < num % 10; i++) {
            initNum((num * 10) + i, idx + 1);
        }
    }

    private void printResult() throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}
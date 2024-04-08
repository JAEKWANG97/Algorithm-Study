package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2138 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] now;
    private int[] backup;
    private int[] target;

    public static void main(String[] args) throws IOException {
        new BOJ_2138().solution();
    }

    private void solution() throws IOException {
        init();
        backup = now.clone();
        int result1 = calcResult();
        now = backup;
        switchLight(0);
        int result2 = calcResult();

        if (result1 == -1) {
            if (result2 == -1) printResult(-1);
            else printResult(result2 + 1);
        } else if (result2 == -1) {
            printResult(result1);
        } else printResult(Math.min(result1, result2 + 1));

        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
        now = new int[N];
        target = new int[N];
        String rowValue = bf.readLine();
        for (int i = 0; i < N; i++) {
            now[i] = rowValue.charAt(i) - '0';
        }
        rowValue = bf.readLine();
        for (int i = 0; i < N; i++) {
            target[i] = rowValue.charAt(i) - '0';
        }
    }

    private int calcResult() {
        int result = 0;
        for (int i = 1; i < N; i++) {
            if (now[i - 1] != target[i - 1]) {
                switchLight(i);
                result++;
            }
        }

        if (now[N - 1] != target[N - 1]) return -1;
        return result;
    }

    private void switchLight(int index) {
        if (0 <= index - 1) {
            now[index - 1] ^= 1;
        }
        if (index + 1 < N) {
            now[index + 1] ^= 1;
        }
        now[index] ^= 1;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

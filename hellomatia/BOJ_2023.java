package hellomatia;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BOJ_2023 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] startNum = {2, 3, 5, 7};

    public static void main(String[] args) throws IOException {
        new BOJ_2023().solution();
    }

    private void solution() throws IOException {
        init();
        for (int start : startNum) {
            calcResult(1, start);
        }
        bw.flush();
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private void calcResult(int count, int num) throws IOException {
        if (count == N) {
            printResult(num);
        }

        for (int i = 1; i < 10; i += 2) {
            int newNum = num * 10 + i;
            if (isPrime(newNum)) {
                calcResult(count + 1, newNum);
            }
        }
    }

    private boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
    }
}

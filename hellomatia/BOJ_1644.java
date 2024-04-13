package hellomatia;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BOJ_1644 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private List<Integer> primes;

    public static void main(String[] args) throws IOException {
        new BOJ_1644().solution();
    }

    private void solution() throws IOException {
        init();
        printResult(calcResult());
        bw.close();
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());
    }

    private int calcResult() {
        int result = 0;
        initPrime();

        int start = 0;
        int end = 0;
        int sum = 0;
        while (start <= end && end < primes.size()) {
            if (sum < N) {
                sum += primes.get(end++);
            } else {
                if (sum == N) {
                    result++;
                }
                sum -= primes.get(start++);
            }
        }

        return result;
    }

    private void initPrime() {
        boolean[] isPrime = new boolean[N + 1];
        Arrays.fill(isPrime, true);

        isPrime[0] = false;
        isPrime[1] = false;

        for (int i = 2; i * i <= N; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= N; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        primes = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            if (isPrime[i]) {
                primes.add(i);
            }
        }
        primes.add(0);
    }

    private void printResult(int result) throws IOException {
        bw.write(result + "\n");
        bw.flush();
    }
}

import java.io.*;
import java.util.*;

public class BOJ_1644 {
    static int N, answer;
    static boolean[] prime;
    static List<Integer> primeNumbers;

    static void setPrime() {
        prime[0] = prime[1] = true;

        for (int i = 2; i <= Math.sqrt(prime.length); i++) {
            if (prime[i]) continue;
            for (int j = i * i; j < prime.length; j += i) {
                prime[j] = true;
            }
        }
    }

    static void setPrimeNumbers() {
        for (int i = 3; i < prime.length; i++) {
            if (!prime[i]) {
                primeNumbers.add(i);
            }
        }
        primeNumbers.add(0);
    }

    static void calcAnswer() {
        int start, end, sum;
        sum = start = end = 0;

        while (end < primeNumbers.size()) {
            if (sum >= N) {
                sum -= primeNumbers.get(start++);
            } else {
                sum += primeNumbers.get(end++);
            }
            if (sum == N) {
                answer++;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        prime = new boolean[N + 1];
        primeNumbers = new ArrayList<>();

        setPrime();
        setPrimeNumbers();
        calcAnswer();

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
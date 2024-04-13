import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1644 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static List<Integer> primes;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        countAnswer();
        printAnswer();
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        primes = getPrimes();
    }

    private static List<Integer> getPrimes() {
        boolean[] check = sieveOfEratosthenes();

        List<Integer> primes = new ArrayList<>();

        for (int n = 2; n <= N; n++) {
            if (!check[n]) {
                primes.add(n);
            }
        }

        return primes;
    }

    private static boolean[] sieveOfEratosthenes() {
        boolean[] sieve = new boolean[N + 1];

        sieve[0] = true;
        sieve[1] = true;

        for (int n = 2; n <= Math.sqrt(N); n++) {
            if (sieve[n]) {
                continue;
            }

            for (int multiple = n + n; multiple <= N; multiple += n) {
                sieve[multiple] = true;
            }
        }

        return sieve;
    }

    private static void countAnswer() {
        int sum = 0;
        int left = 0;

        for (int right = 0; right < primes.size(); right++) {
            sum += primes.get(right);

            while (sum >= N) {
                if (sum == N) {
                    answer++;
                    break;
                }

                sum -= primes.get(left++);
            }
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}

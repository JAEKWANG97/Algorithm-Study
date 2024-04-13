import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;

    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        N = Integer.parseInt(br.readLine());

        getAnswer(0, 0);
        System.out.println(sb);
    }

    private static void getAnswer(int num, int depth) {
        if (depth == N) {
            sb.append(num).append("\n");
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (isPrime(num * 10 + i)) {
                getAnswer(num * 10 + i, depth + 1);
            }
        }
    }

    private static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}


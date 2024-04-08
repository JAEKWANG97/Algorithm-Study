import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class BOJ_2470 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;
    private static int[] solutions;

    private static int solution1;
    private static int solution2;

    private static int minMixed = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();
        findSolutions();
        printAnswer();
    }

    private static void findSolutions() {
        for (int i = 0; i < N - 1; i++) {
            int mixed = solutions[i] + solutions[i + 1];

            if (Math.abs(mixed) < Math.abs(minMixed)) {
                minMixed = mixed;

                solution1 = solutions[i];
                solution2 = solutions[i + 1];
            }

            if (mixed == 0) {
                break;
            }
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        solutions = Arrays.stream(br.readLine().split("\\s"))
                .map(Integer::parseInt)
                .sorted(Comparator.comparingInt(Math::abs))
                .mapToInt(Integer::intValue)
                .toArray();
    }

    private static void printAnswer() {
        System.out.println(Math.min(solution1, solution2) + " " + Math.max(solution1, solution2));
    }
}

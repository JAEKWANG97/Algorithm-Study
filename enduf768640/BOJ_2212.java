import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;

public class BOJ_2212 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N, K;
    private static int[] sensors;

    private static Integer[] diff;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        greedy();

        printAnswer();
    }

    private static void greedy() {
        diff = new Integer[sensors.length - 1];

        for (int i = 0; i < diff.length; i++) {
            diff[i] = sensors[i + 1] - sensors[i];
        }

        Arrays.sort(diff, Collections.reverseOrder());

        for (int i = K - 1; i < diff.length; i++) {
            answer += diff[i];
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
        K = Integer.parseInt(br.readLine());

        sensors = Arrays.stream(br.readLine().split("\\s"))
                .mapToInt(Integer::parseInt)
                .sorted()
                .toArray();
    }

    private static void printAnswer() {
        System.out.println(answer);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1041 {

    private static int N;
    private static int[] dice;

    private static long answer;

    public static void main(String[] args) throws IOException {
        init();

        calculateDice();

        System.out.println(answer);
    }

    private static void calculateDice() {
        if (N == 1) {
            answer = Arrays.stream(dice)
                    .sorted()
                    .limit(5)
                    .sum();

            return;
        }

        answer = (long) minimumOne() * (5L * (N - 2) * (N - 2) + 4L * (N - 2)) +
                (long) minimumTwo() * (4L * (N - 1) + 4L * (N - 2)) +
                (long) minimumThree() * 4;
    }

    private static int minimumOne() {
        return Arrays.stream(dice)
                .min()
                .getAsInt();
    }

    private static int minimumTwo() {
        return Arrays.stream(new int[]{
                        dice[0] + dice[1],
                        dice[0] + dice[2],
                        dice[0] + dice[3],
                        dice[0] + dice[4],
                        dice[1] + dice[2],
                        dice[1] + dice[3],
                        dice[1] + dice[5],
                        dice[2] + dice[4],
                        dice[2] + dice[5],
                        dice[3] + dice[4],
                        dice[3] + dice[5],
                        dice[4] + dice[5]
                })
                .min()
                .getAsInt();
    }

    private static int minimumThree() {
        return Arrays.stream(new int[]{
                        dice[0] + dice[1] + dice[2],
                        dice[0] + dice[1] + dice[3],
                        dice[0] + dice[2] + dice[4],
                        dice[0] + dice[3] + dice[4],
                        dice[1] + dice[2] + dice[5],
                        dice[1] + dice[3] + dice[5],
                        dice[2] + dice[4] + dice[5],
                        dice[3] + dice[4] + dice[5],
                })
                .min()
                .getAsInt();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[6];
        for (int i = 0; i < 6; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }
    }
}

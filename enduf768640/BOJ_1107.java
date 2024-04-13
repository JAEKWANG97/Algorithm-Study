import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BOJ_1107 {

    private static final int START_CHANNEL = 100;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String targetChannel;
    private static int M;

    private static List<Integer> brokenButtons;
    private static List<Integer> unbrokenButtons;

    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        init();

        if (checkCondition()) {
            return;
        }

        makeChannel();

        printAnswer();
    }

    private static boolean checkCondition() {
        if (Integer.parseInt(targetChannel) == START_CHANNEL) {
            System.out.println(0);
            return true;
        }

        if (M == 0) {
            System.out.println(Math.min(targetChannel.length(), Math.abs(Integer.parseInt(targetChannel) - START_CHANNEL)));
            return true;
        }

        if (M == 10) {
            System.out.println(Math.abs(Integer.parseInt(targetChannel) - START_CHANNEL));
            return true;
        }

        return false;
    }

    private static void makeChannel() {
        for (int n = 1; n <= 6; n++) {
            makeChannel(new int[n], 0, n);
        }
    }

    private static void makeChannel(int[] channel, int count, int N) {
        if (count == N) {
            String currentChannel = Arrays.stream(channel)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining());

            answer = Math.min(answer, Math.abs(Integer.parseInt(targetChannel) - Integer.parseInt(currentChannel)) + channel.length);
            return;
        }

        for (int i = 0; i < unbrokenButtons.size(); i++) {
            channel[count] = unbrokenButtons.get(i);
            makeChannel(channel, count + 1, N);
        }
    }

    private static void init() throws IOException {
        targetChannel = br.readLine();

        M = Integer.parseInt(br.readLine());
        if (M == 0) {
            return;
        }

        brokenButtons = Arrays.stream(br.readLine().split("\\s"))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        unbrokenButtons = IntStream.rangeClosed(0, 9)
                .filter(num -> !brokenButtons.contains(num))
                .boxed()
                .collect(Collectors.toList());
    }

    private static void printAnswer() {
        System.out.println(Math.min(answer, Math.abs(Integer.parseInt(targetChannel) - START_CHANNEL)));
    }
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2138 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;

    private static boolean[] init;
    private static boolean[] result;

    public static void main(String[] args) throws IOException {
        init();
        printResult(first(), notFirst());
    }

    private static int first() {
        int count = 1;

        boolean[] pressFirst = init.clone();
        pressButton(pressFirst, 0);

        for (int idx = 1; idx < N; idx++) {
            if (pressFirst[idx - 1] != result[idx - 1]) {
                pressButton(pressFirst, idx);
                count++;
            }
        }

        if (Arrays.equals(pressFirst, result)) {
            return count;
        } else {
            return -1;
        }
    }

    private static int notFirst() {
        int count = 0;

        boolean[] notPressFirst = init.clone();

        for (int idx = 1; idx < N; idx++) {
            if (notPressFirst[idx - 1] != result[idx - 1]) {
                pressButton(notPressFirst, idx);
                count++;
            }
        }

        if (Arrays.equals(notPressFirst, result)) {
            return count;
        } else {
            return -1;
        }
    }

    private static void pressButton(boolean[] init, int idx) {
        if (idx != 0) {
            init[idx - 1] = !init[idx - 1];
        }

        init[idx] = !init[idx];

        if (idx != N - 1) {
            init[idx + 1] = !init[idx + 1];
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        init = new boolean[N];
        String[] input1 = br.readLine().split("");
        for (int i = 0; i < input1.length; i++) {
            init[i] = input1[i].equals("1");
        }

        result = new boolean[N];
        String[] input2 = br.readLine().split("");
        for (int i = 0; i < input2.length; i++) {
            result[i] = input2[i].equals("1");
        }
    }

    private static void printResult(int count1, int count2) {
        if (count1 == -1 && count2 == -1) {
            System.out.println(-1);
        } else if (count1 == -1) {
            System.out.println(count2);
        } else if (count2 == -1) {
            System.out.println(count1);
        } else {
            System.out.println(Math.min(count1, count2));
        }
    }
}

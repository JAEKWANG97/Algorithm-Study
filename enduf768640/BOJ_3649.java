import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_3649 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static String input;

    private static int X, N;
    private static int[] blocks;

    private static int answer1, answer2;

    public static void main(String[] args) throws IOException {
        while ((input = br.readLine()) != null) {
            init();

            if (checkBlock()) {
                System.out.println("yes " + answer1 + " " + answer2);
            } else {
                System.out.println("danger");
            }
        }
    }

    private static boolean checkBlock() {
        for (int i = 0; i < N; i++) {
            int block = blocks[i];

            int idx = Arrays.binarySearch(blocks, X - block);

            if (idx < 0 || idx == i) {
                continue;
            }

            answer1 = block;
            answer2 = blocks[idx];
            return true;
        }

        return false;
    }

    private static void init() throws IOException {
        X = Integer.parseInt(input) * 10000000;
        N = Integer.parseInt(br.readLine());

        blocks = new int[N];
        for (int i = 0; i < N; i++) {
            blocks[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(blocks);
    }
}

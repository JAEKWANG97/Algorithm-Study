import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input;
        while ((input = br.readLine()) != null) {
            int width = Integer.parseInt(input);
            int n = Integer.parseInt(br.readLine());
            int[] lego = new int[n];
            for (int i = 0; i < n; i++) {
                lego[i] = Integer.parseInt(br.readLine());
            }

            Arrays.sort(lego);
            findLego(lego, width);

        }
    }

    private static void findLego(int[] lego, int width) {
        int p1 = 0, p2 = lego.length - 1;
        width *= 10_000_000;

        while (p1 < p2) {
            int sum = lego[p1] + lego[p2];

            if (sum == width) {
                System.out.println("yes " + lego[p1] + " " + lego[p2]);
                return;
            }

            if (sum < width) {
                p1++;
            } else {
                p2--;
            }
        }

        System.out.println("danger");
    }

    private static int validSize(int width, int l1, int l2) {
        long sum = (l1 + l2);
        width *= 10_000_000;

        int result;
        if (width == sum) {
            result = 0;
        } else if (width > sum) {
            result = -1;
        } else {
            result = 1;
        }
        return result;
    }
}



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_5052 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int T;
    private static int N;

    private static List<String> phoneNumbers;

    private static boolean answer;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            init();
            compareNumber();
            printAnswer();
        }
    }

    private static void compareNumber() {
        for (int i = 0; i < N - 1; i++) {
            if (isPrefix(phoneNumbers.get(i), phoneNumbers.get(i + 1))) {
                answer = false;
                break;
            }
        }
    }

    private static boolean isPrefix(String number1, String number2) {
        for (int i = 0; i < Math.min(number1.length(), number2.length()); i++) {
            if (number1.charAt(i) != number2.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        phoneNumbers = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            phoneNumbers.add(br.readLine());
        }

        phoneNumbers.sort((p1, p2) -> {
            for (int i = 0; i < Math.min(p1.length(), p2.length()); i++) {
                if (p1.charAt(i) != p2.charAt(i)) {
                    return p1.charAt(i) - p2.charAt(i);
                }
            }

            return p1.length() - p2.length();
        });

        answer = true;
    }

    private static void printAnswer() {
        if (answer) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}

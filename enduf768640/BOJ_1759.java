import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1759 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int L;
    private static int C;

    private static char[] alpha;

    private static List<Character> vowel = Arrays.asList('a', 'e', 'i', 'o', 'u');

    public static void main(String[] args) throws IOException {
        init();
        Arrays.sort(alpha);
        searchAndPrintPassword(new char[L], 0, 0);
    }

    private static void searchAndPrintPassword(char[] password, int start, int count) {
        if (count == L) {
            if (isValid(password)) {
                System.out.println(password);
            }
            return;
        }

        for (int i = start; i < C; i++) {
            password[count] = alpha[i];
            searchAndPrintPassword(password, i + 1, count + 1);
        }
    }

    private static boolean isValid(char[] password) {
        int consonantCount = 0;
        int vowelCount = 0;

        for (int i = 0; i < password.length; i++) {
            if (vowel.contains(password[i])) {
                vowelCount++;
            } else {
                consonantCount++;
            }
        }

        return consonantCount >= 2 && vowelCount >= 1;
    }
    
    private static void init() throws IOException {
		st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        alpha = new char[C];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }
	}
}
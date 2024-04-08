import java.io.*;
import java.util.*;

public class BOJ_1759 {
    static int L, C, moCount, jaCount;
    static char[] words, password;
    static StringBuilder sb = new StringBuilder();

    static void helper(int idx, int count) {
        if (count == L) {
            if (isPassword()) {
                printPassword();
            }
            return;
        }

        for (int i = idx; i < C; i++) {
            password[count] = words[i];
            helper(i + 1, count + 1);
        }
    }

    private static void printPassword() {
        for (char c : password) {
            sb.append(c);
        }
        sb.append("\n");
    }

    static boolean isPassword() {
        moCount = jaCount = 0;

        for (char ch : password) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                moCount++;
            } else {
                jaCount++;
            }
        }

        return moCount >= 1 && jaCount >= 2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        words = new char[C];
        password = new char[L];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < C; i++) {
            words[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(words);
        helper(0, 0);

        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}

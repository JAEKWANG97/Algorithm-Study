import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    private static int l, c;
    private static char[] code;
    private static final StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        l = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        code = new char[c];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < c; i++) {
            code[i] = st.nextToken().toCharArray()[0];
        }

        Arrays.sort(code);
        dfs(0, "");

        System.out.println(sb);
    }

    private static void dfs(int start, String result) {
        if (result.length() == l) {
            if (isValidCode(result)) {
                sb.append(result).append("\n");
            }
            return;
        }

        if (start >= c) {
            return;
        }

        dfs(start + 1, result + code[start]);
        dfs(start + 1, result);
    }

    private static boolean isValidCode(String result) {
        int moemCnt = 0;
        int jaemCnt = 0;

        for (char ch : result.toCharArray()) {
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                moemCnt++;
            } else {
                jaemCnt++;
            }
        }

        return jaemCnt >= 2 && moemCnt >= 1;
    }
}



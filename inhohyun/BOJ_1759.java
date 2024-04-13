import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
    static StringBuilder sb;

    public static void main(String[] args) throws IOException {
        init();

        System.out.println(sb);
    }

    static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();
        int l = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        char[] alpha = new char[c];
        for (int i = 0; i < c; i++) {
            alpha[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(alpha);

        dfs(l, alpha, 0, "");

    }

    // 조건을 만족하는 조합
    static void dfs(int l, char[] alpha, int depth, String password) {
        if (password.length() == l) {
            if (isCheck(password)) {
//				if(password.charAt(0) == ' ') return;
                sb.append(password + "\n");

            }
            return;
        }

        if (alpha.length <= depth)
            return;

        // 선택
        dfs(l, alpha, depth + 1, password + alpha[depth]);

        // 비선택
        dfs(l, alpha, depth + 1, password);

    }

    static boolean isCheck(String tmp) {
        int cnt = 0;
        int cnt2 = 0;
        for (int i = 0; i < tmp.length(); i++) {
            char target = tmp.charAt(i);

            if (target == 'a' || target == 'e' || target == 'i' || target == 'o' || target == 'u') {
                cnt++;
            } else {
                cnt2++;
            }
        }

        if (cnt >= 1 && cnt2 >= 2)
            return true;
        return false;
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        boolean[] broken = new boolean[10];

        if (m != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                broken[Integer.parseInt(st.nextToken())] = true;
            }
        }

        int answer = Math.abs(n - 100);
        for (int i = 0; i < 999999; i++) {
            String str = String.valueOf(i);
            int len = str.length();

            boolean isBroken = false;
            for (int j = 0; j < len; j++) {
                if (broken[str.charAt(j) - '0']) {
                    isBroken = true;
                    break;
                }
            }

            if (!isBroken) {
                int cnt = Math.abs(i - n) + len;
                answer = Math.min(answer, cnt);
            }
        }
        
        System.out.println(answer);
    }
}

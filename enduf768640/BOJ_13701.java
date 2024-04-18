import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BOJ_13701 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static HashSet<Integer> set = new HashSet<>();

    private static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            int next = Integer.parseInt(st.nextToken());

            if (set.contains(next)) {
                continue;
            }

            set.add(next);
            answer.append(next).append(" ");
        }

        System.out.println(answer);
    }
}

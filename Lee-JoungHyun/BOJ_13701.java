import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;
import java.util.StringTokenizer;

public class BOJ_13701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        BitSet bitSet = new BitSet();

        while (st.hasMoreTokens()) {
            int c = Integer.parseInt(st.nextToken());
            if (!bitSet.get(c)) {
                sb.append(c).append(" ");
                bitSet.set(c);
            }
        }
        System.out.println(sb);

    }
}

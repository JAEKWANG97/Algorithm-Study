import java.io.*;
import java.util.*;

public class BOJ_13701 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        BitSet set = new BitSet(1 << 25);

        st = new StringTokenizer(br.readLine());
        while (st.hasMoreTokens()) {
            int N = Integer.parseInt(st.nextToken());
            if (!set.get(N)) {
                set.set(N);
                sb.append(N).append(" ");
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
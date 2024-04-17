package hellomatia;

import java.io.*;
import java.util.BitSet;
import java.util.StringTokenizer;

public class BOJ_13701 {

    private final BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        new BOJ_13701().solution();
    }

    public void solution() throws IOException {
        printAns(calcResult());
    }

    private String calcResult() throws IOException {
        StringTokenizer st = new StringTokenizer(bf.readLine());

        StringBuilder sb = new StringBuilder();
        BitSet bitSet = new BitSet(1 << 26);
        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            if (!bitSet.get(num)) {
                bitSet.set(num);
                sb.append(num).append(" ");
            }
        }

        return sb.toString();
    }

    private void printAns(String ans) throws IOException {
        bw.write(ans);
        bw.flush();
    }
}

import java.io.*;
import java.util.*;

public class BOJ_1107 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        boolean[] breakdownNumber = new boolean[10];
        int answer = Math.abs(100 - N);

        if (M != 0) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                int num = Integer.parseInt(st.nextToken());
                breakdownNumber[num] = true;
            }
        }

        for (int i = 0; i <= 999999; i++) {
            String curNum = String.valueOf(i);
            boolean isImpossible = false;

            for (char c : curNum.toCharArray()) {
                if (breakdownNumber[c - '0']) {
                    isImpossible = true;
                    break;
                }
            }

            if (!isImpossible) {
                answer = Math.min(curNum.length() + Math.abs(i - N), answer);
            }
        }
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
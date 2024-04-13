import java.io.*;
import java.util.*;

public class BOJ_1038 {
    static List<Long> descNumbers;

    static void helper(int depth, long num) {
        descNumbers.add(num);
        if (num % 10 == 0) {
            return;
        }

        for (int i = 0; i < num % 10; i++) {
            helper(depth + 1, (num * 10) + i);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        descNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            helper(0, i);
        }

        Collections.sort(descNumbers);
        bw.write(String.valueOf(descNumbers.size() <= N ? -1 : descNumbers.get(N)));
        br.close();
        bw.close();
    }
}
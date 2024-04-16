import java.io.*;
import java.util.*;

public class BOJ_1744 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> negative = new ArrayList<>();
        List<Integer> positive = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(br.readLine());
            if (n > 0)
                positive.add(n);
            else
                negative.add(n);
        }

        Collections.sort(positive, Collections.reverseOrder());
        Collections.sort(negative);

        int sum = 0;
        int idx = 0;

        while (idx < positive.size()) {
            if (idx + 1 < positive.size() && positive.get(idx) != 1 && positive.get(idx + 1) != 1)
                sum += positive.get(idx++) * positive.get(idx++);
            else
                sum += positive.get(idx++);
        }

        idx = 0;
        while (idx < negative.size()) {
            if (idx + 1 < negative.size() && negative.get(idx) != 1 && negative.get(idx + 1) != 1)
                sum += negative.get(idx++) * negative.get(idx++);
            else
                sum += negative.get(idx++);
        }
        System.out.println(sum);
    }
}
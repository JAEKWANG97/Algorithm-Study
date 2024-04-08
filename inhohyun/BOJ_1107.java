import java.io.*;
import java.util.*;

public class BOJ_1107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        if (m == 0) {
            int answer = Math.min(Math.abs(100 - n), String.valueOf(n).length());
            System.out.println(answer);
            return;
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> broken = new HashSet<>();
        for (int i = 0; i < m; i++) {
            broken.add(Integer.parseInt(st.nextToken()));
        }

        int minCount = Math.abs(100 - n);

        for (int nums = 0; nums <= 1000000; nums++) {
            String numsStr = Integer.toString(nums);

            boolean isBroken = false;
            for (int j = 0; j < numsStr.length(); j++) {
                int digit = numsStr.charAt(j) - '0';
                if (broken.contains(digit)) {
                    isBroken = true;
                    break;
                }
            }

            if (!isBroken) {
                minCount = Math.min(minCount, Math.abs(nums - n) + numsStr.length());
            }
        }
        System.out.println(minCount);
    }
}

import java.util.*;

public class PGS_K진수에서소수개수구하기 {
    public static int solution(int n, int k) {
        int answer = 0;
        String bin = Integer.toString(n, k);
        List<String> nums = new ArrayList<>(List.of(bin.split("0")));

        for (String str : nums) {
            if (str.equals("")) {
                continue;
            }
            long tmp = Long.parseLong(str);
            if (isPrime(tmp)) {
                answer++;
            }
        }

        return answer;
    }

    public static boolean isPrime(long checkNum) {
        if (checkNum == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(checkNum); i++) {
            if (checkNum % i == 0) {
                return false;
            }
        }
        return true;
    }
}

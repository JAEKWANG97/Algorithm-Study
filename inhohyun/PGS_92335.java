import java.util.ArrayList;
import java.util.List;
 
public class Solution {
    public static void main(String[] args) {
        System.out.println(solution(437674, 3));
        System.out.println(solution(110011, 10));
    }
 
    public static int solution(int n, int k) {
        int answer = 0;
        String binary = Integer.toString(n, k); // n을 k 진법으로 변환
        List<String> nums = new ArrayList<>(List.of(binary.split("0")));
 
        for (String str : nums) {
            // 공백이 있으면 다음 인덱스로 넘어간다.
            if (str.equals("")) {
                continue;
            }
            long checkNum = Long.parseLong(str);
            if (isPrime(checkNum)) {
                answer++;
            }
        }
 
        return answer;
    }
 
    public static boolean isPrime(long checkNum) {
        // 1은 소수가 아니다.
        if (checkNum == 1) {
            return false;
        }
 
        // 에라토스테네스의 체
        for (int i = 2; i <= Math.sqrt(checkNum); i++) {
            if (checkNum % i == 0) {
                return false;
            }
        }
        return true;
    }
}

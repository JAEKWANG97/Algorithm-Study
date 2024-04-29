import java.util.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        Stack<Integer> stack = new Stack();
        
        while (n != 0) {
            stack.add(n % k);
            n /= k;
        }
        long P = 0;
        while (!stack.isEmpty()) {
            int tmp = stack.pop();
            if (stack.isEmpty()) P = P * 10 + tmp;
            // 소수 판별하기!
            if (tmp == 0 || stack.isEmpty()) {
                if (isPrime(P)) {
                    System.out.println(P);
                    answer++;
                }
                P = 0;
            } else {
                P = P * 10 + tmp;
            }
        }
        return answer;
    }
    
    public boolean isPrime(long n) {
        if (n < 2) return false;
	    for (int i = 2; i<=(int)Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
	    }
	    return true;
    }
}
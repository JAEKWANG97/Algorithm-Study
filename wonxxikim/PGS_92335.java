import java.util.*;
import java.lang.*;

class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String num = "";
        while(n!=0){
            int x = n%k;
            num+=x;
            n = n/k;
        }
        String[] nums = num.split("0");
        for(String s : nums){
            int idx = 0;
            long x = 0;
            for(int i = 0;i<s.length();i++){
                x+=(s.charAt(i)-'0')*Math.pow(10,idx);
                idx++;
            }
            if(isPrime(x)) answer++;
        }
        return answer;

    }
    public static boolean isPrime(long x){
        if(x==0) return false;
        if(x==1) return false;
        if(x==2) return true;
        for(int i = 2 ; i<=Math.pow(x,0.5);i++){
            if(x%i==0) return false;
        }
        return true;
    }
    
}

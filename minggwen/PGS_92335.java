import java.util.*;
class Solution {
    Map<Long,Boolean> map;
    public int solution(int n, int k) {
        int answer=0;
        map = new HashMap<>();
        map.put(0L,false);
        map.put(1L,false);
        map.put(2L,true);
        String str = Integer.toString(n,k);
        System.out.println(str);
        String nums [] = str.split("0");
        System.out.println(Arrays.toString(nums));
        for(int i=0;i<nums.length;i++){
            if(nums[i].equals(""))continue;
            Long num = Long.parseLong(nums[i]);
            if(isPrime(num))answer++;
        }
        return answer;
    }
    private boolean isPrime(long num){
        if(map.containsKey(num))return map.get(num);
        for(long i=2L;i*i<=num;i++){
            if(num%i==0){
                map.put(num,false);
                return false;
            }
        }
        System.out.println(num);
        map.put(num,true);
        return true;
    }
}

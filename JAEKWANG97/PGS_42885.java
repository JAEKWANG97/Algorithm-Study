import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;
        
        boolean[] isEscape = new boolean[people.length];
        
        int left = 0;
        int right = people.length - 1;
        Arrays.sort(people);
        while(left < right){
            int p1 = people[left];
            int p2 = people[right];
            
            if(p1 + p2 <= limit){
                left += 1;
                right -= 1;
                answer += 1;
            }else{
                right -= 1;
            }
        }
        
        answer += people.length - answer*2;
        
        return answer;
    }
}

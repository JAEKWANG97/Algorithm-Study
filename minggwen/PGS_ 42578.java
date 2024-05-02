import java.util.*;
class Solution {
    
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String,Integer> map = new HashMap<>();
        for(String[] cloth:clothes){
            if(map.containsKey(cloth[1])){
                int num = map.get(cloth[1]);
                System.out.println("!!"+num);
                map.put(cloth[1],num+1);
            }
            else{
                map.put(cloth[1],1);
            }
        }
        System.out.println(map.toString());
        for(int num : map.values()){
            answer*=(num+1);
        }
        return answer-1;
    }
   
}

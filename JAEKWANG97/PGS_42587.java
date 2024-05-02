import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 0;
        Map<String , Integer> map = new HashMap<>();
        int n = clothes.length;
        for(int i = 0 ; i < n  ; i++){
            map.put(clothes[i][1] , map.getOrDefault(clothes[i][1] , 0) + 1);
        }
        answer = 1;
        for(Integer value : map.values()){
            answer *= value+1;
        }
        
        return --answer;
    }
}

import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String, Integer> map = new HashMap<String, Integer>();
        // HashMap 완성
        for (String[] cloth : clothes) {
            if (!map.containsKey(cloth[1])){
                map.put(cloth[1], 1);    
            } else {
                map.put(cloth[1], map.get(cloth[1])+1);
            }    
        }

        for (Integer value : map.values()){
            answer *= value + 1;
        }

        return answer - 1;
    }
}
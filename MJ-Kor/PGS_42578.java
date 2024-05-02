import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        Map<String,  Integer> map = new HashMap<>();
        for(String[] clothe : clothes){
            if(map.containsKey(clothe[1])){
                int tmp = map.get(clothe[1]);
                map.put(clothe[1], tmp + 1);
            }else{
                map.put(clothe[1], 2);   
            }
        }
        for(String key : map.keySet()){
            answer *= map.get(key);
        }
        answer -= 1;
        return answer;
    }
}

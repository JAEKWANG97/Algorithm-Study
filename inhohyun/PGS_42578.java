import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String,  Integer> clotheHash = new HashMap<>();
        for(String[] clothe : clothes){
            if(clotheHash.containsKey(clothe[1])){
                int tmp = clotheHash.get(clothe[1]);
                clotheHash.put(clothe[1], tmp + 1);
            }else{
                clotheHash.put(clothe[1], 2);   
            }
        }
        
        for(String k : clotheHash.keySet()){
            answer *= clotheHash.get(k);
        }
        
        answer -= 1;
        
        return answer;
    }
}

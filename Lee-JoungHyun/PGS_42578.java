import java.util.*;

class Solution {
    public int solution(String[][] clothes) {
        int answer = 1;
        
        HashMap<String, Integer> hs = new HashMap<>();
        
        for (String[] c : clothes) {
            if (!hs.containsKey(c[1])) {
                hs.put(c[1], 1);
            } else {
                hs.put(c[1], hs.get(c[1]) + 1);
            }
        }
        
        LinkedList<Integer> value = new LinkedList<>(hs.values());
        
        if (value.size() == 1) {
            answer = value.get(0) + 1;
        } else {
            for (int i : value) {
                answer *= (i + 1);
            }
        }
        
        return answer - 1;
    }
}
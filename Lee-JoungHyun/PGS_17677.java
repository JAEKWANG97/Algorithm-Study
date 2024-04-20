import java.util.*;

class Solution {
    
    public static final int NUM = 65536;
    
    public int solution(String str1, String str2) {
        
        double union = 0;
        double intersection = 0;
        
        char[] chars1 = str1.toLowerCase().toCharArray();
        char[] chars2 = str2.toLowerCase().toCharArray();      
        
        HashMap<String, Integer> hm1 = setCode(chars1);
        HashMap<String, Integer> hm2 = setCode(chars2);
        
        Iterator keySetIterator = hm1.keySet().iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next().toString();
            int max = 0, min = 0;
            if (hm2.containsKey(key)) {
                max = Math.max(hm1.get(key), hm2.get(key));
                min = Math.min(hm1.get(key), hm2.get(key));
                hm2.remove(key);
            } else {
                max = hm1.get(key);
            }
            union += max;
            intersection += min;
        }
        
        keySetIterator = hm2.keySet().iterator();
        while (keySetIterator.hasNext()) {
            String key = keySetIterator.next().toString();
            union += hm2.get(key);
        }
        if (intersection == 0 && union == 0) return NUM;
        return (int) (intersection / union * NUM);
    }
    
    private HashMap<String, Integer> setCode (char[] chars) {
        HashMap<String, Integer> hm = new HashMap<>();
        for (int i = 0; i < chars.length - 1; i++) {
            if (96 < chars[i] && chars[i] < 123 && 96 < chars[i + 1] && chars[i + 1] < 123) {
                String code = "" + chars[i] + chars[i + 1];
                if (hm.containsKey(code)) {
                    hm.put(code, hm.get(code) + 1);
                } else {
                    hm.put(code, 1);
                }
            }
        }
        
        return hm;
    }
}
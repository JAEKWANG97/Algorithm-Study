package algorithm_study.april.day_04_20;

import java.util.*;

class PGS_17684 {
    
    public static class LZW {
        int lastValue;
        Map<String, Integer> dict = new HashMap<>();
        
        public LZW() {
            init();
        }
        
        public void init() {
            int value = 0;
            for(int i = 65; i <= 90 ; i++) {
                char ch = (char)i;
                dict.put(""+ch, ++value);
            }
            this.lastValue = value;
        }
        
        public List<Integer> zip(String str) {
            int N = str.length();
            List<Integer> result = new LinkedList<>();
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++) {
                sb.append(str.charAt(i));
                String now = sb.toString();
                if(dict.containsKey(now)) { 
                    if(i == N - 1) {
                        result.add(dict.get(now));
                    }
                    continue;
                }
                dict.put(now, ++this.lastValue);
                String validStr = sb.deleteCharAt(sb.length() - 1).toString();
                result.add(dict.get(validStr));
                sb.setLength(0);
                i--;
            }
            return result;
        }
    }
    
    public int[] solution(String msg) {
        LZW lzw = new LZW();
        List<Integer> result = lzw.zip(msg);
        
        int L = result.size();
        int[] answer = new int[L];
        for(int i = 0; i < L; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
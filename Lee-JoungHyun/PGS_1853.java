import java.util.*;

class Solution {
    
    private static HashMap<Character, Integer> hs = new HashMap();
    private static boolean[] visited;
    private static int answer;
    private static int[] setting;
    private static String[] d;
    
    public int solution(int n, String[] data) {
        d = data;
        setting = new int[8];
        answer  = 0;
        visited = new boolean[8];
        hs.put('A', 0);
        hs.put('C', 1);
        hs.put('F', 2);
        hs.put('J', 3);
        hs.put('M', 4);
        hs.put('N', 5);
        hs.put('R', 6);
        hs.put('T', 7);
        
        // 순서 정하기 (순열)
        makePermutation(0);
        
        return answer;
        
    }
    // 순열 뽑기
    public void makePermutation(int cnt) {
        
        if (cnt > 7) {
            if (check()) {
                answer++;     
            }
            return;
        }
        
        for (int i = 0; i < 8; i++) {
            if (!visited[i]) {
                visited[i] = true;
                setting[cnt] = i;
                makePermutation(cnt + 1);
                visited[i] = false;
            }
        }
        
    }
    // d 가지고 체크
    private boolean check() {
        
        for (String str : d) {
            char[] chars = str.toCharArray();
            char f1 = chars[0];
            char f2 = chars[2];
            char oper = chars[3];
            int gap = 1 + chars[4] - '0';
            
            if (oper == '=') {
                if (!(Math.abs(setting[hs.get(f1)] - setting[hs.get(f2)]) == gap)) {
                    return false;
                }
            }
            
            else if (oper == '>') {
                if (!(Math.abs(setting[hs.get(f1)] - setting[hs.get(f2)]) > gap)) {
                    return false;
                }
            }
            
            else if (oper == '<') {
                if (!(Math.abs(setting[hs.get(f1)] - setting[hs.get(f2)]) < gap)) {
                    return false;
                }
            }
            
            
        }
        return true;
    }
    
    
}

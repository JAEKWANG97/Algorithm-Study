import java.util.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        
        for (int i = 0; i < n; i++) {
            int tmp = arr1[i] | arr2[i];
            String str = "";
            for (int j = 0; j < n; j++) {
                boolean check = (tmp & 1<<j) == (1<<j);
                
                if (check) {
                    str = "#" + str;
                } else {
                    str = " " + str;
                }
                
            }
            answer[i] = str;
        }
        
        
        return answer;
    }
}

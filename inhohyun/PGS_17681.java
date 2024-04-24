import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<String> solution(int n, int[] arr1, int[] arr2) {
        List<String> answer = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String str1 = Integer.toBinaryString(arr1[i]);
            String str2 = Integer.toBinaryString(arr2[i]);
            // 두 문자열의 길이를 n으로 맞추기
            str1 = String.format("%" + n + "s", str1).replace(' ', '0');
            str2 = String.format("%" + n + "s", str2).replace(' ', '0');
            
            StringBuilder temp = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (str1.charAt(j) == '1' || str2.charAt(j) == '1') {
                    temp.append('#');
                } else {
                    temp.append(' ');
                }
            }
            answer.add(temp.toString());
        }
        return answer;
    }
}

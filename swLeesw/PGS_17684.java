import java.util.*;
 
public class Solution {
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution("KAKAO")));
        System.out.println(Arrays.toString(solution("TOBEORNOTTOBEORTOBEORNOT")));
 
    }
 
    public static int[] solution(String msg) {
        List<Integer> indexLists = new ArrayList<>();
        Map<String, Integer> book = new HashMap<>();
 
        char word = 'A';
        for (int i = 1; i <= ('Z' - 'A') + 1; i++) {
            book.put(word + "", i);
            word += 1;
        }
 
        int idx = 0;
        while (idx < msg.length()) {
            String str = "";
            // 해당 번째 인덱스의 문자가 있다면, 그 다음 인덱스의 문자들을 합친 문자열을 반복확인.
            while (idx < msg.length()) {
                if (!book.containsKey(str + msg.charAt(idx))) {
                    break;
                } else {
                    str += msg.charAt(idx);
                }
                idx++;
            }
            indexLists.add(book.get(str));
 
            // 다음 글자까지의 문자열을 사전에 추가
            if (idx < msg.length()) {
                book.put(str + msg.charAt(idx), book.size() + 1);
            }
        }
        return indexLists.stream().mapToInt(Integer::intValue).toArray();
    }
}

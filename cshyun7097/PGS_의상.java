import java.util.*;

public class PGS_의상 {
    public int solution(String[][] clothes) {
        Map<String, Integer> map = new HashMap<>();
        int answer = 1;
        int length = clothes.length;
        for (int i = 0; i < length; i++) {
            map.put(clothes[i][1], map.getOrDefault(clothes[i][1], 0) + 1);
        }
        for (String key : map.keySet()) {
            answer *= (map.get(key) + 1);
        }
        answer -= 1;
        return answer;
    }
}
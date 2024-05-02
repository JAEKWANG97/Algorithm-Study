package hellomatia;

import java.util.*;

class PGS_42578 {
    public int solution(String[][] clothes) {
        Map<String, List<String>> clothesMap = new HashMap<>();

        for (int i = 0; i < clothes.length; i++) {
            List<String> list = clothesMap.getOrDefault(clothes[i][1], new ArrayList<>());
            list.add(clothes[i][0]);
            clothesMap.put(clothes[i][1], list);
        }

        int answer = 1;
        for (String key : clothesMap.keySet()) {
            answer *= (clothesMap.get(key).size() + 1);
        }
        return answer - 1;
    }
}
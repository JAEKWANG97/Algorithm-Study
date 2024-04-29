import java.util.*;

public class PGS_신고결과받기 {
    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];
        Map<String, HashSet<String>> map = new HashMap<>();
        Map<String, Integer> userInfo = new HashMap<>();

        for (int i = 0; i < id_list.length; i++) {
            String name = id_list[i];
            map.put(name, new HashSet<>());
            userInfo.put(name, i);
        }

        for (String s : report) {
            String[] str = s.split(" ");
            String start = str[0];
            String end = str[1];
            map.get(end).add(start);
        }

        for (int i = 0; i < id_list.length; i++) {
            HashSet<String> set = map.get(id_list[i]);
            if (set.size() >= k) {
                for (String name : set) {
                    answer[userInfo.get(name)]++;
                }
            }
        }
        return answer;
    }
}

import java.util.*;
class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        int[] answer = new int[N];
        Map<String, HashSet<String>> map = new HashMap<>();
        Map<String, Integer> idx = new HashMap<>();
 
        for (int i = 0; i < id_list.length; i++) {
            String name = id_list[i];
            map.put(name, new HashSet<>());
            idx.put(name, i);
        }
 
        for (String s : report) {
            String[] str = s.split(" ");
            String from = str[0];
            String to = str[1];
            map.get(to).add(from);
        }
 
        for (int i = 0; i < id_list.length; i++) {
            HashSet<String> send = map.get(id_list[i]);
            if (send.size() >= k) {
                for (String name : send) {
                    answer[idx.get(name)]++;
                }
            }
        }
        return answer;
    }
}

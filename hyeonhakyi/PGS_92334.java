package ex0429;
import java.util.*;
public class PGS_92334 {
    class Solution {
        public int[] solution(String[] id_list, String[] report, int k) {
            int[] answer = new int[id_list.length];
            Map<String, HashSet<String>> map = new HashMap<>();
            Map<String, Integer> idxMap = new HashMap<>();

            for(int i = 0; i < id_list.length; i++){
                String name = id_list[i];
                map.put(name , new HashSet<>());
                idxMap.put(name, i);
            }

            for(int i = 0; i < report.length; i++){
                String[] str = report[i].split(" ");
                String from = str[0];
                String to = str[1];
                map.get(to).add(from);
            }

            for(int i = 0; i < id_list.length; i++){
                HashSet<String> send = map.get(id_list[i]);
                if(send.size() >= k){
                    for(String name : send){
                        answer[idxMap.get(name)]++;
                    }
                }
            }
            return answer;
        }
    }
}

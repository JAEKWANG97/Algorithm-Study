import java.util.*;
import java.io.*;

class Solution {
    
    static String[] id_list;
    static String[] report;
    static int k;
    Map<String, Integer> map = new HashMap<>();
    static boolean visited[][]; // 방문 여부 체크
    
    public int[] solution(String[] _id_list, String[] _report, int _k) {
        StringTokenizer st = null;
        id_list = _id_list;
        report = _report;
        k = _k;
        visited = new boolean[id_list.length][id_list.length];
        int[] answer = new int[id_list.length];
        int[] count = new int[id_list.length];
        
        for (int i = 0; i < id_list.length; i++) {
            map.put(id_list[i], i);
        }
        
        for (int i = 0; i < report.length; i++) {
            st = new StringTokenizer(report[i]);
            int u = map.get(st.nextToken());
            int v = map.get(st.nextToken());
            
            if (!visited[u][v]) { //신고 안했으면 신고 ++
                visited[u][v] = true;
                count[v]++;
            }
        }
        
        for (int i = 0; i < id_list.length; i++) {
            
            if (count[i] >= k) { //신고 횟수  k 이상
                for (int j = 0; j < id_list.length; j++) {
                    if (visited[j][i]) { //신고 이력이 있으면 answer++
                        answer[j]++;
                    }
                }
            }
            
        }
        
        
        
        return answer;
    }
}

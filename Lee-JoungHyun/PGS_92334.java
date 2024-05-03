import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        int N = id_list.length;
        
        StringTokenizer st;
        int[] answer = new int[N];
        
        HashMap<String, Integer> hs = new HashMap<>();
        HashSet<Integer>[] rList = new HashSet[N];
        
        for (int i = 0; i < N; i++) {
            hs.put(id_list[i], i);
            rList[i] = new HashSet<>();
        }
        
        for (String str : report) {
            st = new StringTokenizer(str);
            String p1 = st.nextToken();
            String p2 = st.nextToken();
            
            rList[hs.get(p2)].add(hs.get(p1));
        }
        
        for (HashSet<Integer> list : rList) {
            if (list.size() >= k) {
                for (int add : list) {
                    answer[add]++;
                }
            }
        }
        
        
        return answer;
    }
}
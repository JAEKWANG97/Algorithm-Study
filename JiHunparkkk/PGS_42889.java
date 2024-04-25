import java.util.*;

class Solution {
    public int[] solution(int N, int[] stages) {

        int[] notClear = new int[N + 1];
        int[] clear = new int[N + 1];
        
        for(int i = 0; i < stages.length; i++) {
            for(int j = 0; j < stages[i]; j++) {
                clear[j]++;
            }
            notClear[stages[i] - 1]++;
        }
        
        Map<Integer, Double> map = new HashMap<>();
        for(int i = 0; i < N; i++) {
            if(clear[i] == 0 || notClear[i] == 0) {
                map.put(i + 1, 0.0);
            } else{
                map.put(i + 1, notClear[i] * 1.0 / clear[i]);
            }
        }
        
        List<Integer> keySet = new ArrayList<>(map.keySet());
        keySet.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return Double.compare(map.get(o2), map.get(o1));
            }
        });
        
        int[] answer = new int[N];
        for(int i = 0; i < keySet.size(); i++) {
            if(keySet.get(i) == N + 1){
               answer[i] = keySet.get(i) - 1;
                continue;
            }
            answer[i] = keySet.get(i);
        }
        
       
        
        return answer;
    }
}

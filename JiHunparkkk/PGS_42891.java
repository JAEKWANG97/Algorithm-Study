import java.util.*;

class Solution {
    static class Food implements Comparable<Food>{
        int idx, time;
        
        public Food(int idx, int time){
            this.idx = idx;
            this.time = time;
        }
        
        @Override
        public int compareTo(Food o1){
            return time - o1.time;
        }
    }
    
    public int solution(int[] food_times, long k) {
        int len = food_times.length;
        PriorityQueue<Food> pq = new PriorityQueue<>();
        
        long sum = 0;
        for(int i = 0; i < food_times.length; i++){
            pq.add(new Food(i, food_times[i]));
            sum += food_times[i];
        }
        
        if(sum <= k){
            return -1;
        }
        
        long prevTime = 0;
        long nowTime = 0;
        while(!pq.isEmpty() && (nowTime = (pq.peek().time - prevTime) * len) < k){
            prevTime = pq.poll().time;
            k -= nowTime;
            len = pq.size();
        }
        
        List<Food> remain = new ArrayList<>();
        for(int i = 0; i < len; i++){
            remain.add(pq.poll());
        }
        remain.sort((o1, o2) -> o1.idx - o2.idx);
        
        int resultIdx = (int)(k % len);
        return remain.get(resultIdx).idx + 1;
    }
}

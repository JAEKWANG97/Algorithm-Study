import java.util.*;

class Solution {
    
    private class Food implements Comparable<Food>{
        int idx, lastTime;
        public Food(int idx, int lastTime) {
            this.idx = idx;
            this.lastTime = lastTime;
        }
        
        public int compareTo(Food o1) {
            return this.lastTime - o1.lastTime;
        }
    }
    
    public int solution(int[] food_times, long k) {
        long sumTime = 0;         
        PriorityQueue<Food> pq = new PriorityQueue<>();
        
        for (int i = 0; i < food_times.length; i++) {  
            sumTime += food_times[i];
            pq.add(new Food(i + 1, food_times[i]));
        }
        
        if(sumTime <= k){
            return -1;
        }
        
        sumTime = 0; // 먹기 위해 사용한 시간
        long previous = 0; // 직전에 다 먹은 음식 시간
        long length = food_times.length; // 남은 음식의 개수
        
        while(sumTime + ((pq.peek().lastTime - previous) * length) <= k){
            int now = pq.poll().lastTime;
            sumTime += (now - previous) * length;
            previous = now;
            length--;
        }
        
        ArrayList<Food> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(pq.poll());
        }
        
        Collections.sort(result, (o1, o2) -> o1.idx - o2.idx);
        return result.get((int) ((k-sumTime) % length)).idx;
        
        
    }
    
}
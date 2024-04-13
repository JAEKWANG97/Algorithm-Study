
import java.util.*;

class Food implements Comparable<Food> { 
    private int index;
    private int time;
    
    public Food(int index, int time){
        this.index = index;
        this.time = time;
    }
    
    public int getIndex(){ return this.index; }
    public int getTime(){ return this.time; }
    
    @Override
    public int compareTo(Food other){
        return Integer.compare(this.time, other.getTime());
    }
}

class Solution {
    public int solution(int[] food_times, long k) {
        long sumTime = 0;
        
        for (int i = 0; i < food_times.length; i++) {
            sumTime += food_times[i];
        }
        
        if(sumTime <= k){
            return -1;
        }
        
        PriorityQueue<Food> pq = new PriorityQueue<>();
        for (int i = 0; i < food_times.length; i++) {
            pq.offer(new Food(i + 1, food_times[i]));
        }
        
        sumTime = 0; // 먹기 위해 사용한 시간
        long previous = 0; // 직전에 다 먹은 음식 시간
        long length = food_times.length; // 남은 음식의 개수
        
        while(sumTime + ((pq.peek().getTime() - previous) * length) <= k){
            int now = pq.poll().getTime();
            sumTime += (now - previous) * length;
            previous = now;
            length--;
        }
        
        ArrayList<Food> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(pq.poll());
        }
        
        result.sort(Comparator.comparing(Food::getIndex));
        
        return result.get((int) ((k-sumTime) % length)).getIndex();
    }
}
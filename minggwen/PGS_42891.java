
import java.util.*;
public class PGS_42891 {
    static int len_food;
    static class Food implements Comparable<Food>{
        int index,times;
        public Food(int index, int times){
            this.index = index;
            this.times = times;
        }
        
        @Override
        public int compareTo (Food o){
            return Integer.compare(this.times,o.times);
        }
    }
    public int solution(int[] food_times, long k) {
        long foodSum = 0;
        Queue<Food> que = new PriorityQueue<>();
        for(int idx =0; idx<food_times.length;idx++){
            que.add(new Food(idx+1,food_times[idx]));
            foodSum+=food_times[idx];
        }
        if(foodSum <= k) return -1;
        
        long previous = 0;
        foodSum = 0;
        long length = food_times.length;
        while(foodSum+(que.peek().times-previous)*length<=k){
            long now = que.poll().times;
            foodSum+=(now-previous)*length;
            previous = now;
            length--;
            
        }
        ArrayList<Food> foods = new ArrayList<>();
        while(!que.isEmpty()){
            foods.add(que.poll());
        }
        foods.sort(Comparator.comparing(Food->Food.index));
        
       return foods.get((int)((k - foodSum) % length)).index;
    }
}
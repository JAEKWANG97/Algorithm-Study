import java.util.*;
class Solution {
    static class food implements Comparable<food>{
        int idx;
        int remaintime;
        food(int idx, int remaintime){
            this.idx = idx;
            this.remaintime = remaintime;
        }
        public int compareTo(food o){
            return Integer.compare(this.remaintime,o.remaintime);
        }
    }
    public int solution(int[] food_times, long k) {
        long food_sum = 0;
        PriorityQueue<food> pq = new PriorityQueue<>();
        int idx = 0;
        for(int i : food_times){
            pq.offer(new food(++idx,i));
            food_sum+=i;
        }
        if(food_sum <=k) return -1;
        
        long total = 0;
        long before = 0;
        long length = food_times.length;
        
        while(true){
            if(total+((pq.peek().remaintime-before)*length)>k) break;
            int now = pq.poll().remaintime;
            total+=(now-before)*length;
            length-=1;
            before = now;
        }
        ArrayList<food> result = new ArrayList<>();
        while(!pq.isEmpty()){
            result.add(pq.poll());
        }
        Collections.sort(result, new Comparator<food>(){
            public int compare(food a, food b){
                return Integer.compare(a.idx,b.idx);
            }
        });
        
        return result.get((int)((k-total)%length)).idx;
    }
}

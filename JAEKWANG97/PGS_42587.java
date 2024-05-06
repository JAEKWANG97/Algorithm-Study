import java.util.*;

class Solution {
    static class Item{
        int p;
        int l;
        
        public Item(int p, int l){
            this.p = p;
            this.l = l;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int max = 0;
        Queue<Item> que = new ArrayDeque<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1,o2) -> o2-o1);
        for(int i = 0 ; i < priorities.length ; i++){
            que.add(new Item(priorities[i], i));
            pq.add(priorities[i]);
        }
                    
        int count = 0;
        while(!que.isEmpty() && !pq.isEmpty()){
            Item cur = que.poll();
            if(cur.p == pq.peek()){
                count+=1;
                pq.poll();
                if(cur.l == location){
                    return count;
                }
            }else{
                que.add(cur);
                continue;
            }
            
            
        }
        
        return answer;
    }
}

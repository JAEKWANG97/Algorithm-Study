import java.util.*;

class Solution {
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<int[]> q = new LinkedList<>();
        ArrayList<Integer> list = new ArrayList<>();
        for(int i = 0 ; i<priorities.length ; i++){
            q.add(new int[] {i,priorities[i]});
        }
        while(!q.isEmpty()){
            int[] cur = q.poll();
            boolean flag = true;
            int size = q.size();
            for(int i = 0 ; i<size ; i++){
                int[] next = q.poll();
                if(cur[1]<next[1]) {
                    flag = false;
                }
                q.add(next);
                
            }
            if(!flag) q.add(cur);
            else list.add(cur[0]);
        
        }
        for(int i=0 ; i<list.size() ; i++){
            if(list.get(i)==location){
                answer = i;
                break;
            }
        }
        return answer+1;
    }

}

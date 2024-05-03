import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        Queue<Integer> que = new PriorityQueue<>();
        for(int s:scoville)que.add(s);
        while(!que.isEmpty()){
            int num = que.poll();
            if(num>=K) break;
            else if(num<K&&que.isEmpty())return -1;
            que.offer(num+que.poll()*2);
            answer++;
        }
        return answer;
    }
}

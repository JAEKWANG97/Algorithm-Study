import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.add(s);
        }
        while(!pq.isEmpty() && pq.peek() < K) {
            if (pq.size() < 2) {
                return -1;
            }    
            int f1 = pq.poll();
            int f2 = pq.poll();
            pq.add(f1 + (f2 * 2));
            answer++;
        }
        
        return answer;
    }
}
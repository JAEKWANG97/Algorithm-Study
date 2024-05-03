import java.util.*;

// 더 맵게 / 40분
class PGS_42626 {
    public int solution(int[] scoville, int K) {
        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue();
        for (int s : scoville) {
            pq.offer(s);
        }        
        
        while (pq.peek() < K) {
            if (pq.size() < 2) return -1;
            int min1 = pq.poll();
            int min2 = pq.poll();
            pq.offer(min1 + (min2 * 2));
            count++;
        }
        
        return count;
    }
}
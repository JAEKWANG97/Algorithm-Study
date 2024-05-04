import java.util.*;

class PGS_42626 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (var val : scoville) {
            pq.add(val);
        }
        while (pq.peek() < K) {
            if (pq.size() < 2) {
                return -1;
            }
            pq.add(pq.poll() + pq.poll() * 2);
            answer++;
        }

        return answer;
    }
}

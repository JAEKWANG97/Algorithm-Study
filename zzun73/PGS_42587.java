import java.util.Comparator;
import java.util.PriorityQueue;

public class PGS_42587 {

    public int solution(int[] priorities, int location) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for (int priority : priorities) {
            pq.add(priority);
        }

        while (!pq.isEmpty()) {
            for (int i = 0; i < priorities.length; i++) {
                if (pq.peek() == priorities[i]) {
                    if (i == location) {
                        answer++;
                        return answer;
                    } else {
                        pq.poll();
                        answer++;
                    }
                }
            }
        }

        return answer;
    }
}

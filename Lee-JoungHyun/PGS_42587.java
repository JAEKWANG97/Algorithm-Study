import java.util.*;

class Solution {
    
    private class Process {
        int idx, prior;
        public Process(int idx, int prior) {
            this.idx = idx;
            this.prior = prior;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 1;
        
        Queue<Process> queue = new LinkedList<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i = 0; i < priorities.length; i++) {
            pq.add(priorities[i]);
            queue.add(new Process(i, priorities[i]));
        }
        
        
        while(true) {
            Process now = queue.poll();
            if (now.prior == pq.peek()) {
                // 뽑을 수 있음!
                if (now.idx == location) {
                    return answer;
                }
                pq.poll();
                answer++;
            } else {
                queue.add(now);
            }
        }
        
        //return answer;
    }
}
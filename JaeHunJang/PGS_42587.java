import java.util.*;

// 프로세스 / 60분
class PGS_42587 {
    public int solution(int[] priorities, int location) {
        Queue<int[]> q = new ArrayDeque();
        PriorityQueue<Integer> pq = new PriorityQueue(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2) {
                return Integer.compare(o2, o1);
            }
        });
        
        int i = 0;
        for (int p : priorities) {
            q.offer(new int[] {i++, p});
            pq.offer(p);
        }
        
        int answer = 1;
        while(!q.isEmpty()) {
            int[] p = q.poll();
            if (p[1] < pq.peek()) {
                q.offer(p);   
            } else {
                if (p[0] == location) return answer;
                pq.poll();
                answer++;
            }
            
        }
        
        return answer;
    }
}
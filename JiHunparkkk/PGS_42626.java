import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        //오름차순 정렬 -> 매 로직마다 정렬 필요, 시간초과 가능성 있음 -> 우선순위 큐 사용
        //k이하 숫자가 하나라도 있으면 로직 실행
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < scoville.length; i++) {
            pq.add(scoville[i]);
        }
        
        while(!pq.isEmpty() && pq.peek() < K) {
            if(pq.size() < 2) {
                answer = -1;
                break;
            }
            int poll1 = pq.poll();
            int poll2 = pq.poll();
            
            pq.add(mixFood(poll1, poll2));
            answer++;
        }
        
        return answer;
    }
    
    private static int mixFood(int a, int b) {
        return a + (b * 2);
    }
}

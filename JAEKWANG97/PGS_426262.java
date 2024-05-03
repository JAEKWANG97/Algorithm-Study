import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for(int i = 0 ; i < scoville.length ; i++){
            pq.add(scoville[i]);
        }
        
        while(pq.size() >= 2 && pq.peek() < K){
            
            pq.add(pq.poll() + pq.poll() * 2);
            answer += 1;
            if(pq.peek() >= K){
                return answer;
            }
        }
        if(pq.peek() >= K){
            return answer;
        }
        return -1;
    }
}

// 섞은 음식의 스코빌 지수 = 가장 맵지 않은 음식의 스코빌 지수 + (두 번째로 맵지 않은 음식의 스코빌 지수 * 2)

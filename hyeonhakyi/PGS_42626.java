package ex0504;

import java.util.*;

public class PGS_42626 {
    class Solution {
        public int solution(int[] scoville, int K) {
            int answer = 0;
            PriorityQueue<Integer> que = new PriorityQueue<>();

            for(int i : scoville){
                que.offer(i);
            }

            while(que.peek() < K){
                if(que.size() == 1){
                    return -1;
                }

                int fir = que.poll();
                int sec = que.poll();
                int sco = fir + (sec * 2);
                que.offer(sco);
                answer++;
            }
            return answer;
        }
    }
}

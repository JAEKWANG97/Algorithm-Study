package ex0504;

import java.util.*;

public class PGS_42587 {
    class Solution {
        public int solution(int[] priorities, int location) {
            int answer = 0;
            PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());

            for(int num : priorities){
                que.offer(num);
            }

            while(!que.isEmpty()){
                for(int i = 0; i < priorities.length; i++){
                    if(priorities[i] == que.peek()){
                        que.poll();
                        answer++;
                        if(i == location){
                            return answer;
                        }
                    }
                }
            }
            return answer;
        }
    }
}

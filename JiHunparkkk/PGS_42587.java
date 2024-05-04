import java.util.*;

class Solution {
    private static class Node {
        int idx;
        int num;
        
        public Node (int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;
        int[] numCnt = new int[10];
        Queue<Node> queue = new ArrayDeque<>();
        
        for(int i  = 0; i < priorities.length; i++) {
            queue.add(new Node(i, priorities[i]));      
            numCnt[priorities[i]]++;
        }
        
        while(!queue.isEmpty()) {            
            for(int i = 9; i >= 1; i--) {
                if(numCnt[i] == 0) {
                    continue;
                }
                
                while(numCnt[i] != 0) {
                    Node poll = queue.poll();
                    if(poll.num == i) {
                        numCnt[i]--;
                        answer++;
                        
                        if(poll.idx == location) {
                            return answer;
                        }
                    } else {
                        queue.add(poll);
                    }
                }
            }
        }
        
        return answer;
    }
}

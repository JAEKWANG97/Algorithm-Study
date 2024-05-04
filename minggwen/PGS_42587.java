import java.util.*;
class Solution {
    static class Process implements Comparable<Process>{
        int idx, p;
        public Process(int idx, int p){
            this.idx=idx;
            this.p=p;
        }
        @Override
        public int compareTo(Process o){
            return o.p-this.p;
        }
        @Override
        public String toString(){
            return "idx : "+idx+" p : "+p;
        }
    }
    public int solution(int[] priorities, int location) {
        int answer = 0;
        Queue<Process> pq = new PriorityQueue<>();
        Queue<Process> que = new ArrayDeque<>();
       
        for(int idx=0;idx<priorities.length;idx++){
            pq.add(new Process(idx,priorities[idx]));
            que.add(new Process(idx,priorities[idx]));
        }
        while(true){
            Process p = que.poll();
            if(p.p!=pq.peek().p) que.offer(p);
            else{
                answer++;
                pq.poll();
                if(location==p.idx) break;
            }
        }
        return answer;
    }
}

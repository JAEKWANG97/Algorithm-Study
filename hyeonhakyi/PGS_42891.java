package ex0310;

import java.util.*;

public class PGS_42891 {
    private static class Node implements Comparable<Node>{
        int time;
        int seq;
        public Node(int time,int seq){
            this.time = time;
            this.seq = seq;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.time,o.time);
        }
    }
    public int solution(int[] food_times, long k){
        long food_sum = 0;

        for(int i = 0; i < food_times.length; i++){
            food_sum += food_times[i];
        }

        if(food_sum <= k) return -1;

        PriorityQueue<Node> que = new PriorityQueue<>();
        for(int i = 0; i < food_times.length; i++){
            que.offer(new Node(food_times[i],i+1));
        }

        long totalTime = 0;
        long preTime = 0;
        long count = food_times.length;
        while(totalTime + ((que.peek().time - preTime) * count) <= k) {
            int now = que.poll().time;
            totalTime += (now - preTime) * count;
            count -= 1;
            preTime = now;
        }

        ArrayList<Node> result = new ArrayList<>();
        while (!que.isEmpty()){
            result.add(que.poll());
        }
        Collections.sort(result, new Comparator<Node>(){
            @Override
            public int compare(Node a,Node b){
                return Integer.compare(a.seq,b.seq);
            }
        });

        return result.get((int) ((k-totalTime) % count)).seq;
    }//solution end
}//class end

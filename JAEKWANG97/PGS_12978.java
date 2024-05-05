import java.util.*;

class Solution {
    static class Node implements Comparable<Node>{
        int to;
        int weight;
        
        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
        
        public int compareTo(Node other){
            return Integer.compare(this.weight, other.weight);
        }
        
        public String toString(){
            return "to : " + to + " weight : " + weight; 
        }
    }
    
    public int solution(int N, int[][] road, int K) {
        List<List<Node>> nodeList = new ArrayList<>();
        
        for(int i = 0 ; i <= N ; i++){
            nodeList.add(new ArrayList<>());
        }
        
        for(int[] r : road){
            nodeList.get(r[0]).add(new Node(r[1], r[2]));
            nodeList.get(r[1]).add(new Node(r[0], r[2]));
        }
        
        PriorityQueue<Node> que = new PriorityQueue<>();
        int[] dist = new int[N+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        
        que.add(new Node(1, 0));
        
        while(!que.isEmpty()){
            Node cur = que.poll();
            
            if(dist[cur.to] < cur.weight) continue; 
            
            for(Node next : nodeList.get(cur.to)){
                if(dist[next.to] > dist[cur.to] + next.weight){
                    dist[next.to] = dist[cur.to] + next.weight;
                    que.add(new Node(next.to, dist[next.to]));
                }
            }
        }
        
        int count = 0;
        for(int i = 1; i <= N; i++){
            if(dist[i] <= K) count++;
        }
        
        return count;
    }
}

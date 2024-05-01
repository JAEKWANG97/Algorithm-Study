import java.util.*;

class Solution {
    static class Node{
        int to;
        int weight;
        
        public Node(int to, int weight){
            this.to = to;
            this.weight = weight;
        }
    }
    static List<List<Node>> nodeList;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int answer = Integer.MAX_VALUE;
        nodeList= new ArrayList<>();
        
        for(int i = 0 ; i <= n ; i++){
            nodeList.add(new ArrayList<>());
        }
        for (int[] fare : fares) {
            nodeList.get(fare[0]).add(new Node(fare[1], fare[2]));
            nodeList.get(fare[1]).add(new Node(fare[0], fare[2])); 
        }
        

        for(int i = 1 ; i <= n ; i++){
            int s1 = dijkstra(s,i);
            int a1 = dijkstra(i,a);
            int b1 = dijkstra(i,b);

            if(s1 == -1 || a1 == -1 || b1 == -1) continue;
            
            int sum = s1 + a1 + b1;
            
            answer = Math.min(answer, sum);
        }
        
        return answer;
    }
    
    private static int dijkstra(int start, int target) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        int[] distance = new int[nodeList.size()];
        Arrays.fill(distance, Integer.MAX_VALUE);
        pq.offer(new Node(start, 0));
        distance[start] = 0;

        while(!pq.isEmpty()){
            Node current = pq.poll();
            if(current.to == target){
                return distance[target];
            }
            for(Node next : nodeList.get(current.to)){
                if(distance[next.to] > distance[current.to] + next.weight){
                    distance[next.to] = distance[current.to] + next.weight;
                    pq.offer(new Node(next.to, distance[next.to]));
                }
            }
        }
    
        return -1;

    }
}


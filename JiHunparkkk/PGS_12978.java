import java.util.*;

class Solution {
    
    private static class Node {
        int to;
        int weight;
        
        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    
    public int solution(int N, int[][] road, int K) {        
        List<List<Node>> list = init(N, road);  //입력 초기화
        int[] result = new int[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();
        
        Arrays.fill(result, 500001);
        result[1] = 0;
        queue.add(1);
        
        while(!queue.isEmpty()) {
            int poll = queue.poll();
            List<Node> now = list.get(poll);
            
            for(int i = 0; i < now.size(); i++) {
                Node next = now.get(i);
                
                if(result[next.to] > result[poll] + next.weight) {
                    result[next.to] = result[poll] + next.weight;
                    queue.add(next.to);
                }
            }
        }
        
        int answer = 0;
        for(int i = 1; i <= N; i++) {
            if(result[i] <= K) {
                answer++;
            }
        }

        return answer;
    }
    
    private static List<List<Node>> init(int N, int[][] road) {
        List<List<Node>> list = new ArrayList<>();
        for(int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int w = road[i][2];
            
            list.get(a).add(new Node(b, w));
            list.get(b).add(new Node(a, w));
        }
        return list;
    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
 
class Solution {
	    public static int solution(int N, int[][] road, int K) {
        int answer = 1; 
        ArrayList<ArrayList<Node>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
        	list.add(new ArrayList<>());
		}
        
        for (int i = 0; i < road.length; i++) {
			list.get(road[i][0]).add(new Node(road[i][0],road[i][1],road[i][2]));
			list.get(road[i][1]).add(new Node(road[i][1],road[i][0],road[i][2]));
		}
        
        Queue<Node> q = new LinkedList<>();
        int[] visit = new int[N+1];
        for (int i = 2; i < visit.length; i++) {
        	visit[i] = Integer.MAX_VALUE; // 방문 배열을 모두 max 값으로 갱신
		}
        q.addAll(list.get(1)); // 1번 마을에서 갈수있는 마을 정보 모두 큐에 담음
        
        while(!q.isEmpty()) { // bfs 돌림
        	Node n = q.poll();
        	if(visit[n.x] <= visit[n.y] + n.v)
        		continue;
        	visit[n.x] = visit[n.y] + n.v;
        	q.addAll(list.get(n.x));
        }
        
        for (int i = 2; i < visit.length; i++) {
			if(visit[i] <= K)
				answer++;
		}
        return answer;
    }
    
	static class Node{
    	int y, x, v;
    	Node(int y, int x, int v){
    		this.y = y;
    		this.x = x;
    		this.v = v;
    	}
    }
}

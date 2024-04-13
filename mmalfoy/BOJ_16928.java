
import java.util.*;
import java.io.*;

public class BOJ_16928 {
    
    static class Node {
        int position;
        int count;

        Node(int position, int count) {
            this.position = position;
            this.count = count;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken()); 
        int M = Integer.parseInt(st.nextToken()); 
        
        Map<Integer, Integer> transitions = new HashMap<>();
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            transitions.put(start, end);
        }

        System.out.println(bfs(transitions));
    }

    private static int bfs(Map<Integer, Integer> transitions) {
        Queue<Node> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        
        queue.offer(new Node(1, 0));
        visited[1] = true;
        
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            
            if (current.position == 100) {
                return current.count;
            }

            for (int i = 1; i <= 6; i++) { 
                int nextPosition = current.position + i;
                if (nextPosition > 100) continue;

                // 사다리나 뱀을 통해 이동하기 전에 해당 위치를 방문 처리
                if (!visited[nextPosition]) { 
                    visited[nextPosition] = true;
                    if (transitions.containsKey(nextPosition)) {
                        int finalPosition = transitions.get(nextPosition);
                        if (!visited[finalPosition]) {
                            visited[finalPosition] = true; // 최종 목적지 방문 처리
                            queue.offer(new Node(finalPosition, current.count + 1));
                        }
                    } else {
                        queue.offer(new Node(nextPosition, current.count + 1));
                    }
                }
            }
        }
        
        return -1;
    }
}

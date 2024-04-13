import java.io.*;
import java.util.*;

public class BOJ_17471 {
    static int N;
    static int[] population;
    static List<Integer>[] adj;
    static boolean[] divided;
    static int minDiff = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        population = new int[N + 1];
        divided = new boolean[N + 1];
        adj = new ArrayList[N + 1];
        // 인접 노드 초기화
        for (int i = 1; i <= N; i++) {
            adj[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 노드 별 인구 수 초기화
        for (int i = 1; i <= N; i++) {
            population[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            // 노드 별 인접 노드 초기화
            for (int j = 0; j < M; j++) {
                adj[i].add(Integer.parseInt(st.nextToken()));
            }
        }
        // 구역 나누기
        divideAreas(1);
        System.out.println(minDiff == Integer.MAX_VALUE ? -1 : minDiff);
    }

    static void divideAreas(int index) {
        // 기저 조건
        if (index > N) {
            ArrayList<Integer> area1 = new ArrayList<>();
            ArrayList<Integer> area2 = new ArrayList<>();
            
            //  boolean  배열을 통해 구연 나눠 주기
            for (int i = 1; i <= N; i++) {
                if (divided[i]) {
                    area1.add(i);
                } else {
                    area2.add(i);
                }
            }
            // 어떠 한 구역이라도 0 이면 리턴
            if (area1.isEmpty() || area2.isEmpty()) {
                return;
            }
            
            // 이어져 있지 않다면 리턴
            if (!isConnected(area1) || !isConnected(area2)) {
                return;
            }

            int diff = Math.abs(calculatePopulation(area1) - calculatePopulation(area2));
            minDiff = Math.min(minDiff, diff);
            return;
        }

        
        // 백트레킹 조합 구하기 
        
        // 선택 되었을 때
        divided[index] = true;
        divideAreas(index + 1);
        
        // 선택 안되었을 때
        divided[index] = false;
        divideAreas(index + 1);
    }

    static boolean isConnected(ArrayList<Integer> area) {
        // bfs를 통해 연결성 확보
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> q = new LinkedList<>();
        q.add(area.get(0));
        visited[area.get(0)] = true;
        int count = 1;

        while (!q.isEmpty()) {
            int current = q.poll();
            for (int next : adj[current]) {
                if (!visited[next] && area.contains(next)) {
                    visited[next] = true;
                    q.add(next);
                    count++;
                }
            }
        }

        return count == area.size();
    }

    static int calculatePopulation(ArrayList<Integer> area) {
        // 해당 구역 인구수 정하기
        int total = 0;
        for (int i : area) {
            total += population[i];
        }
        return total;
    }
}

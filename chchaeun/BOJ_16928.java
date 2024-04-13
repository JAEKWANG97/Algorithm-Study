import java.io.*;
import java.util.*;

public class BOJ_16928 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException{
        int answer = 100;
        int N, M;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new LinkedList<>();
        Map<Integer, Integer> moveMap = new HashMap<>();
        boolean[] visited = new boolean[101];

        for (int i = 0; i < N + M; i++){
            st = new StringTokenizer(br.readLine());
            moveMap.put(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        queue.add(new int[]{1, 0});
        visited[1] = true;

        while (!queue.isEmpty()){
            int[] current = queue.poll();

            if (current[0] == 100){
                answer = answer > current[1] ? current[1] : answer;
                break;
            }

            for (int i = 1; i <= 6; i++){
                int newLocation = current[0] + i;
                if (newLocation <= 100 && current[1] + 1 < answer && !visited[newLocation]){
                    if (moveMap.containsKey(newLocation)){
                        queue.add(new int[]{moveMap.get(newLocation), current[1] + 1});
                    }else{
                        queue.add(new int[]{newLocation, current[1] + 1});
                    }
                    visited[newLocation] = true;
                }
            }
        }

        System.out.println(answer);
    }
}

import java.io.*;
import java.util.*;

public class BOJ_10282 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static class Dependency implements Comparable<Dependency>{
        int number;
        int time;

        Dependency(int number, int time){
            this.number = number;
            this.time = time;
        }

        @Override
        public int compareTo(Dependency o) {
            return this.time - o.time;
        }
    }

    public static void main(String[] args) throws IOException{
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Map<Integer, ArrayList<Dependency>> dependencies = new HashMap<>();

            for (int i = 0; i < D; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                if (!dependencies.containsKey(b)){
                    dependencies.put(b, new ArrayList<>());
                }
                dependencies.get(b).add(new Dependency(a, s));
            }

            PriorityQueue<Dependency> queue = new PriorityQueue<>();
            int[] visited = new int[N + 1];
            for (int i = 0; i < N + 1; i++){
                visited[i] = (int) 1e9;
            }

            queue.add(new Dependency(C, 0));
            visited[C] = 0;

            int numOfVirusComputer = 0;
            int totalTime = 0;

            while(!queue.isEmpty()){
                Dependency current = queue.poll();

                if (visited[current.number] == current.time){
                    numOfVirusComputer += 1;
                    totalTime = totalTime < current.time ? current.time : totalTime;
                }

                if (!dependencies.containsKey(current.number)){
                    continue;
                }

                for (Dependency next: dependencies.get(current.number)){
                    if (visited[next.number] > current.time + next.time){
                        queue.add(new Dependency(next.number, current.time + next.time));
                        visited[next.number] = current.time + next.time;
                    }
                }
            }
            sb.append(numOfVirusComputer).append(" ").append(totalTime).append("\n");
        }
        System.out.println(sb);
    }
}

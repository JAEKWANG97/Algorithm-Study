import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
    static int K;
    static int[] visited = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 수빈이 위치
        K = Integer.parseInt(st.nextToken()); // 동생 위치
        int count = bfs(N);
        System.out.println(count);
    }

    private static int bfs(int n) {
        Queue<Integer> queue = new ArrayDeque<>();
        queue.add(n);
        int count = 0;
        visited[n] = 1;
        while(!queue.isEmpty()){
            n = queue.poll();
            if(n == K) return visited[n]-1;
            if(n-1>=0 && visited[n-1]==0){
                visited[n-1] = visited[n]+1;
                queue.add(n-1);
            }
            if(n+1<=100000 && visited[n+1]==0){
                visited[n+1] = visited[n]+1;
                queue.add(n+1);
            }
            if(2*n<=100000 && visited[n*2]==0){
                visited[n*2] = visited[n]+1;
                queue.add(2*n);
            }
        }
        return -1;
    }
}

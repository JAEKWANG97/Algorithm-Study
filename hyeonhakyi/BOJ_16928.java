package ex0312;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {
    private static int result;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[101];

        for(int i = 1; i < 101; i++){
            arr[i] = i;
        }

        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arr[x] = y;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u] = v;
        }
        bfs();
        System.out.println(result);
    }//main end

    private static void bfs(){
        Queue<Integer> que = new LinkedList<>();
        que.offer(1);
        int[] check = new int[101];
        check[1] = 0;

        while(true){
            int nowIdx = que.poll();

            for(int i = 1; i < 7; i++){
                int nextIdx = nowIdx + i;

                if(nextIdx > 100){
                    continue;
                }

                if(check[arr[nextIdx]] == 0){
                    que.offer(arr[nextIdx]);
                    check[arr[nextIdx]] = check[arr[nowIdx]] + 1;
                }

                if(arr[nextIdx] == 100){
                    result = check[nextIdx];
                    return;
                }
            }
        }
    }//bfs end
}//class end

package ex0305;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_11657 {
    private static class Node{
        int x;
        int y;
        int weight;
        public Node(int x,int y,int weight){
            this.x = x;
            this.y = y;
            this.weight = weight;
        }
    }
    private static int n,m;
    private static long[] ans;
    private static long INF;
    private static ArrayList<Node> list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        ans = new long[n+1];
        list = new ArrayList<>();
        INF = Integer.MAX_VALUE;

        Arrays.fill(ans,INF);

        for(int i = 1; i <= m; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.add(new Node(x,y,weight));
        }

        if(!bellman(1)){
            System.out.println(-1);
        }else {
            for (int i = 2; i <= n; i++) {
                if (ans[i] == INF) {
                    System.out.println(-1);
                } else {
                    System.out.println(ans[i]);
                }
            }
        }
    }//main end

    private static boolean bellman(int start) {
        ans[start] = 0;

        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < m; j++) {
                Node node = list.get(j);
                if (ans[node.x] != INF && ans[node.y] > ans[node.x] + node.weight) {
                    ans[node.y] = ans[node.x] + node.weight;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            Node node = list.get(i);

            if (ans[node.x] != INF && ans[node.y] > ans[node.x] + node.weight) {
                return false;
            }
        }
        return true;
    }//bellman end
}//class end

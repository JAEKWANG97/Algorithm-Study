package ex0325;

import java.awt.im.spi.InputMethod;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_13549 {
    private static class Node{
        int x;
        int count;
        public Node(int x,int count){
            this.x = x;
            this.count = count;
        }
    }
    private static boolean[] check = new boolean[100001];
    private static int n,k,result;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        result = Integer.MAX_VALUE;

        if(n == k){
            System.out.println(0);
        }else {
            bfs(n);
            System.out.println(result);
        }
    }//main end

    private static void bfs(int n){
        Queue<Node> que = new LinkedList<>();
        que.offer(new Node(n,0));
        check[n] = true;

        while(!que.isEmpty()){
            Node now = que.poll();
            int x = now.x;
            int count = now.count;

            if(x == k){
                result = Math.min(result,count);
            }

            int next ;
            next = x*2;
            if( next < 100001 &&  !check[next]){
                check[next] = true;
                que.offer(new Node(x*2,count));
            }

            next = x-1;
            if( next >= 0 && !check[next]){
                que.offer(new Node(x-1,count+1));
                check[next] = true;
            }

            next = x+1;
            if(next < 100001 && !check[next]){
                que.offer(new Node(x+1,count+1));
                check[next] = true;
            }
        }
    }//bfs end
}//class end

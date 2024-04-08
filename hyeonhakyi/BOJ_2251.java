package ex0313;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2251 {
    private static class Water{
        int a;
        int b;
        int c;
        public Water(int a,int b,int c){
            this.a = a;
            this.b = b;
            this.c = c;
        }
    }
    private static int maxA,maxB,maxC;
    private static boolean[][][] visited = new boolean[201][201][201];
    private static ArrayList<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        maxA = Integer.parseInt(st.nextToken());
        maxB = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());

        check();
        Collections.sort(list);
        for(int i : list){
            System.out.print(i + " ");
        }
    }//main end

    private static void check(){
        Queue<Water> que = new LinkedList<>();
        que.offer(new Water(0,0,maxC));

        while(!que.isEmpty()){
            Water water = que.poll();
            int a = water.a;
            int b = water.b;
            int c = water.c;

            if(visited[a][b][c] == true){
                continue;
            }

            visited[a][b][c] = true;

            if(a == 0){
                list.add(c);
            }

            //a -> b
            if(a + b >= maxB){
                que.offer(new Water(a-(maxB-b),maxB,c));
            }else{
                que.offer(new Water(0,a+b,c));
            }
            //a -> c
            if(a + c >= maxC){
                que.offer(new Water(a-(maxC-c),b,maxC));
            }else{
                que.offer(new Water(0,b,a+c));
            }
            //b -> c
            if(b + c >= maxC){
                que.offer(new Water(a,b-(maxC-c),maxC));
            }else{
                que.offer(new Water(a,0,b+c));
            }
            //b -> a
            if(b + a >= maxA){
                que.offer(new Water(maxA,b-(maxA-a),c));
            }else{
                que.offer(new Water(b+a,0,c));
            }
            //c -> a
            if(a + c >= maxA){
                que.offer(new Water(maxA,b,c - (maxA-a)));
            }else{
                que.offer(new Water(c+a,b,0));
            }
            //c -> b
            if(b + c >= maxB){
                que.offer(new Water(a,maxB,c-(maxB-b)));
            }else{
                que.offer(new Water(a,c+b,0));
            }
        }
    }//check end
}//class end

package ex0314;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_10282 {
    private static class Computer implements Comparable<Computer>{
        int index;
        int time;
        public Computer(int index,int time){
            this.index = index;
            this.time = time;
        }

        @Override
        public int compareTo(Computer o) {
            return Integer.compare(this.time,o.time);
        }
    }
    private static int n,d,c;
    private static int INF = Integer.MAX_VALUE;
    private static int[] arr;
    private static boolean[] check;
    private static ArrayList<Computer>[] computer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            computer = new ArrayList[n+1];

            for(int i = 1; i < n+1; i++){
                computer[i] = new ArrayList<>();
            }

            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());

                computer[b].add(new Computer(a,s));
            }

            dijkstra(n,c);
            int count = 0;
            int result = 0;
            for(int i : arr){
                if(i != INF){
                    count++;
                    result = Math.max(result,i);
                }
            }
            System.out.println(count + " " + result);
        }
    }//main end
    private static void dijkstra(int n,int start){
        arr = new int[n+1];
        check = new boolean[n+1];

        Arrays.fill(arr,INF);
        arr[start] = 0;

        PriorityQueue<Computer> pq = new PriorityQueue<>();
        pq.offer(new Computer(start,0));

        while(!pq.isEmpty()){
            int nowVertex = pq.poll().index;

            if(check[nowVertex]){
                continue;
            }

            check[nowVertex] = true;

            for(Computer next : computer[nowVertex]){
                if(arr[next.index] > arr[nowVertex] + next.time){
                    arr[next.index] = arr[nowVertex] + next.time;
                    pq.offer(new Computer(next.index,arr[next.index]));
                }
            }
        }
    }//dijkstra end
}//class end

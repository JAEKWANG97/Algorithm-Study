import java.util.*;
import java.io.*;

class BOJ_1744{

    static int getSum(PriorityQueue<Integer> pq){
        Queue<Integer> queue = new LinkedList<>();
        while(!pq.isEmpty()){
            int curr = pq.poll();

            if(pq.isEmpty()) queue.add(curr);
            else{
                int next = pq.poll();
                if(curr==1 || next==1){
                    queue.add(curr);
                    queue.add(next);
                }
                else queue.add(curr*next);
            }
        }

        int res = 0;
        while(!queue.isEmpty()){
            res += queue.poll();
        }
        return res;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> over = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> under = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++){
            int n = Integer.parseInt(br.readLine());
            if(n>0) over.add(n);
            else under.add(n);
        }

        int res = getSum(over) + getSum(under);
        System.out.println(res);
    }
}

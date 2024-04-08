import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13549 {

    static int n;   
    static int s;   

    static boolean visited[];
    static int max = 100000; 

    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        s = sc.nextInt();

        visited = new boolean[max+1];

        djs();

        System.out.println(min);
    }

    static void djs(){
        PriorityQueue<Node> queue = new PriorityQueue<>(((o1, o2) -> o1.time - o2.time));
        queue.add(new Node(n,0));   

        while (!queue.isEmpty()){

             Node now = queue.poll();
            visited[now.v] = true;
             if(now.v == s) min = Math.min(min, now.time);

             if(now.v * 2 <= max && !visited[now.v * 2]) queue.add(new Node(now.v * 2, now.time));
             if(now.v + 1 <= max && !visited[now.v + 1]) queue.add(new Node(now.v + 1, now.time+1));
             if(now.v - 1 >= 0 && !visited[now.v - 1]) queue.add(new Node(now.v - 1, now.time + 1));
        }
    }

    static class Node{
        int v;
        int time;

        public Node(int v,int time){
            this.v  = v;
            this.time = time;
        }
    }
}

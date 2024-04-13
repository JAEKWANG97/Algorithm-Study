package ex0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_1647 {
    private static class Node implements Comparable<Node>{
        int to;
        int from;
        int weight;
        public Node(int to,int from,int weight){
            this.to = to;
            this.from = from;
            this.weight = weight;
        }
        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.weight,o.weight);
        }
    }
    private static int n,m;
    private static int[] arr;
    private static Node[] nodeList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        nodeList = new Node[m];
        arr = new int[n+1];

        for(int i = 1; i<= n; i++){
            arr[i] = i;
        }

        for(int i = 0; i < m; i++){
            st = new StringTokenizer(br.readLine());
            int to = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            nodeList[i] = new Node(to,from,weight);
        }
        Arrays.sort(nodeList);

        int totalCost = 0;
        int nodeCnt = 1;
        int max = 0;
        for(Node node : nodeList){
            if(union(node.to,node.from)){
                totalCost += node.weight;
                max = Math.max(max,node.weight);
            }
        }
        System.out.println(totalCost - max);
    }

    private static boolean union(int to,int from){
        int toArr = find(to);
        int fromArr = find(from);

        if(toArr == fromArr) return false;
        arr[fromArr] = arr[toArr];
        return true;
    }


    private static int find(int a){
        if(arr[a] == a){
            return a;
        }
        return arr[a] = find(arr[a]);
    }
}

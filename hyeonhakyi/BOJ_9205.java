package ex0319;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

import static java.awt.geom.Point2D.distance;

public class BOJ_9205 {
    private static class Node{
        int x;
        int y;
        public Node(int x,int y){
            this.x = x;
            this.y = y;
        }
    }
    private static ArrayList<Node> list = new ArrayList<>();
    private static Node sNode,eNode;
    private static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < t; tc++){
            n = Integer.parseInt(br.readLine());
            list = new ArrayList<>();

            for(int i = 0; i < n+2; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if(i == 0){
                    sNode = new Node(x,y);
                }else if(i == n+1){
                    eNode = new Node(x,y);
                }else{
                    list.add(new Node(x,y));
                }
            }
            if(bfs()){
                System.out.println("happy");
            }else{
                System.out.println("sad");
            }
        }//test_case end
    }//main end

    private static boolean bfs(){
        Queue<Node> que = new LinkedList<>();
        boolean[] visited = new boolean[n];
        que.offer(sNode);

        while(!que.isEmpty()){
            Node now = que.poll();


            if(distance(now.x,now.y,eNode.x,eNode.y) <= 1000) {
                return true;
            }

            for(int i = 0; i < n;i++){
                if(!visited[i]){
                    Node next = list.get(i);

                    if(distance(now.x, now.y, next.x, next.y) <= 1000){
                        que.offer(new Node(next.x, next.y));
                        visited[i] = true;
                    }
                }
            }
        }
        return false;
    }//bfs end

    private static int distance(int startx,int starty,int endx,int endy){
        int result = Math.abs(startx-endx) + Math.abs(starty-endy);
        return result;
    }
}//class end

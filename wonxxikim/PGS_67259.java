import java.util.*;
class Solution {
    static int n, answer;
    static boolean[][][] visit;
    static int[][] delta = {{1,0},{0,1},{-1,0},{0,-1}};
    static class node{
        int x;
        int y;
        int cost;
        int direc;
        node(int x, int y, int cost, int direc){
            this.x = x;
            this.y = y;
            this.cost = cost;
            this.direc = direc;
        }
    }
    public int solution(int[][] board) {
        answer = Integer.MAX_VALUE;
        for(int[] i : board){
            n++;
        }
        visit = new boolean[4][n][n];
        bfs(board);
        return answer;
    }
    public static void bfs(int[][] board){
        Queue<node> q = new LinkedList<>();
        q.add(new node(0,0,0,-1));
        while(!q.isEmpty()){
            node cur = q.poll();
            if(cur.x==(n-1) && cur.y == (n-1)){
                answer = Math.min(answer,cur.cost);
            }
            for(int d = 0 ; d<4 ;d++){
                int nr = cur.x+delta[d][0];
                int nc = cur.y+delta[d][1];
                int cost = cur.cost;
                if(nr<0 || nr>=n || nc<0 || nc>=n || board[nr][nc] == 1) continue;
                if(cur.direc == -1 || cur.direc==d){
                    cost+=100;
                }
                else cost+=600;
                if(!visit[d][nr][nc] || board[nr][nc]>=cost){
                    q.add(new node(nr,nc,cost,d));
                    visit[d][nr][nc] = true;
                    board[nr][nc] = cost;
                }
            }
            
        }
    }
}

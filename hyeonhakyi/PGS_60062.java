package ex0427;

public class PGS_60062 {
    class Solution {
        public static int[] weak_append;
        public static int answer;
        public int solution(int n, int[] weak, int[] dist) {
            answer = Integer.MAX_VALUE;
            weak_append = new int[weak.length*2];
            int i = 0;
            while(i < weak.length){
                weak_append[i] = weak[i];
                weak_append[i + weak.length] = weak[i++]+n;
            }

            for(int k = 0; k < weak.length; k++){
                dfs(k,0,dist,new boolean[dist.length],new int[dist.length]);
            }


            if(answer == Integer.MAX_VALUE){
                return -1;
            }
            return answer;
        }//main end

        public void dfs(int start,int depth, int[] dist, boolean[] visited, int[] permuted){
            if(depth == dist.length){
                answer = Math.min(answer, check(start,start+weak_append.length/2,permuted));
                return;
            }

            for(int i = 0; i < dist.length; i++){
                if(visited[i]){
                    continue;
                }
                visited[i] = true;
                permuted[depth] = dist[i];
                dfs(start,depth+1,dist,visited,permuted);
                visited[i] = false;
            }
        }//dfs end

        public int check(int start,int end,int[] permuted){
            int friend = 1;
            int pos = weak_append[start] + permuted[friend-1];

            for(int i = start; i < end; i++){
                if(pos < weak_append[i]){
                    friend++;
                    if(friend > permuted.length){
                        return Integer.MAX_VALUE;
                    }
                    pos = weak_append[i] + permuted[friend - 1];
                }
            }
            return friend;
        }
    }
}

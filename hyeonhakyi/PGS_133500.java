package ex0504;

import java.util.*;

public class PGS_133500 {
    class Solution {
        static int N,result = 0;
        static List<Integer>[] map;
        public int solution(int n, int[][] lighthouse) {
            N = n;
            map = new ArrayList[n+1];
            for(int i = 0; i <= N; i++){
                map[i] = new ArrayList<>();
            }
            for(int i = 0; i < lighthouse.length; i++){
                int from = lighthouse[i][0];
                int to = lighthouse[i][1];

                map[from].add(to);
                map[to].add(from);
            }

            dfs(1,0);
            return result;
        }
        static int dfs(int now,int before){
            if(map[now].size() == 1 && map[now].get(0) == before){
                return 1;
            }

            int tmp = 0;
            for(int i = 0; i < map[now].size(); i++){
                int next = map[now].get(i);
                if(next == before) continue;
                tmp += dfs(next,now);
            }
            if(tmp == 0){
                return 1;
            }
            result++;
            return 0;
        }
    }
}

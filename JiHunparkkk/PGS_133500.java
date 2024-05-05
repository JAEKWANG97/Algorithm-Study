import java.util.*;

class Solution {
    
    private static List<List<Integer>> list;
    private static int answer;
    
    public int solution(int n, int[][] lighthouse) {        
        list = initList(n, lighthouse);
        dfs(1, 0);
      
        return  answer;
    }
    
    private static List<List<Integer>> initList(int n , int[][] lighthouse) {
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }
        
        for(int i = 0; i < lighthouse.length; i++) {
            int a = lighthouse[i][0];
            int b = lighthouse[i][1];
            
            list.get(a).add(b);
            list.get(b).add(a);
        }
        
        return list;
    }
    
    private static int dfs(int cur, int before) {
        if(list.get(cur).size() == 1 && list.get(cur).get(0) == before) {
            return 1;
        }
        
        int tmp = 0;
        for(int i = 0; i < list.get(cur).size(); i++) {
            int next = list.get(cur).get(i);
            if(next == before) {
                continue;
            }
            tmp += dfs(next, cur);
        }
        
        if(tmp >= 1) {
            answer++;
            return 0;
        }
        
        return 1;
    }
}

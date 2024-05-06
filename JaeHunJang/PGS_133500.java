import java.util.*;

// 등대 / 100분
class PGS_133500 {
    List<Integer>[] list;
    HashSet<Integer> set;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        list = new List[n+1];
        set = new HashSet();
        
        for (int i = 0; i <= n; i++) list[i] = new ArrayList();
        
        for (int[] e : lighthouse) {
            list[e[0]].add(e[1]);
            list[e[1]].add(e[0]);
        }
        
        dfs(1, 0);
        
        return set.size();
    }
    
    void dfs(int cur, int parent) {
        if (list[cur].size() == 1 && list[cur].get(0) == parent) {
            set.add(parent); // leaf와 연결된 노드
            return ; 
        }
        
        for (int next : list[cur]) {
            if (next == parent) continue;
            dfs(next, cur);
            if (!set.contains(next)) set.add(cur); // 이전 노드가 불이 안켜진 노드
        }
        
    }
}
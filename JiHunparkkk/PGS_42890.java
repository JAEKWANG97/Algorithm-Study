import java.util.*;
class Solution {
    static int answer;
    static int n,m;
    static List<HashSet<Integer>> candidateKey;
    static String[][] relationCopy;
    
    public int solution(String[][] relation) {
        relationCopy = relation;
        
        answer = 0;

        candidateKey = new ArrayList<>();
        n = relationCopy.length;
        m = relationCopy[0].length;

        for (int i = 1; i < m + 1; i++) {
            combi(0, i, 0, new HashSet<>());
        }

        return answer;
    }
    
    static void combi(int start, int size, int depth, HashSet<Integer> set) {
        if(depth == size) {
            // 유일성 검사
            if(!unique(set)) {
                return;
            }
            // 최소성 검사
            for (HashSet<Integer> key : candidateKey) {
                if(set.containsAll(key)) {
                    return;
                }
            }
            candidateKey.add(set);
            answer++;
            return;
        }
        for (int i = start; i < m; i++) {
            HashSet<Integer> newSet = new HashSet<>(set);
            newSet.add(i);
            combi(start + 1, size, depth + 1, newSet);
        }
    }

    static boolean unique(HashSet<Integer> set) {
        List<String> list = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int idx : set)  {
                sb.append(relationCopy[i][idx]);
            }
            if(!list.contains(sb.toString())) {
                list.add(sb.toString());
            } else {
                return false;
            }
        }
        return true;
    }
}

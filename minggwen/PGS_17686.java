import java.util.*;

class Solution {
    static int R, C;
    static List<String> candidates;
    static String[][] table;

    public int solution(String[][] relation) {
        R = relation.length;
        C = relation[0].length;
        table = relation;
        candidates = new ArrayList<>();
        for (int bitmask = 1; bitmask < (1 << C); bitmask++) {
            if (unique(bitmask)) {
                candidates.add(Integer.toBinaryString(bitmask));
            }
        }

        for (int i = 0; i < candidates.size(); i++) {
            String current = candidates.get(i);
            for (int j = i + 1; j < candidates.size(); j++) {
                String compare = candidates.get(j);
                if (min(current, compare)) {
                    candidates.remove(j);
                    j--; 
                }
            }
        }

        return candidates.size();
    }

    private boolean unique(int bitmask) {
        Set<String> seen = new HashSet<>();
        for (int r = 0; r < R; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < C; c++) {
                if ((bitmask & (1 << c)) != 0) {
                    sb.append(table[r][c]).append(",");
                }
            }
            if (!seen.add(sb.toString())) {
                return false; 
            }
        }
        return true; 
    }

    private boolean min(String current, String compare) {
        int currentBitmask = Integer.parseInt(current, 2);
        int compareBitmask = Integer.parseInt(compare, 2);
        return (currentBitmask & compareBitmask) == currentBitmask;
    }
}

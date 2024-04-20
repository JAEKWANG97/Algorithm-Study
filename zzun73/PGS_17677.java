import java.util.*;

class PGS_17677 {
    public int solution(String str1, String str2) {
        StringBuilder sb = new StringBuilder();
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        HashMap<String, Integer> map = new HashMap<>();
        int intersection = 0, union = 0;
        for (int i = 0; i < str1.length() - 1; i++) {
            String cur = str1.substring(i, i + 2);
            if (cur.charAt(0) >= 'a' && cur.charAt(0) <= 'z' && cur.charAt(1) >= 'a' && cur.charAt(1) <= 'z') {
                map.put(cur, map.getOrDefault(cur, 0) + 1);
            }
        }

        for (int i = 0; i < str2.length() - 1; i++) {
            String cur = str2.substring(i, i + 2);
            if (cur.charAt(0) >= 'a' && cur.charAt(0) <= 'z' && cur.charAt(1) >= 'a' && cur.charAt(1) <= 'z') {
                if (map.get(cur) != null && map.get(cur) >= 1) {
                    map.put(cur, map.get(cur) - 1);
                    intersection++;
                }
                union++;
            }
        }

        for (int value : map.values()) {
            union += value;
        }

        return union == 0 ? 65536 : (int) (intersection / (double) union * 65536);
    }
}
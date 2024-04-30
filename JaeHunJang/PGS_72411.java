import java.util.*;

// 메뉴 리뉴얼 / 60분
class PGS_72411 {
    HashMap<String, Integer>[] map;
    int[] max;
    
    public String[] solution(String[] orders, int[] course) {
        String[] answer = {};
        
        map = new HashMap[11];
        for (int i = 0; i < map.length; i++) {
            map[i] = new HashMap();
        }
        max = new int[11];
        for (String order : orders) {
            for (int i = 0; i < course.length; i++) {
                char[] sortCourse = order.toCharArray();
                StringBuilder sb = new StringBuilder();
                Arrays.sort(sortCourse);
                sb.append(sortCourse);
                comb(sb.toString(), course[i], 0, 0, "");
            }
        }
        // System.out.println(Arrays.toString(max));
        
        List<String> list = new ArrayList();
        for (int i = 0; i < course.length; i++) {
            if (max[course[i]] < 2) continue;
            Set<String> keySet = map[course[i]].keySet();
            for (String key : keySet) {
                if (map[course[i]].get(key) == max[course[i]]) list.add(key);
            }
            // System.out.println(map[course[i]]);
        }
        Collections.sort(list);
        // System.out.println(list);
        return list.toArray(new String[0]);
    }
    
    void comb(String order, int n, int cnt, int start, String course) {
        if (cnt == n) {
            map[n].put(course, map[n].getOrDefault(course, 0)+1);
            max[n] = Math.max(max[n], map[n].get(course));
            return;
        }
        
        for (int i = start; i < order.length(); i++) {
            comb(order, n, cnt+1, i+1, course+order.charAt(i));
        }
    }
}
import java.util.*;

class Solution {

    public int solution(String str1, String str2) {
        int answer = 0;
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();

        Map<String, Integer> map1 = new HashMap<>();
        Map<String, Integer> map2 = new HashMap<>();

        splitString(str1, map1);
        splitString(str2, map2);

        double unionSize = union(map1, map2);
        double intersectionSize = intersection(map1, map2);
        System.out.println(unionSize);
        if (unionSize != 0) {
            System.out.println(intersectionSize / unionSize);

            return (int) (intersectionSize / unionSize * 65536);
        }else{
            return 65536;
        }

    }

    private static void splitString(String str, Map<String, Integer> map) {
        int n = str.length();

        for (int i = 0; i < n - 1; i++) {
            char temp1 = str.charAt(i);
            char temp2 = str.charAt(i + 1);
            if (temp1 >= 'a' && temp1 <= 'z' && temp2 >= 'a' && temp2 <= 'z') {
                map.put(temp1 + "" + temp2, map.getOrDefault(temp1 + "" + temp2, 0) + 1);
            }
        }
    }

    private static double union(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> temp = new HashMap<>(map1);
        int count = 0;
        for (String key : map2.keySet()) {
            temp.put(key, Math.max(map1.getOrDefault(key, 0), map2.get(key)));
        }

        for (int value : temp.values()) {
            count += value;
        }

        return count;
    }

    private static double intersection(Map<String, Integer> map1, Map<String, Integer> map2) {
        Map<String, Integer> temp = new HashMap<>();
        int count = 0;
        for (String key : map2.keySet()) {
            if (map1.containsKey(key)) {
                temp.put(key, Math.min(map1.get(key), map2.get(key)));
            }
        }

        for (int value : temp.values()) {
            count += value;
        }

        return count;
    }

}

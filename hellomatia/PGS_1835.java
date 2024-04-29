package hellomatia;

import java.util.*;

class PGS_1835 {

    private String[] friends = new String[]{"A", "C", "F", "J", "M", "N", "R", "T"};
    private boolean[] visited;
    private Set<String> set;

    public int solution(int n, String[] data) {
        init();

        for (String d : data) {
            calcData(d);
        }

        return set.size();
    }

    public void init() {
        set = new HashSet<>();
        visited = new boolean[8];
        initSet(0, "");
    }

    public void initSet(int count, String str) {
        if (count == 8) {
            set.add(str);
            return;
        }

        for (int i = 0; i < 8; i++) {
            if (visited[i]) continue;
            visited[i] = true;
            initSet(count + 1, str + friends[i]);
            visited[i] = false;
        }
    }

    public void calcData(String data) {
        char friends1 = data.charAt(0);
        char friends2 = data.charAt(2);
        char command = data.charAt(3);
        int length = data.charAt(4) - '0';

        Set<String> temp = new HashSet<>();
        for (String str : set) {
            int len = 0;
            boolean isStart = false;
            for (int i = 0; i < 8; i++) {

                if (!isStart) {
                    if (str.charAt(i) == friends1 || str.charAt(i) == friends2) {
                        isStart = true;
                    }
                } else {
                    if (str.charAt(i) == friends1 || str.charAt(i) == friends2) {
                        break;
                    }
                    len++;
                }
            }

            if (command == '<') {
                if (len < length) {
                    temp.add(str);
                }
            } else if (command == '>') {
                if (len > length) {
                    temp.add(str);
                }
            } else if (command == '=') {
                if (len == length) {
                    temp.add(str);
                }
            }
        }
        set = temp;
    }
}
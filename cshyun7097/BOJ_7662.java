import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;
import java.util.TreeSet;

public class BOJ_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        TreeMap<Integer, Integer> map;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = null;
            map = new TreeMap<>();
            int k = Integer.parseInt(br.readLine());
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                char id = st.nextToken().charAt(0);
                int num = Integer.parseInt(st.nextToken());
                if (id == 'I') {
                    if (map.get(num) == null) {
                        map.put(num, 1);
                    } else {
                        map.put(num, map.get(num) + 1);
                    }
                } else {
                    if (map.isEmpty()) {
                        continue;
                    } else {
                        if (num == 1) {
                            int tmp = map.lastKey();
                            if (map.get(tmp) == 1) {
                                map.remove(tmp);
                            } else {
                                map.put(tmp, map.get(tmp) - 1);
                            }
                        } else {
                            int tmp = map.firstKey();
                            if (map.get(tmp) == 1) {
                                map.remove(tmp);
                            } else {
                                map.put(tmp, map.get(tmp) - 1);
                            }
                        }
                    }
                }
            }
            if (map.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
            }
        }
        System.out.println(sb);
    }
}
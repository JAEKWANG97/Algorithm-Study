import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7662 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        TreeMap<Integer, Integer> treeMap;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            treeMap = new TreeMap<>();
            int N = Integer.parseInt(br.readLine());
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                char code = st.nextToken().charAt(0);
                int value = Integer.parseInt(st.nextToken());

                if (code == 'I') {
                    if (treeMap.containsKey(value)) {
                        treeMap.put(value, treeMap.get(value) + 1);
                    } else {
                        treeMap.put(value, 1);
                    }
                } else if (!treeMap.isEmpty()) {
                    // 최댓값 삭제
                    if (value == 1) {
                        int tmp = treeMap.lastKey();
                        if (treeMap.get(tmp) == 1) {
                            treeMap.remove(tmp);
                        } else {
                            treeMap.put(tmp, treeMap.get(tmp) - 1);
                        }
                    }
                    // 최솟값 삭제
                    else {
                        int tmp = treeMap.firstKey();
                        if (treeMap.get(tmp) == 1) {
                            treeMap.remove(tmp);
                        } else {
                            treeMap.put(tmp, treeMap.get(tmp) - 1);
                        }
                    }
                }
                //System.out.println(treeMap);
            }

            if (treeMap.isEmpty()) {
                sb.append("EMPTY");
            } else {
                sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey());
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
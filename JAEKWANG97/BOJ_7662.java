package backjoon.treemap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7662 {
    static int T;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb;
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        for (int i = 0; i < T; i++) {
            solve();
        }
        System.out.print(sb);
    }

    private static void solve() throws IOException {
        int n = Integer.parseInt(br.readLine());
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String commend = st.nextToken();
            int num = Integer.parseInt(st.nextToken());

            if (commend.equals("I")) {
                treeMap.put(num, treeMap.getOrDefault(num, 0) + 1);
            } else if (commend.equals("D") && !treeMap.isEmpty()) {
                if (num == 1) {
                    int target = treeMap.lastKey();
                    treeMap.put(target, treeMap.get(target) - 1);
                    if (treeMap.get(target) == 0) {
                        treeMap.remove(target);
                    }
                } else if (num == -1) {
                    treeMap.put(treeMap.firstKey(), treeMap.get(treeMap.firstKey()) - 1);
                    if (treeMap.get(treeMap.firstKey()) == 0) {
                        treeMap.remove(treeMap.firstKey());
                    }
                }
            }
        }

        if (treeMap.isEmpty()) {
            sb.append("EMPTY").append("\n");
        } else {
            sb.append(treeMap.lastKey()).append(" ").append(treeMap.firstKey()).append("\n");
        }

    }
}

import java.io.*;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7662 {
    public static void main(String[] args) throws IOException {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> tmap = new TreeMap<>();

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine(), " ");
                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                if (command.equals("I")) {
                    tmap.put(value, tmap.getOrDefault(value, 0) + 1);
                } else {
                    if (tmap.isEmpty()) {
                        continue;
                    }
                    int deleteValue = value == 1 ? tmap.lastKey() : tmap.firstKey();

                    if (tmap.put(deleteValue, tmap.get(deleteValue) - 1) == 1) {
                        tmap.remove(deleteValue);
                    }
                }
            }
            if (tmap.isEmpty()) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(tmap.lastKey()).append(" ").append(tmap.firstKey()).append("\n");
            }
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
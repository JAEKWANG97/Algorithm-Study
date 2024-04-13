import java.io.*;
import java.util.*;

public class BOJ_2251 {

    static class Bottle {
        int first, second;

        public Bottle(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static List<Integer> helper(int[] limit) {
        boolean[][] visited = new boolean[201][201];
        int[] from = new int[]{0, 0, 1, 1, 2, 2};
        int[] to = new int[]{1, 2, 0, 2, 0, 1};
        Deque<Bottle> deque = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();

        deque.add(new Bottle(0, 0));
        visited[0][0] = true;

        while (!deque.isEmpty()) {
            Bottle bottle = deque.poll();
            int one = bottle.first;
            int two = bottle.second;
            int three = limit[2] - bottle.first - bottle.second;

            if (one == 0) {
                res.add(three);
            }

            for (int i = 0; i < 6; i++) {
                int[] cur = new int[]{one, two, three};

                cur[to[i]] += cur[from[i]];
                cur[from[i]] = 0;

                if (cur[to[i]] > limit[to[i]]) {
                    cur[from[i]] = cur[to[i]] - limit[to[i]];
                    cur[to[i]] = limit[to[i]];
                }

                if (!visited[cur[0]][cur[1]]) {
                    visited[cur[0]][cur[1]] = true;
                    deque.add(new Bottle(cur[0], cur[1]));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[] limit = new int[3];

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < limit.length; i++) {
            limit[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> result = helper(limit);
        Collections.sort(result);

        for (int val : result) {
            sb.append(val).append(" ");
        }

        bw.write(sb.toString());
        br.close();
        bw.close();
    }
}
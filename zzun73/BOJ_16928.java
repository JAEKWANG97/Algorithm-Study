import java.io.*;
import java.util.*;

public class BOJ_16928 {
    static int answer;

    static class Pos implements Comparable<Pos> {
        int position, count;

        public Pos(int position, int count) {
            this.position = position;
            this.count = count;
        }

        @Override
        public int compareTo(Pos o) {
            return this.count - o.count;
        }
    }

    static void helper(Map<Integer, Integer> road) {
        int[] cnt = new int[101];
        Arrays.fill(cnt, Integer.MAX_VALUE);
        Deque<Pos> deque = new ArrayDeque<>();
        deque.add(new Pos(1, 0));
        while (!deque.isEmpty()) {
            Pos cur = deque.poll();

            if (cur.position == 100) {
                answer = cur.count;
                return;
            }

            for (int i = 1; i <= 6; i++) {
                int nextPosition = cur.position + i;
                int nextTime = cur.count + 1;

                if (nextPosition > 100) {
                    continue;
                }

                if (nextTime < cnt[nextPosition]) {
                    if (road.containsKey(nextPosition)) {
                        deque.add(new Pos(road.get(nextPosition), nextTime));
                        cnt[nextPosition] = nextTime;
                    } else {
                        deque.add(new Pos(nextPosition, nextTime));
                        cnt[nextPosition] = nextTime;
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        Map<Integer, Integer> road = new HashMap<>();
        answer = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine(), " ");

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            road.put(a, b);
        }
        helper(road);

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}

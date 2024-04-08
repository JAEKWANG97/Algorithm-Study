package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1931 {

    static class Conference implements Comparable<Conference> {
        int start;
        int end;

        public Conference(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Conference o) {
            int value = this.end - o.end;
            if (value == 0) {
                value = this.start - o.start;
            }
            return value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        PriorityQueue<Conference> pq = new PriorityQueue<>();
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            pq.offer(new Conference(start, end));
        }

        Conference cf = pq.poll();

        int prevEnd = cf.end;
        int count = 1;
        while (!pq.isEmpty()) {
            Conference cur = pq.poll();

            if (cur.start >= prevEnd) {
                count += 1;
                prevEnd = cur.end;
            }
        }

        System.out.println(count);
    }
}

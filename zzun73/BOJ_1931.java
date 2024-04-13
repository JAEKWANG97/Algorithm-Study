import java.io.*;
import java.util.*;


public class BOJ_1931 {
    static class Task implements Comparable<Task> {
        int start, end;

        public Task(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public int compareTo(Task o) {
            if (this.end == o.end) {
                return this.start - o.start;
            }
            return this.end - o.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Task> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            pq.add(new Task(s, e));
        }

        int time = 0, answer = 0;
        while (!pq.isEmpty()) {
            Task cur = pq.poll();
            if (time <= cur.start) {
                time = cur.end;
                answer++;
            }
        }
        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}

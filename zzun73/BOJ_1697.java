import java.io.*;
import java.util.*;

public class BOJ_1697 {
    static class Person {
        int position, time;

        public Person(int position, int time) {
            this.position = position;
            this.time = time;
        }
    }

    private static final int MAX_SIZE = 100_000;
    private static final int MIN_SIZE = 0;
    static int N, K, answer;
    static Deque<Person> deque;
    static boolean[] visited;

    static void helper() {
        deque.add(new Person(N, 0));
        while (!deque.isEmpty()) {
            Person cur = deque.poll();

            if (cur.position == K) {
                answer = Math.min(answer, cur.time);
                break;
            }

            if (cur.position * 2 <= MAX_SIZE && !visited[cur.position * 2]) {
                visited[cur.position * 2] = true;
                deque.add(new Person(cur.position * 2, cur.time + 1));
            }
            if (cur.position - 1 >= MIN_SIZE && !visited[cur.position - 1]) {
                visited[cur.position - 1] = true;
                deque.add(new Person(cur.position - 1, cur.time + 1));
            }
            if (cur.position + 1 <= MAX_SIZE && !visited[cur.position + 1]) {
                visited[cur.position + 1] = true;
                deque.add(new Person(cur.position + 1, cur.time + 1));
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        deque = new ArrayDeque<>();
        visited = new boolean[MAX_SIZE + 1];
        answer = Integer.MAX_VALUE;

        helper();

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BOJ_1946 {

    private static class Person implements Comparable<Person>{
        int score1, score2;

        public Person(int score1, int score2) {
            this.score1 = score1;
            this.score2 = score2;
        }

        @Override
        public int compareTo(Person other) {
            if (this.score1 < other.score1)
                return -1;
            return 1;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader  br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder   sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Person> pq = new PriorityQueue<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int s1 = Integer.parseInt(st.nextToken());
                int s2 = Integer.parseInt(st.nextToken());
                pq.add(new Person(s1, s2));
            }
            int tmpMax = N + 1;
            int ans = 0;
            while (!pq.isEmpty()) {
                Person now = pq.poll();
                if (now.score2 < tmpMax) {
                    tmpMax = now.score2;
                    ans++;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}

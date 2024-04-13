import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.PriorityQueue;

public class BOJ_5052 {
    private static class Num implements Comparable<Num>{
        char[] num;
        public Num(char[] num) {
            this.num = num;
        }
        @Override
        public int compareTo(Num o) {
            for (int i = 0; i < Math.min(this.num.length, o.num.length); i++) {
                if (this.num[i] > o.num[i]) {
                    return 1;
                }
                else if (this.num[i] < o.num[i]) {
                    return -1;
                }
            }
            return 0;
        }
        public boolean equalsToMin(Num o) {
            int minLength = Math.min(this.num.length, o.num.length);
            for (int i = 0; i < minLength; i++) {
                if (this.num[i] != o.num[i]) return false;
            }
            return true;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            PriorityQueue<Num> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) {
                pq.offer(new Num(br.readLine().toCharArray()));
            }
            boolean flag = false;
            Num past = pq.poll();
            while (!pq.isEmpty()) {
                Num now = pq.poll();
                //System.out.println(Arrays.toString(past.num) + " vs " + Arrays.toString(now.num));
                if (past.equalsToMin(now)) {
                    flag = true;
                    pq.clear();
                }
                past = now;
            }
            if (flag)
                sb.append("NO");
            else
                sb.append("YES");
            sb.append("\n");
        }
        System.out.println(sb);

    }
}

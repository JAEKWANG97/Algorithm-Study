package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1697 {
    static int answer = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if (N == K) {
            System.out.println(0);
            return;
        }
        HashSet<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        visited.add(N);
        int answer = 0;
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            while (qsize-- > 0) {
                int poz = queue.poll();
                if (poz == K) {
                    queue.clear();
                    break;
                }
                if (poz > 0 && !visited.contains(poz-1)) {
                    visited.add(poz - 1);
                    queue.add(poz - 1);
                }
                if (poz < K && !visited.contains(poz+1)) {
                    visited.add(poz + 1);
                    queue.add(poz + 1);
                }
                if (poz < K && !visited.contains(poz*2)) {
                    visited.add(poz * 2);
                    queue.add(poz * 2);
                }

            }
            answer++;
        }
        System.out.println(answer-1);
    }
}

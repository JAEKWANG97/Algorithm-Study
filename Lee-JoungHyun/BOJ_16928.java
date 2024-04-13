import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] map = new int[101];
        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map[start] = end;
        }
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        visited[1] = true;
        int depth = 0;
        int now, nxt;
        queue.add(1);
        while (!queue.isEmpty()) {
            int qsize = queue.size();
            depth++;
            //System.out.println("depth: " + depth);
            while (qsize-- > 0) {
                now = queue.poll();
                //System.out.println(now);
                for (int i = 0; i < 7; i++) {
                    if (now + i > 100) break;
                    if (map[now + i] == 0) {
                        nxt = now + i;
                    }
                    else {
                        nxt = map[now + i];
                    }

                    if (nxt == 100) {
                        queue.clear();
                        qsize = 0;
                        break;
                    }
                    if (nxt < 100 && !visited[nxt]) {
                        visited[nxt] = true;
                        queue.add(nxt);
                    }
                }

            }
        }
        System.out.println(depth);



    }
}

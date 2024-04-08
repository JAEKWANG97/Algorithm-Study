import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2458 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
      
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        LinkedList<Integer>[] goBig = new LinkedList[N + 1];
        LinkedList<Integer>[] goSmall = new LinkedList[N + 1];

        for (int i = 1; i < N + 1; i++) {
            goBig[i] = new LinkedList<>();
            goSmall[i] = new LinkedList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            goBig[s].add(b);
            goSmall[b].add(s);
        }
        int ans = 0;
        for (int i = 1; i < N + 1; i++) {
            int sCnt = findCnt(goSmall, i);
            int bCnt = findCnt(goBig, i);

            if (sCnt + bCnt == N - 1) {
                ans++;
            }
        }
      
        System.out.println(ans);
    }
    private static int findCnt(LinkedList<Integer>[] go, int n) {
        int cnt = 0;
        boolean visited[] = new boolean[go.length];
        visited[n] = true;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(n);

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int nxt :go[now]) {
                if (!visited[nxt]) {
                    visited[nxt] = true;
                    cnt++;
                    queue.add(nxt);
                }
            }
        }

        return cnt;
    }
}

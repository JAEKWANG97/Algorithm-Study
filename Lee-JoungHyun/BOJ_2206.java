import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_2206 {
    private static class Poz {
        int m, n, bCnt;
        public Poz(int m, int n, int bcnt) {
            this.m = m;
            this.n = n;
            this.bCnt = bcnt;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        char[][] map = new char[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = br.readLine().toCharArray();
        }
        int[] dn = {0, 1, 0, -1};
        int[] dm = {1, 0, -1, 0};
        boolean[][][] visited = new boolean[2][M][N];
        Queue<Poz> queue = new LinkedList<>();
        visited[0][0][0] = true;
        queue.add(new Poz(0, 0, 0));
        int depth = 1;
        Poz now;
        while (!queue.isEmpty()) {

            int qsize = queue.size();
            //System.out.println("d: " + depth);
            while (qsize-- > 0) {

                now = queue.poll();
                //System.out.println(now.m + " " + now.n);
                if (now.n == N - 1 && now.m == M - 1) {
                    System.out.println(depth);
                    return;
                }
                for (int d = 0; d < 4; d++) {
                    int nm = now.m + dm[d];
                    int nn = now.n + dn[d];
                    if (0 <= nm && nm < M && 0 <= nn && nn < N) {
                        if (map[nm][nn] == '1') {
                            if (now.bCnt == 0 && !visited[1][nm][nn]) {
                                visited[1][nm][nn] = true;
                                queue.add(new Poz(nm, nn, 1));
                            }
                        } else if (!visited[now.bCnt][nm][nn]) {
                            visited[now.bCnt][nm][nn] = true;
                            queue.add(new Poz(nm, nn, now.bCnt));
                        }
                    }
                }
            }
            depth++;
        }
        System.out.println(-1);
    }
}

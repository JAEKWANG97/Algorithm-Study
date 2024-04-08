import java.io.*;
import java.util.*;


public class BOJ_9205 {

    static class Pos {
        int x, y;

        public Pos(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static boolean isLink(Pos p1, Pos p2) {
        return 1000 >= (Math.abs(p1.x - p2.x) + Math.abs(p1.y - p2.y));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());

        while (t-- > 0) {
            int N = Integer.parseInt(br.readLine());
            boolean[][] link = new boolean[N + 2][N + 2];
            List<Pos> edges = new ArrayList<>();

            for (int i = 0; i < N + 2; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                edges.add(new Pos(x, y));
            }

            for (int i = 0; i < N + 2; i++) {
                for (int j = i + 1; j < N + 2; j++) {
                    if (isLink(edges.get(i), edges.get(j))) {
                        link[i][j] = link[j][i] = true;
                    }
                }
            }

            for (int k = 0; k < N + 2; k++) {
                for (int i = 0; i < N + 2; i++) {
                    for (int j = 0; j < N + 2; j++) {
                        if (link[i][k] && link[k][j]) {
                            link[i][j] = true;
                        }
                    }
                }
            }
            sb.append(link[0][N + 1] ? "happy" : "sad").append("\n");
        }
        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
=======
import java.util.Arrays;
>>>>>>> 1dcc6c63031b04a801691e4261138e4ac1a99ee2
import java.util.StringTokenizer;

public class BOJ_11660 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N + 1][N + 1];

        for (int y = 1; y < N + 1; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 1; x < N + 1; x++) {
                map[y][x] = map[y - 1][x] + map[y][x - 1] - map[y - 1][x - 1] + Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int y1 = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            int ans = map[y2][x2] - map[y1 - 1][x2] - map[y2][x1 - 1] + map[y1 - 1][x1 - 1];
            sb.append(ans).append("\n");
        }
<<<<<<< HEAD

        System.out.println(sb);
    }
}
=======
        System.out.println(sb);
    }
}
>>>>>>> 1dcc6c63031b04a801691e4261138e4ac1a99ee2

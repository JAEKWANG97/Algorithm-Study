import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2660 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        int N = Integer.parseInt(br.readLine());
        //List<Integer>[] graph = new LinkedList[N + 1];
        int[][] distance = new int[N + 1][N + 1];

        for (int y = 1; y < N + 1; y++) {
            Arrays.fill(distance[y], 100000);

        }

        while (true) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            if (n1 == -1 && n2 == -1) break;
            distance[n1][n2] = 1;
            distance[n2][n1] = 1;
        }

        for (int k = 1; k < N + 1; k++) {
            for (int i = 1; i < N + 1; i++) {
                for (int j = 1; j < N + 1; j++) {
                    int minDis = Math.min(distance[i][j], distance[i][k] + distance[k][j]);
                    distance[i][j] = minDis;
                    distance[j][i] = minDis;
                }
            }
        }

        int min = 10000;
        List<Integer> answer = new LinkedList<>();
        for (int y = 1; y < N + 1; y++) {
            int max = 0;
            for (int x = 1; x < N + 1; x++) {
                if (x == y || distance[y][x] == 10000) continue;
                max = Math.max(max, distance[y][x]);
            }

            if (max < min) {
                answer.clear();
                answer.add(y);
                min = max;
            }
            else if (min == max) {
                answer.add(y);
            }
        }
        System.out.println(min + " " + answer.size());
        for (int s : answer) {
            System.out.print(s + " ");
        }

    }
}
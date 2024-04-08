import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_8983 {
    static int M, N, L;
    static int[] hunter;
    static int[][] animal;

    private static boolean search(int idx) {

        int left = 0, right = M - 1;
        while (left <= right) {
            int mid = (left + right) / 2;
            int dist = Math.abs(hunter[mid] - animal[idx][0]) + animal[idx][1];
            if (dist <= L) {
                return true;
            } else if (animal[idx][0] < hunter[mid]) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        hunter = new int[M];
        st = new StringTokenizer(br.readLine(), " ");

        for (int i = 0; i < M; i++) {
            hunter[i] = Integer.parseInt(st.nextToken());
        }

        animal = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            animal[i][0] = Integer.parseInt(st.nextToken());
            animal[i][1] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(hunter);

        int answer = 0;
        for (int i = 0; i < N; i++) {
            answer += search(i) ? 1 : 0;
        }

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
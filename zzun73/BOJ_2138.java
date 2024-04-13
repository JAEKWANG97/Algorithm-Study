import java.io.*;

public class BOJ_2138 {
    private static final int INF = Integer.MAX_VALUE;

    static int compareLight(int n, char[] src, char[] dest, int i) {
        if (src[i - 1] != dest[i - 1]) {
            for (int j = i - 1; j <= i + 1; j++) {
                if (j >= n) {
                    continue;
                }
                src[j] = src[j] == '0' ? '1' : '0';
            }
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[] src1 = br.readLine().toCharArray();
        char[] src2 = src1.clone();
        char[] dest = br.readLine().toCharArray();
        int answer1 = 0, answer2 = 1;

        src2[0] = src2[0] == '0' ? '1' : '0';
        src2[1] = src2[1] == '0' ? '1' : '0';

        for (int i = 1; i < N; i++) {
            answer1 += compareLight(N, src1, dest, i);
            answer2 += compareLight(N, src2, dest, i);
        }

        if (src1[N - 1] != dest[N - 1]) {
            answer1 = INF;
        }
        if (src2[N - 1] != dest[N - 1]) {
            answer2 = INF;
        }

        bw.write(String.valueOf(answer1 == INF && answer2 == INF ? -1 : Math.min(answer1, answer2)));

        br.close();
        bw.close();
    }
}
import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1946 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[] grade = new int[N + 1];
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int document = Integer.parseInt(st.nextToken());
                int interview = Integer.parseInt(st.nextToken());
                grade[document] = interview;
            }

            int answer = 1;
            int min = grade[1];
            for (int i = 2; i < N + 1; i++) {
                if (grade[i] < min) {
                    answer++;
                    min = grade[i];
                }
            }
            bw.write(answer + "\n");
        }

        br.close();
        bw.close();
    }
}

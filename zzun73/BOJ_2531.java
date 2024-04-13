import java.io.*;
import java.util.StringTokenizer;

public class BOJ_2531 {
    static int N, d, k, c, count, answer;
    static int[] table, choice;

    static void init() {
        choice[c]++;
        answer = 1;
        for (int i = 0; i < k; i++) {
            if (choice[table[i]] == 0) {
                answer++;
            }
            choice[table[i]]++;
        }
        count = answer;
    }

    static void calcAnswer() {
        for (int i = 1; i < N; i++) {
            if (choice[table[i - 1]] == 1) {
                count--;
            }
            choice[table[i - 1]]--;


            if (choice[table[(i + k - 1) % N]] == 0) {
                count++;
            }
            choice[table[(i + k - 1) % N]]++;
            answer = Math.max(answer, count);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        table = new int[N];
        choice = new int[d + 1];
        for (int i = 0; i < N; i++) {
            table[i] = Integer.parseInt(br.readLine());
        }

        init();
        calcAnswer();

        bw.write(String.valueOf(answer));

        br.close();
        bw.close();
    }
}
package BOJ;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_2138 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[] start = br.readLine().toCharArray();
        char[] target = br.readLine().toCharArray();
        int answer = go(start, 0, N, target);

        start[0] = start[0] == '0' ? '1' : '0';
        start[1] = start[1] == '0' ? '1' : '0';
        answer = Math.min(answer, go(start, 1, N, target));
        if (answer == Integer.MAX_VALUE)
            System.out.println(-1);
        else
            System.out.println(answer);

    }
    static int go(char[] st, int answer, int N, char[] target) {
        char[] tmp = Arrays.copyOf(st, st.length);
        for(int i = 1; i < N-1; i++) {
            if (tmp[i-1] != target[i-1]) {
                answer++;
                tmp[i-1] = tmp[i-1] == '0' ? '1' : '0';
                tmp[i] = tmp[i] == '0' ? '1' : '0';
                tmp[i+1] = tmp[i+1] == '0' ? '1' : '0';
            }
        }
        if (tmp[N-1] != target[N-1]) {
            answer++;
            tmp[N-1] = tmp[N-1] == '0' ? '1' : '0';
            tmp[N-2] = tmp[N-2] == '0' ? '1' : '0';
        }
        for (int i = 0; i < N; i++)
            if (tmp[i] != target[i]) {
                return Integer.MAX_VALUE;
            }
        return answer;

    }
}
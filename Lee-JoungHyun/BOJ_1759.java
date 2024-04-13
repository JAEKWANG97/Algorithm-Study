import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1759 {
    static int R, N, tmp, vowel;

    static char[] code, alph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        alph = new char[N];
        code = new char[R];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            alph[i] = st.nextToken().charAt(0);
        }
        Arrays.sort(alph);
        // 전략
        // 1. 입력받은 알파벳 조합으로 뽑기
        // 2. 뽑은 조합을 모음들을 비트마스킹한 flag(vowel) 로 모음 개수 확인하기
        // 3. 모음 개수가 1보다 크고 L-2개 보다 작다면(자음이 2개 이상이면) 유효한 코드라 보고 출력

        // 모음들 비트마스킹한 값
        vowel = (1) + (1<<'e' - 'a') + (1<<'i' - 'a') + (1<<'o' - 'a') + (1<<'u' - 'a');
        // 알파벳을 조합으로 뽑기
        makePer(0, 0);
    }
    static void makePer(int cnt, int start) {
        if (cnt == R) {
            tmp = 0;
            for (int i = 0; i < R; i++) {
                // 조합으로 뽑은 코드가 모음인지 검사 후 모음 개수 증가시켜주기
                if ((vowel & 1<<code[i] - 'a') == 1<<code[i] - 'a')
                    tmp++;
            }
            // 모음 개수가 1보다 크고 모음이 아닌것이 2개 이상 있으면 만족!
            if (0 < tmp && tmp <= R-2) {
                for(int i = 0; i < R; i++)
                    System.out.print(code[i]);
                System.out.println();
            }
            return;
        }
        for(int i = start; i < N; i++) {
            code[cnt] = alph[i];
            makePer(cnt+1, i+1);
        }
    }
}

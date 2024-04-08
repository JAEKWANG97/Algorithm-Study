
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ_1759 {
    static String[] alpha;
    static String[] key;
    static int L,C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); // 문자열길이
        C = Integer.parseInt(st.nextToken()); // 가능한알파벳
        alpha = new String[C];
        key = new String[L];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            alpha[i] = st.nextToken();
        }
        Arrays.sort(alpha);
//        System.out.println(Arrays.toString(alpha));

        print(0,0,0,0);

    }

    private static void print(int start, int cnt, int mo, int ja) {
        if(cnt == L) {
            if(mo <1) return;
            else if(ja < 2) return;
            for (int i = 0; i < L; i++) {
                System.out.print(key[i]);
            }
            System.out.println();
            return;
        }
        for (int i = start; i < C; i++) {
            key[cnt] = alpha[i];
            if(alpha[i].equals("a") || alpha[i].equals("e") || alpha[i].equals("i") || alpha[i].equals("o") || alpha[i].equals("u")) {
                print(i+1,cnt+1,mo+1,ja);
            }
            else {
                print(i+1,cnt+1,mo,ja+1);
            }

        }

    }



}

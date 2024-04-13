package backjoon;

import java.io.*;
import java.util.*;


public class BOJ_1759 {


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb;
    static List<String> VOWEL = Arrays.asList("a", "e", "i", "o", "u");
    static int L, C;

    static String[] arr;


    public static void main(String[] args) throws IOException {
        init();
        combination(0, 0, new boolean[C]);
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken()); //암호 문자열 수
        C = Integer.parseInt(st.nextToken()); //문자 수
        arr = new String[C];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < C; i++) {
            arr[i] = st.nextToken();
        }
        Arrays.sort(arr);
    }

    //최소 한 개의 모음(a, e, i, o, u)
    //최소 두 개의 자음으로 구성
    //증가하는 순서로 배열
    //문자의 종류는 C가지
    //두 정수 L, C
    //알파벳 소문자이며, 중복되는 것은 없

    private static void combination(int start, int depth, boolean[] visited) {
        if (depth == L) {
            List<String> pw = confirmCombiAndGetPw(visited);
            if (pw != null) {
                for (int i = 0; i < L; i++) {
                    System.out.print(pw.get(i));
                }
                System.out.println();
            }
            return;
        }

        for (int i = start; i < C; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(i + 1, depth + 1, visited);
                visited[i] = false;
            }
        }
    }

    private static List<String> confirmCombiAndGetPw(boolean[] visited) {
        List<String> pw = new ArrayList<>();
        int vowelCount = 0;
        int consonantCount = 0;
        for (int i = 0; i < C; i++) {
            if (visited[i]) {
                pw.add(arr[i]);
                if (VOWEL.contains(arr[i])) {
                    vowelCount += 1;
                } else {
                    consonantCount += 1;
                }
            }
        }

        if (vowelCount >= 1 && consonantCount >= 2) {
            return pw;
        }

        return null;
    }

}

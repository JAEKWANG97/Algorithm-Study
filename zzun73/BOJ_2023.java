import java.io.*;

public class BOJ_2023 {
    static int N;
    static StringBuilder sb = new StringBuilder();

    //1자리부터 N자리까지 늘려가며 소수인 수 판별
    static void helper(int depth, int number) {
        // N자리 완성됐을 경우
        if (depth == N) {
            sb.append(number).append("\n");
            return;
        }
        for (int i = 0; i < 10; i++) {
            int cur = (number * 10) + i; // 자릿수 증가하며 숫자 생성
            if (isPrime(cur)) {
                helper(depth + 1, cur);
            }
        }
    }

    // 소수 판정
    static boolean isPrime(int val) {
        for (int i = 2; i <= (int) Math.sqrt(val); i++) {
            if (val % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        helper(1, 2);
        helper(1, 3);
        helper(1, 5);
        helper(1, 7);

        bw.write(sb.toString());

        br.close();
        bw.close();
    }
}
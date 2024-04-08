import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2023 {
    static int N;
    static StringBuilder sb;

    private static boolean isPrime(int num) {           //제곱근까지만 해서 소수를 구하듯 num의 반까지 소수인지 팔별해서 나누어떨어지지 않으면 소수이다.
        for (int i = 2; i < (num / 2) + 1; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static void dfs(int number) {               //신기한 소수를 구하는 메서드 -> 자리수를 늘려가며 4자리수까지 소수가 충족되면 출력
        if (String.valueOf(number).length() == N) {
            sb.append(number).append("\n");             // 기저조건 : 자리수가 N이면 종료 + StringBuilder에 추가
        } else {
            for (int i = 1; i < 10; i++) {
                if (i % 2 == 0) {                       // 짝수를 넣으면 소수기 때문에 짝수를 거른다.
                    continue;
                }
                if (isPrime(number * 10 + i)) {     //number를 앞자리로 넣고 뒤에 i를 붙혀서 소수인지 확인
                    dfs(number * 10 + i);         //소수면 재귀로 다시 판별
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        sb = new StringBuilder();

        dfs(2);
        dfs(3);
        dfs(5);
        dfs(7);

        System.out.println(sb);
    }
}

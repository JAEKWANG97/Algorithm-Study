import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> sosu = new ArrayList<>();

        //에라토스테네스의 체
        makeSosu(sosu, N);

        //2부터 더해가면서 출발
        int start = 0, sum = 0, answer = 0;
        for (int i = 0; i < sosu.size(); i++) { //i는 끝에 값
            sum += sosu.get(i);

            while (sum > N) {
                sum -= sosu.get(start++);
            }

            if (sum == N) {
                answer++;
            }
        }

        System.out.println(answer);
    }

    private static void makeSosu(List<Integer> sosu, int N) {
        boolean[] noSosu = new boolean[N + 1];

        noSosu[0] = noSosu[1] = true;
        for (int i = 2; i * i <= N; i++) {
            if (!noSosu[i]) {
                for (int j = i * i; j <= N; j += i) {
                    noSosu[j] = true;
                }
            }
        }

        for (int i = 0; i <= N; i++) {
            if (!noSosu[i]) {
                sosu.add(i);
            }
        }
    }
}


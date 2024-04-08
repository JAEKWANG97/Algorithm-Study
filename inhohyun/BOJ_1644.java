import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1644 {
    public static void main(String[] args) throws  IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = solution(n);

        System.out.println(answer);
    }

    static int solution(int n) {
        int cnt = 0;
        List<Integer> list = isPrime(n); // 소수가 판별되어있는 리스트 받아오기
        int l = 0;
        int r = 0;
        int sum = 0; // 2부터 더할 것

        while (true) {
            if(sum >= n) sum -= list.get(l++);

            else if(r == list.size()) break;

            else sum += list.get(r++);

            if(n == sum) cnt++;
        }
        return cnt;
    }

    //n 이하의 소수 판정을 저장할 배열
    // n 이하의 소수 판정을 저장할 배열
    static List<Integer> isPrime(int n) {
        List<Integer> list = new ArrayList<>();	// n이하의 소수들이 들어있는 리스트
        boolean[] prime = new boolean[n + 1];


        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i *i <= n; i++) {
            if(!prime[i]) {
                for(int j = i *i; j<= n; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (!prime[i]) {
                list.add(i);
            }
        }

        return list;
    }
}

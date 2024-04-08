package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BOJ_5052 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스의 개수
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 전화번호의 수
            String[] phoneNumbers = new String[n];

            for (int i = 0; i < n; i++) {
                phoneNumbers[i] = br.readLine(); // 전화번호 입력
            }

            Arrays.sort(phoneNumbers); // 전화번호를 사전순으로 정렬

            boolean consistent = true; // 일관성 있는지 확인하는 변수
            for (int i = 0; i < n - 1; i++) {
                // 현재 번호가 다음 번호의 접두어인지 확인
                if (phoneNumbers[i + 1].startsWith(phoneNumbers[i])) {
                    consistent = false;
                    break;
                }
            }

            if (consistent) {
                sb.append("YES");
            } else {
                sb.append("NO");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
}

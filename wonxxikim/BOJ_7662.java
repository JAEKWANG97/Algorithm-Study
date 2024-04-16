import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());    // 테스트케이스의 개수

        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());    // 연산의 개수
            TreeMap<Integer, Integer> map = new TreeMap<>();    // 이중 우선순위 큐 역할을 할 TreeMap

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine(), " ");

                char cmd = st.nextToken().charAt(0);    // 연산 명령을 구분하는 변수
                int value = Integer.parseInt(st.nextToken());   // 연산 명령에 따라 삽입/삭제할 값

                if (cmd == 'I') {   // 삽입 연산
                    // TreeMap이 입력받은 정수를 갖고 있다면 그 value값에 + 1, 그렇지 않다면 1을 삽입
                    map.put(value, map.getOrDefault(value, 0) + 1);
                }
                else {  // 삭제 연산
                    if (map.isEmpty()) continue;    // TreeMap이 비어있다면 연산 X
                    else {
                        // TreeMap이 제공하는 firstKey()와 lastKey() 이용
                        int n = (value == 1) ? map.lastKey() : map.firstKey();
                        if (map.put(n, map.get(n) - 1) == 1) {  // 해당 정수의 개수를 -1 해주면서 만약 0개가 된다면 삭제
                            map.remove(n);
                        }
                    }
                }
            }

            if (map.isEmpty()) sb.append("EMPTY").append('\n'); // TreeMap이 비어있으면 "EMPTY" 저장
            else sb.append(map.lastKey()).append(' ').append(map.firstKey()).append('\n');  // 그렇지 않다면 최솟값, 최댓값 저장
        }

        System.out.println(sb);
    }

}

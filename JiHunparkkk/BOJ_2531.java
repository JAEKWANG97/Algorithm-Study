import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, d, k, c;
        N = Integer.parseInt(st.nextToken());   //접시 수
        d = Integer.parseInt(st.nextToken());   //초밥의 가짓수
        k = Integer.parseInt(st.nextToken());   //연속 접시 수
        c = Integer.parseInt(st.nextToken());   //쿠폰 번호
        int[] many = new int[d + 1];
        int[] arr = new int[N];
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            //연속해서 먹는 접시 수 초기화
            if (i < k) {
                if (++many[arr[i]] == 1) {    //중복 접시 없으면 총 갯수 증가
                    cnt++;
                }
            }
        }

        int answer = 0;
        //슬라이딩 윈도우, i는 시작점을 의미
        for (int i = 0; i < N; i++) {
            if (answer <= cnt) { //정답 값보다 현재 초밥갯수가 많으면 저장
                answer = cnt;
                if (many[c] == 0) {   //쿠폰이 중복되지 않으면 +1
                    answer++;
                }
            }

            //시작값 제거
            if (--many[arr[i]] == 0) {
                cnt--;
            }

            //윈도우 이동
            if (++many[arr[(i + k) % N]] == 1) {
                cnt++;
            }
        }

        System.out.println(answer);
    }
}


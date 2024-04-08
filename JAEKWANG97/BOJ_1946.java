// 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을 세웠다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.

// 이러한 조건을 만족시키면서, 진영 주식회사가 이번 신규 사원 채용에서 선발할 수 있는 신입사원의 최대 인원수를 구하는 프로그램을 작성하시오.

public class Main {
    static class Score implements Comparable<Score> {
        int x;
        int y;

        public Score(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Score o) {
            return this.x - o.x;
        }
    }

    static int T;
    static int N;

    static int minX = Integer.MAX_VALUE;
    static int minY = Integer.MAX_VALUE;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static Score[] list;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            init();
            solve();
        }
        System.out.println(sb);
    }

    private static void init() throws NumberFormatException, IOException {
        N = Integer.parseInt(br.readLine());
        list = new Score[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            list[i] = new Score(x, y);
        }
    }

    // 다른 모든 지원자와 비교했을 때 서류심사 성적과 면접시험 성적 중 적어도 하나가 다른 지원자보다 떨어지지 않는 자만 선발한다는 원칙을
    // 세웠다.
    // 어떤 지원자 A의 성적이 다른 어떤 지원자 B의 성적에 비해 서류 심사 결과와 면접 성적이 모두 떨어진다면 A는 결코 선발되지 않는다.
    // 오름 차순으로 한 후에 비교
    private static void solve() {
        Arrays.sort(list);
        int count = 0;
        for (int i = 0; i < list.length; i++) {
            if (list[i].y < minY || count == 0) {
                count++;
                minY = list[i].y;
            }
        }
        sb.append(count).append("\n");
    }
}

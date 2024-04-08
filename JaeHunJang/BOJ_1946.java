import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

// 1946. 신입 사원 / 60분
public class BOJ_1946 {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static int score[];

    static class Score {
        int idx, s1, s2, rank, rank2;

        public Score(int idx, int s1, int s2) {
            this.idx = idx;
            this.s1 = s1;
            this.s2 = s2;
            rank = s1 + s2;
            rank2 = Math.max(s1, s2);
        }


        @Override
        public String toString() {
            return "Score{" +
                    "idx=" + idx +
                    ", s1=" + s1 +
                    ", s2=" + s2 +
                    ", rank=" + rank +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        init();
        System.out.println(sb.toString());
    }

    private static void init() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            N = Integer.parseInt(br.readLine());
            score = new int[N + 1];

            int s1, s2;
            for (int i = 1; i <= N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                s1 = Integer.parseInt(st.nextToken()); // 서류 성적
                s2 = Integer.parseInt(st.nextToken()); // 면접 성적
                score[s1] = s2; // 서류 성적 기준으로 면접 성적을 넣으면 자동 정렬!
            }

            solve();
        }

    }

    private static void solve() {
        int cnt = 1; // 1등은 반드시 뽑힘, 1로 시작
        int min = score[1];
        for (int i = 2; i <= N; i++){
            if (min > score[i]){
				min = score[i];
                cnt++;
            }
        }
		sb.append(cnt).append("\n");
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_20056 {
    private static class FireBall implements Comparable<FireBall>{
        int y, x, m, s, d;
        public FireBall(int y, int x, int m, int s, int d) {
            this.y = y;
            this.x = x;
            this.m = m;
            this.s = s;
            this.d = d;
        }
        @Override
        public int compareTo(FireBall o) {
            if (this.y != o.y)
                return this.y - o.y;
            return this.x - o.x;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            FireBall fireBall = (FireBall) o;
            return y == fireBall.y && x == fireBall.x;
        }
        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }

        @Override
        public String toString() {
            return "FireBall{" +
                    "y=" + y +
                    ", x=" + x +
                    ", m=" + m +
                    ", s=" + s +
                    ", d=" + d +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {

        //System.out.println(-9 % 3);

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());


        ArrayList<FireBall> balls = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            balls.add(new FireBall(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }// 입력 끝

        int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
        int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
        int[] sameDir = {0, 2, 4, 6};
        int[] difDir = {1, 3, 5, 7};

        // 시뮬레이션 시작
        for (int step = 0; step < K; step++) {
            // 이동시키기
            for (FireBall ball : balls) {
                ball.y = (N + ball.y + dy[ball.d] * (ball.s % N)) % N;
                ball.x = (N + ball.x + dx[ball.d] * (ball.s % N)) % N;
            }
            // y, x 기준으로 정렬시키기
            Collections.sort(balls);
            ArrayList<FireBall> subList = new ArrayList<>();
            int idx = 0;
            while (idx < balls.size() - 1) {
                // 내 옆이 나랑 다르면 무시
                if (!(balls.get(idx).equals(balls.get(idx + 1))))
                    idx++;
                // 내 옆이 나랑 같으면 어디까지 같은지 확인
                else {
                    int nxtIdx = idx + 1;
                    int sumM = balls.get(idx).m + balls.get(nxtIdx).m;
                    int sumS = balls.get(idx).s + balls.get(nxtIdx).s;
                    boolean evenFlag = balls.get(idx).d % 2 == 0 ? true : false;
                    boolean totalFlag = true;
                    if (evenFlag != (balls.get(nxtIdx).d % 2 == 0))
                        totalFlag = false;
                    while (nxtIdx + 1< balls.size() && balls.get(idx).equals(balls.get(nxtIdx + 1))) {
                        sumM += balls.get(nxtIdx + 1).m;
                        sumS += balls.get(nxtIdx + 1).s;
                        if (evenFlag != (balls.get(nxtIdx + 1).d % 2 == 0))
                            totalFlag = false;
                        nxtIdx++;
                    }
                    if (sumM / 5 > 0) {
                        for (int i = 0; i < 4; i++) {
                            if (totalFlag) {
                                subList.add(new FireBall(balls.get(idx).y, balls.get(idx).x,sumM / 5, sumS / (nxtIdx - idx + 1), sameDir[i]));
                            } else {
                                subList.add(new FireBall(balls.get(idx).y, balls.get(idx).x, sumM / 5, sumS / (nxtIdx - idx + 1), difDir[i]));
                            }
                        }
                    }
                    // i 부터 nxtIdx 까지 같음 ㅇㅇ
                    for (int i = 0; i < nxtIdx - idx + 1; i++) {
                        balls.remove(idx);
                    }
                }
            }
            balls.addAll(subList);
            //System.out.println(step + " " + balls);
        }

        //System.out.println(balls);
        int answer = 0;
        for (FireBall ball : balls) {
            answer += ball.m;
        }
        System.out.println(answer);

    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_3190 {
    private static class Pos {
        int y, x;
        public Pos(int y, int x) {
            this.y = y;
            this.x = x;
        }
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pos pos = (Pos) o;
            return y == pos.y && x == pos.x;
        }
        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }

        @Override
        public String toString() {
            return "Pos{" +
                    "y=" + y +
                    ", x=" + x +
                    '}';
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine()); // 보드 길이
        int appleCnt = Integer.parseInt(br.readLine());
        HashSet<Pos> apples = new HashSet<>(); // 사과 위치
        //HashSet<Pos> body = new HashSet<>();
        Queue<Pos> body = new LinkedList<>();
        char[] turn = new char[10001]; // 회전 횟수
        int[] dx = {0, 1, 0, -1};
        int[] dy = {-1, 0, 1, 0};
        int dir = 1;
        Pos head = new Pos(0, 0);
        body.add(head);

        for (int i = 0; i < appleCnt; i++) {
            st = new StringTokenizer(br.readLine());
            apples.add(new Pos(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1));
        }

        int turnCnt = Integer.parseInt(br.readLine());
        for (int i = 0; i < turnCnt; i++) {
            st = new StringTokenizer(br.readLine());
            turn[Integer.parseInt(st.nextToken())] = st.nextToken().charAt(0);
        } // 입력 끝

        int time = 1;
        while (true) {



            Pos nxtHead = new Pos(head.y + dy[dir], head.x + dx[dir]);

            // 종료 조건 검사
            if (nxtHead.y < 0 || nxtHead.y >= N || nxtHead.x < 0 || nxtHead.x >= N || body.contains(nxtHead))
                break;

            // 사과인지
            if (apples.contains(nxtHead)) {
                apples.remove(nxtHead);
                body.add(nxtHead);
            }
            // 빈칸
            else {
                body.poll();
                body.add(nxtHead);
            }
            head = nxtHead;

            if (turn[time] == 'L') {
                dir = (dir + 3) % 4;
            } else if (turn[time] == 'D') {
                dir = (dir + 1) % 4;
            }
            
            time++;
        }
        System.out.println(time);
    }
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_2251 {
    private static class State {
        int A, B, C, nowA, nowB, nowC;
        public State(int a, int b, int c, int nowA, int nowB, int nowC) {
            A = a;
            B = b;
            C = c;
            this.nowA = nowA;
            this.nowB = nowB;
            this.nowC = nowC;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return nowA == state.nowA && nowB == state.nowB && nowC == state.nowC;
        }


        @Override
        public int hashCode() {
            return Objects.hash(nowA, nowB, nowC);
        }

        public State XtoY(char X, char Y) {
            int na = nowA, nb = nowB, nc = nowC;
            if (X == 'A') {
                if (Y == 'B') {
                    if (nowA > B - nowB) {
                        nb = B;
                        na = nowA - (B - nowB);
                    }
                    else {
                        nb = nowB + nowA;
                        na = 0;
                    }
                }
                else {
                    if (nowA > C - nowC) {
                        nc = C;
                        na = nowA - (C - nowC);
                    }
                    else {
                        nc = nowC + nowA;
                        na = 0;
                    }
                }
            }
            else if (X == 'B') {
                if (Y == 'A') {
                    if (nowB > A - nowA) {
                        na = A;
                        nb = nowB - (A - nowA);
                    }
                    else {
                        na = nowA + nowB;
                        nb = 0;
                    }
                }
                else {
                    if (nowB > C - nowC) {
                        nc = C;
                        nb = nowB - (C - nowC);
                    }
                    else {
                        nc = nowC + nowB;
                        nb = 0;
                    }

                }
            }
            else if (X == 'C') {
                if (Y == 'A') {
                    if (nowC > A - nowA) {
                        na = A;
                        nc = nowC - (A - nowA);
                    }
                    else {
                        na = nowA + nowC;
                        nc = 0;
                    }
                }
                else {
                    if (nowC > B - nowB) {
                        nb = B;
                        nc = nowC - (B - nowB);
                    }
                    else {
                        nb = nowB + nowC;
                        nc = 0;
                    }
                }
            }
            return new State(A, B, C, na, nb, nc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        HashSet<State> visited = new HashSet<>();
        Queue<State> queue = new LinkedList<>();
        TreeSet<Integer> ans = new TreeSet<>();

        char[][] dir = {{'C', 'A'}, {'C', 'B'}, {'A', 'B'}, {'A', 'C'}, {'B', 'A'}, {'B', 'C'}};

        State now = new State(A, B, C,0, 0, C), nxt;
        visited.add(now);
        queue.add(now);

        while (!queue.isEmpty()) {
            now = queue.poll();
            if(now.nowA == 0) ans.add(now.nowC);
            for (int i = 0; i < 6; i++) {
                nxt = now.XtoY(dir[i][0], dir[i][1]);
                if (!visited.contains(nxt)) {
                    visited.add(nxt);
                    queue.add(nxt);
                }
            }
        }

        for (Integer an : ans) {
            System.out.printf(an + " ");
        }
        // 1. Set 을 stream().sorted()로 정렬하면 안됨... -> TreeSet 사용시 pq 처럼 작동 (red black tree 로 구현됨)
        // 2. TreeSet 에 하나씩 넣는것과 Set 에 다 넣고 TreeSet 에 addAll 하는것의 시간 차이
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class CT_왕실의기사대결 {
    static int L, N, Q, map[][], deltas[][] = {{-1,0}, {0,1}, {1,0}, {0,-1}};
    static Knight knights[];

    static Order orders[];
    static StringBuilder sb = new StringBuilder();

    static class Order {
        int i, d;

        public Order(int i, int d) {
            this.i = i;
            this.d = d;
        }
    }
    static class Knight {
        int r, c, h, w, k, totalDmg, dmg, nr, nc;

        public Knight(int r, int c, int h, int w, int k) {
            this.r = r;
            this.c = c;
            this.h = h;
            this.w = w;
            this.k = k;
            dmg = 0;
            totalDmg = 0;
            nr = -1;
            nc = -1;
        }

        public void clear() {
            dmg = 0;
            nr = -1;
            nc = -1;
        }

        @Override
        public String toString() {
            return "Knight{" +
                    "r=" + r +
                    ", c=" + c +
                    ", h=" + h +
                    ", w=" + w +
                    ", k=" + k +
                    ", hp=" + totalDmg +
                    '}';
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        map = new int[L][L];

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < L; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        knights = new Knight[N+1];
        int r, c, h, w, k;
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            knights[i] = new Knight(r, c, h, w, k);
        }

        orders = new Order[Q];
        int j, d;
        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            j = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            orders[i] = new Order(j, d);
        }

        solve();

        System.out.println(sb.toString());
    }

    static void solve() {
        for (Order order : orders) {
            move(order);
//            System.out.println(Arrays.toString(knights));
//            printArr(map);
        }
    }

    static void move(Order o) {
        Queue<Integer> q = new ArrayDeque<>();
        boolean isMoved[] = new boolean[N+1];
        isMoved[o.i] = true;
        q.offer(o.i);

        while(!q.isEmpty()) {
            int cur = q.poll();

            knights[cur].nr = knights[cur].r + deltas[o.d][0];
            knights[cur].nc = knights[cur].c + deltas[o.d][1];

            if (knights[cur].nr >= 0 && knights[cur].nr < L && knights[cur].nc >= 0 && knights[cur].nc < L) {
                int dmg = 0;
                for (int i = knights[cur].nr; i < knights[cur].nr+ knights[cur].h; i++) {
                    for (int j = knights[cur].nc; j < knights[cur].nc+ knights[cur].w; j++) {
                        if (map[knights[cur].nr][knights[cur].nc] == 1) dmg++; // 기사 위치에 있는 함정 개수
                        if (map[knights[cur].nr][knights[cur].nc] == 2) { // 벽에 막혀 갈수 없는 상태
                            for (int k = 1; k <= N; k++) {
                                knights[k].clear();
                            }
                            return;
                        }
                    }
//                    if (!isMoved[cur]) break;
                }
                knights[cur].dmg = dmg;
            }
//            if (!isMoved[cur]) break;

            for (int i = 1; i <= N; i++) { // 충돌한 기사 있는지 확인
                if (!isMoved[i] && knights[i].dmg > 0) {
                    if (knights[i].r > knights[cur].nr + knights[cur].h - 1 || knights[cur].nr > knights[i].r + knights[i].h - 1)
                        continue;
                    if (knights[i].c > knights[cur].nc + knights[cur].w - 1 || knights[cur].nc > knights[i].c + knights[i].w - 1)
                        continue;
                    isMoved[i] = true;
                    q.offer(i);
                }
            }
        }

        for (int i = 1; i <= N; i++) { // 위치 이동
            if (knights[i].nr != -1 && knights[i].nc != -1) {
                knights[i].r = knights[i].nr;
                knights[i].c = knights[i].nc;
                knights[i].totalDmg += knights[i].dmg;
                knights[i].clear();
            }
        }


        int temp[][] = new int[L][L];
        for (int n = 1; n <= N; n++) {
            for (int i = knights[n].r; i < knights[n].r+ knights[n].h; i++) {
                for (int j = knights[n].c; j < knights[n].c+ knights[n].w; j++) {
                    temp[i][j] = n;
                }
            }
        }
//        printArr(temp);

    }






    static void printArr(int map[][]) {
        for (int i = 0; i < map.length; i++) {
            System.out.println(Arrays.toString(map[i]));
        }
        System.out.println();
    }
}

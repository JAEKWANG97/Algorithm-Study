import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static class Cannon {
        int Attack, last, y, x;
        public Cannon(int attack, int last, int y, int x) {
            Attack = attack;
            this.last = last;
            this.y = y;
            this.x = x;
        }
        // 공격 기준 우선순위 높으면 -, 낮으면 +, 같으면 0;
        public int compareTo(Cannon o) {
            if (this.Attack != o.Attack) {
                return this.Attack - o.Attack;
            }
            else if (this.last != o.last) {
                return o.last - this.last;
            }
            else {
                return 0;
            }
        }

        @Override
        public String toString() {
            return this.Attack + "";
        }
    }

    private static LinkedList<Cannon> route = null;
    private static int routeLength = Integer.MAX_VALUE;
    private static boolean[][] visited = null;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int Y = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Cannon[][] map = new Cannon[Y][X];

        for (int y = 0; y < Y; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < X; x++) {
                int tmp = Integer.parseInt(st.nextToken());
                Cannon c = new Cannon(tmp, 0, y, x);
                map[y][x] = c;
            }
        }

//        for (int y = 0; y < Y; y++)
//            System.out.println(Arrays.toString(map[y]));
        for (int i = 0; i < K; i++) {
            routeLength = Integer.MAX_VALUE;
            route = new LinkedList<>();

            // 공격자, 수비자 찾기
            Cannon[] atdf = select(map);
            map[atdf[0].y][atdf[0].x].Attack += X + Y;
            //System.out.println("공 :" + atdf[0].y + atdf[0].x + " 수 : " + atdf[1].y + atdf[1].x);
            // 레이저 경로 반환 (못찾으면 null)
            visited = new boolean[Y][X];
            visited[atdf[0].y][atdf[0].x] = true;
            LinkedList<Cannon> list = new LinkedList<>();
            list.add(atdf[0]);

            DFS(list, map, atdf[1]);


            // 처리
            // 포탄
            int dy[] = {-1, -1, 0, 1, 1, 1, 0, -1};
            int dx[] = {0, 1, 1, 1, 0, -1, -1, -1};
            visited = new boolean[Y][X];

            int power = atdf[0].Attack;
            map[atdf[1].y][atdf[1].x].Attack = Math.max(map[atdf[1].y][atdf[1].x].Attack - power, 0);
            visited[atdf[1].y][atdf[1].x] = true;
            visited[atdf[0].y][atdf[0].x] = true;
            //System.out.println("경로 : " + route);
            if (route.isEmpty()) {
                for (int d = 0; d < 8; d++) {
                    int nY = (Y + atdf[1].y + dy[d]) % Y;
                    int nX = (X + atdf[1].x + dx[d]) % X;
                    //System.out.println("포탄");
                    if (!visited[nY][nX]) {
                        map[nY][nX].Attack = Math.max(map[nY][nX].Attack - power / 2, 0);
                        visited[nY][nX] = true;
                    }
                }
            }
            // 레이저
            else {
                for (Cannon c : route) {
                    map[c.y][c.x].Attack = Math.max(map[c.y][c.x].Attack - power / 2, 0);
                    visited[c.y][c.x] = true;
                }

            }
            // 정비
            for (int y = 0; y < Y; y++) {
                for (int x = 0; x < X; x++) {
                    if (!visited[y][x] && map[y][x].Attack != 0)
                        map[y][x].Attack++;
                }
            }

//            for (int y = 0; y < Y; y++)
//                System.out.println(Arrays.toString(map[y]));
//            System.out.println();

        }

        Cannon[] answer = select(map);
        System.out.println(answer[1].Attack);
    }
    // 공격자와 수비자 찾기
    private static Cannon[] select(Cannon[][] map) {
        int Y = map.length;
        int X = map[0].length;
        Cannon attack = map[0][0].Attack == 0 ? new Cannon(5001, 0, 0, 0) : map[0][0];
        Cannon defense = map[0][0];
        int gap = 1;

        int[] dx = new int[]{0, -1};
        int[] dy = new int[]{1, 0};

        while (gap < Math.max(X, Y)) {
            int x = gap >= X ? X - 1 : gap;
            int y = gap >= X ? Y - 1 : 0;
            int dir = 0;

            while (x >= 0) {
                if (y >= Y) break;
                if (map[y][x].Attack != 0) {
                    if (attack.compareTo(map[y][x]) >= 0) {
                        attack = map[y][x];
                    }
                    if (defense.compareTo(map[y][x]) < 0) {
                        defense = map[y][x];
                    }
                }
                if (y == gap && x == (gap >= X ? X - 1 : gap)) dir++;
                y += dy[dir];
                x += dx[dir];
            }
            gap++;
        }

        return new Cannon[]{attack, defense};
    }

    // DFS 로 경로 찾아 반환 (길이가 0이면 대포 쏘기)
    private static void DFS(LinkedList<Cannon> c, Cannon[][] map, Cannon defense) {
        //System.out.println(c + " " + route);
        if (c.size() >= routeLength || c.isEmpty()) return;
        if (c.getLast().y == defense.y && c.getLast().x == defense.x) {
            route.clear();
            for (Cannon con : c) {
                route.add(con);
            }
            route.remove(0);
            route.remove(route.size() - 1);
            routeLength = route.size() + 2;
            return;
        }

        int dx[] = {1, 0, -1, 0};
        int dy[] = {0, 1, 0, -1};
        int Y = map.length;
        int X = map[0].length;
        for (int d = 0; d < 4; d++) {
            int nxtY = (Y + c.getLast().y + dy[d]) % Y;
            int nxtX = (X + c.getLast().x + dx[d]) % X;
            if (map[nxtY][nxtX].Attack == 0 || visited[nxtY][nxtX]) continue;
            visited[nxtY][nxtX] = true;
            c.add(new Cannon(0, 0, nxtY, nxtX));
            DFS(c, map, defense);
            visited[nxtY][nxtX] = false;
            c.remove(c.size() - 1);

        }
        visited[c.getLast().y][c.getLast().x] = false;
        //c.remove(c.size() - 1);
    }
}

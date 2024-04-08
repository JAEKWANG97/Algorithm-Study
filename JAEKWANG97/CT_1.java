package codeTree;

import java.io.*;
import java.util.*;

public class CT_1.java {

    static class Location {
        int x;
        int y;

        List<Location> route;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
            this.route = new ArrayList<>();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }

        @Override
        public String toString() {
            return "Location [x=" + x + ", y=" + y + "]";
        }

    }

    // 포탑
    static class Turret implements Comparable<Turret> {
        // 좌표
        int x;
        int y;

        // 공격력
        int attack;
        // 가장 최근에 한 공격 시간
        int time;

        boolean isAttacked;

        public Turret(int x, int y, int attack, int time) {
            this.x = x;
            this.y = y;
            this.attack = attack;
            this.time = time;
            this.isAttacked = false;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Turret turret = (Turret) o;
            return x == turret.x && y == turret.y && attack == turret.attack && time == turret.time;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, attack, time);
        }

        @Override
        public int compareTo(Turret o) {
            int value = this.attack - o.attack;
            if (value == 0) {
                value = this.time - o.time;
                if (value == 0) {
                    value = (o.x + o.y) - (this.x + this.y);
                    if (value == 0) {
                        value = o.y - this.y;
                    }
                }
            }
            return value;
        }

        @Override
        public String toString() {
            return "Turret{" +
                    "x=" + x +
                    ", y=" + y +
                    ", attack=" + attack +
                    ", time=" + time +
                    ", isAttacked=" + isAttacked +
                    '}';
        }
    }

    static BufferedReader br;
    static StringTokenizer st;
    static int N, M, K;
    static TreeSet<Turret> turretTreeSet;
    static Turret[][] map;
    static Turret attacker, target;
    static List<Turret> laserAttackList;

    // 우/하/좌/상
    static int[] dx = new int[] { 0, 1, 0, -1 };
    static int[] dy = new int[] { 1, 0, -1, 0 };

    // map을 순회하면서 계속 체크 해야함 (한 턴마다 트리셋에 담아야 한다는 뜻)
    public static void main(String[] args) throws IOException {
        init();
        simulate();
    }

    private static void init() throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // row
        M = Integer.parseInt(st.nextToken()); // col
        K = Integer.parseInt(st.nextToken()); // time

        map = new Turret[N][M];
        turretTreeSet = new TreeSet<>();
        laserAttackList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int attack = Integer.parseInt(st.nextToken());
                map[i][j] = new Turret(i, j, attack, 0);
                if (attack > 0) {
                    turretTreeSet.add(new Turret(i, j, attack, 0));
                }
            }
        }
        attacker = turretTreeSet.first();
        target = turretTreeSet.last();
    }

    private static void simulate() {
        for (int i = 1; i <= K; i++) { // K번의 턴을 시뮬레이션합니다.
            // 공격자 및 타겟 선정
            if (turretTreeSet.isEmpty())
                break; // 모든 터렛이 제거되었다면 종료

            attacker = turretTreeSet.first(); // 가장 약한 포탑을 공격자로 선정
            target = turretTreeSet.last(); // 가장 강한 포탑을 타겟으로 선정

            // 공격자의 공격력 업데이트
            attacker.attack += (N + M); // 공격자에게 N+M 만큼의 공격력 부여

            // 레이저 경로 설정 및 공격 시뮬레이션
            List<Location> route = selectLaserAttackRoute(); // 레이저 공격 경로 선택
            if (route != null) {
                laserAttack(route, attacker.attack); // 레이저 공격 수행
            } else {
                cannonAttack(attacker.attack); // 포탄 공격 수행
            }

            // 공격 후 처리: 공격력 업데이트 및 isAttacked 플래그 초기화
            maintenance();

            // 한 포탑만 남았다면 종료
            if (turretTreeSet.size() == 1) {
                System.out.println(turretTreeSet.first().attack);
                return;
            }
        }
        if (!turretTreeSet.isEmpty()) {
            System.out.println(turretTreeSet.last().attack); // 마지막 턴 후 가장 강한 포탑의 공격력 출력
        } else {
            System.out.println("All turrets destroyed."); // 모든 터렛이 파괴된 경우 메시지 출력
        }
    }

    private static void laserAttack(List<Location> route, int attack) {
        map[attacker.x][attacker.y].isAttacked = true;
        System.out.println(route);
        for (Location location : route) {
            if (map[location.x][location.y].attack <= 0)
                continue;
            if (target.x == location.x && target.y == location.y)
                continue;
            map[location.x][location.y].attack -= attack / 2;
            map[location.x][location.y].isAttacked = true;
        }
        map[target.x][target.y].attack -= attack;
        map[target.x][target.y].isAttacked = true;
    }

    private static void cannonAttack(int attack) {
        // 추가적으로 주위 8개의 방향에 있는 포탑도 피해를 입힘
        attacker.isAttacked = true;

        int[] adx = new int[] { 0, -1, -1, -1, 0, +1, +1, +1 };
        int[] ady = new int[] { -1, -1, 0, +1, +1, +1, 0, -1 };

        for (int i = 0; i < 8; i++) {
            int nx = (target.x + adx[i]) % N < 0 ? N + (target.x + adx[i]) : (target.x + adx[i]) % N;
            int ny = (target.y + ady[i]) % M < 0 ? M + (target.y + ady[i]) : (target.y + ady[i]) % M;
            if (nx == attacker.x && ny == attacker.y)
                continue;
            if (map[nx][ny].attack <= 0)
                continue;
            map[nx][ny].attack -= attack / 2;
            map[nx][ny].isAttacked = true;
        }

        target.attack -= attack;
        target.isAttacked = true;
    }

    private static List<Location> selectLaserAttackRoute() {
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][M];

        queue.add(new Location(attacker.x, attacker.y));
        visited[attacker.x][attacker.y] = true;

        while (!queue.isEmpty()) {
            Location cur = queue.poll();

            if (cur.equals(new Location(target.x, target.y))) {
                // 경로 리턴
                return cur.route;
            }
            for (int i = 0; i < 4; i++) {
                int nx = (cur.x + dx[i]) % N < 0 ? N + (cur.x + dx[i]) : (cur.x + dx[i]) % N;
                int ny = (cur.y + dy[i]) % M < 0 ? M + (cur.y + dy[i]) : (cur.y + dy[i]) % M;
                if (visited[nx][ny] || map[nx][ny].attack <= 0) {
                    continue;
                }
                Location next = new Location(nx, ny);

                next.route = new ArrayList<>(cur.route); // 현재 경로를 복사
                next.route.add(next); // 다음 위치를 경로에 추가
                visited[nx][ny] = true;
                queue.add(next);
            }
        }
        return null;
    }

    static void maintenance() {
        // map 순회하면서 isAttacked 랑 attack > 0 인 포탑 attack + 1;
        // treeset 초기화
        turretTreeSet = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (!map[i][j].isAttacked && map[i][j].attack > 0) {
                    map[i][j].attack += 1;
                }
                if (map[i][j].attack > 0) {
                    turretTreeSet.add(map[i][j]);
                }
                map[i][j].isAttacked = false;
            }
        }

    }
}

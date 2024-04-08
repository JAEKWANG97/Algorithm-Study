import java.util.*;
import java.io.*;

public class CT_메이즈러너 {
    static int N, M, K, map[][], sumDist, deltas[][] = {{1,0},{-1,0},{0,1},{0,-1}};
    static Pos exit;
    static Queue<Pos> runner;
    static StringBuilder sb = new StringBuilder();

    static class Pos {
        int r, c;
        Queue<Pos> history;
        public Pos(int r, int c) {
            this.r = r;
            this.c = c;
            history = new ArrayDeque();
        }

        public String toString() {
            return "r : " + r + ", c : " + c;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        sumDist = 0;
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        runner = new ArrayDeque();
        int r, c;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken())-1;
            c = Integer.parseInt(st.nextToken())-1;
            map[r][c] = -1;
            runner.offer(new Pos(r,c));
        }

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken())-1;
        c = Integer.parseInt(st.nextToken())-1;
        exit = new Pos(r, c);

        solve();

        System.out.println(sb.toString());
    }

    static void solve() {
        for (int k = 0; k < K; k++) {
            moveRunner();
            if (runner.isEmpty()) break;
            rotateMaze();
        }
        sb.append(sumDist + "\n" + (exit.r + 1) + " " + (exit.c + 1));
    }

    static void rotateMaze() {
        Pos min = null;
        int minX = N, minY = N, maxX = 0, maxY = 0; // 가장 작은 정사각형을 찾기 위한 변수
        while (!runner.isEmpty()) {
            Pos p = runner.poll();
            minX = Math.min(minX, p.r);
            minY = Math.min(minY, p.c);
            maxX = Math.max(maxX, p.r);
            maxY = Math.max(maxY, p.c);
            map[p.r][p.c]--;
        }

        // 출구의 위치도 고려
        minX = Math.min(minX, exit.r);
        minY = Math.min(minY, exit.c);
        maxX = Math.max(maxX, exit.r);
        maxY = Math.max(maxY, exit.c);
        map[exit.r][exit.c] = -100;

        // 회전할 정사각형의 크기
        int size = Math.max(maxX - minX + 1, maxY - minY + 1);

        // 회전하기 전 상태를 저장할 임시 배열
        int[][] temp = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (minX + i < N && minY + j < N) {
                    temp[i][j] = map[minX + i][minY + j];
                }
            }
        }

        // 실제 회전 및 내구도 감소 로직
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (minX + j < N && minY + size - 1 - i < N) {
                    map[minX + j][minY + size - 1 - i] = temp[i][j];
                    // 벽의 내구도 감소
                    if (map[minX + j][minY + size - 1 - i] > 0) {
                        map[minX + j][minY + size - 1 - i] -= 1;
                        // // 내구도가 0이 되면 빈 칸으로 변경
                        // if (map[minX + j][minY + size - 1 - i] == 0) {
                        //     map[minX + j][minY + size - 1 - i] = -1;
                        // }
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == -100) {
                    exit = new Pos(i, j);
                    continue;
                }
                if (map[i][j] < 0) {
                    for (;map[i][j] < 0;map[i][j]++) {
                        runner.offer(new Pos(i, j));
                    }
                }
            }
        }
    }

    static void moveRunner() {
        int size = runner.size(); // 현재 참가자 수만큼만 진행
        List<Pos> moved = new ArrayList<>(); // 이동한 위치
        for (int s = 0; s < size; s++) {
            Pos run = runner.poll();
            if (run.r == exit.r && run.c == exit.c) { // 탈출 가능하면 바로 탈출
                sumDist++;
                continue;
            }

            Pos nextStep = findNextStep(run); // 다음 이동 위치 찾기
            if (nextStep != null) {
                moved.add(nextStep);
                sumDist++;
            } else {
                moved.add(run); // 움직일 수 없는 경우
            }
        }
        runner.addAll(moved);
    }

    static Pos findNextStep(Pos current) {
        int minDist = calcDist(current, exit); // 현재 위치에서 출구까지의 거리 계산
        Pos nextStep = null;

        for (int[] delta : deltas) {
            int nr = current.r + delta[0];
            int nc = current.c + delta[1];
            if (nr < 0 || nr >= N || nc < 0 || nc >= N || map[nr][nc] > 0) continue;

            int dist = calcDist(new Pos(nr, nc), exit); // 상하좌우 중 더 가까운 위치 찾기
            if (dist < minDist) {
                minDist = dist;
                nextStep = new Pos(nr, nc);
            } else if (dist == minDist) {
                if (nextStep == null || delta[0] == -1 || (delta[0] == 0 && delta[1] == -1)) { // 가로 우선, 가로 동일하면 세로 우선
                    nextStep = new Pos(nr, nc);
                }
            }
        }

        return nextStep;
    }

    static void printArr(int map[][]) {
        for (int i = 0; i < map.length; i++) System.out.println(Arrays.toString(map[i]));
        System.out.println();
    }

    static int calcDist(Pos o1, Pos o2) {
        return Math.abs(o1.r - o2.r) + Math.abs(o1.c - o2.c);
    }
}
package codeTree;
 
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class CT_2 {
    static class Location implements Comparable<Location> {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 1. 행 번호 + 열 번호가 큰 칸
        // 2. 행 번호가 큰 칸, 열 번호가 큰 칸
        @Override
        public int compareTo(Location o) {
            int value = (o.x + o.y) - (this.x + this.y);
            if (value == 0) {
                value = o.x - this.x;
                if (value == 0) {
                    value = o.y - this.y;
                }
            }
            return value;
        }
    }

    static class Rabbit {
        int no;
        int x;
        int y;
        int dist;
        int count;
        int score;

        public Rabbit(int no, int dist, int count, int score, int x, int y) {
            this.no = no;
            this.x = x;
            this.y = y;
            this.dist = dist;
            this.count = count;
            this.score = score;
        }

        @Override
        public String toString() {
            return "Rabbit{" +
                    "no=" + no +
                    ", x=" + x +
                    ", y=" + y +
                    ", dist=" + dist +
                    ", count=" + count +
                    ", score=" + score +
                    '}';
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    ;
    static StringTokenizer st;
    static int Q, N, M, P;
    static int[][] map;
    static List<Rabbit> rabbits;
    static Map<Integer, Rabbit> rabbitMap;


    public static void main(String[] args) throws IOException {
        Q = Integer.parseInt(br.readLine());
        for (int i = 0; i < Q; i++) {
            simulate();
        }
    }

    private static void simulate() throws IOException {
        st = new StringTokenizer(br.readLine());

        int command = Integer.parseInt(st.nextToken());

        if (command == 100) {
            init();
        } else if (command == 200) {
            race();
        } else if (command == 300) {
            changeDist();
        } else if (command == 400) {
            selectWinner();
        }

    }

    private static void init() {
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        rabbits = new ArrayList<>();
        rabbitMap = new HashMap<>();
        // 경주 진행 자료구조 1
        // 마지막 점수 낼수 있는 자료구조 1
        // 이동할 자료구조 1

        for (int i = 0; i < P; i++) {
            // 토끼들 입장
            int no = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            Rabbit rabbit = new Rabbit(no, dist, 0, 0, 1, 1);
            rabbits.add(rabbit);
            rabbitMap.put(no, rabbit);
        }
    }

    private static void race() {
        int k = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        for (int j = 0; j < k; j++) {

            sortRaceRabbits();

            Rabbit racer = rabbits.get(0);
            rabbits.get(0).count += 1;

            int[] dx = new int[]{0, 1, 0, -1};
            int[] dy = new int[]{-1, 0, 1, 0};

            int dist = racer.dist;
            // 레이서 전용 이동경로 우선순위 큐 생성
            PriorityQueue<Location> nextLocation = new PriorityQueue<>();
            for (int i = 0; i < 4; i++) {
                int nx = racer.x;
                int ny = racer.y;

                for (int l = 0; l < dist; l++) {
                    nx += dx[i];
                    ny += dy[i];
                    if (nx > N) {
                        nx = N - 1;
                    }
                    if (ny > M) {
                        ny = M - 1;
                    }
                }

                nextLocation.add(new Location(nx, ny));
            }

            Location next = nextLocation.poll();

            racer.x = next.x;
            racer.y = next.y;
            int score = next.x + next.y;

            for (int i = 1; i < rabbits.size(); i++) {
                rabbits.get(i).score += score;
            }
        }
        sortScoreRabbits();
        for (Rabbit rabbit : rabbits) {
            if (rabbit.count > 0) {
                rabbit.score += s;
                break;
            }
        }
    }

    private static void sortRaceRabbits() {
        Collections.sort(rabbits, (o1, o2) -> {
            int value = o1.count - o2.count;
            if (value == 0) {
                value = (o1.x + o1.y) - (o2.x + o2.y);
                if (value == 0) {
                    value = o1.x - o2.x;
                    if (value == 0) {
                        value = o1.y - o2.y;
                        if (value == 0) {
                            value = o1.no - o2.no;
                        }
                    }
                }
            }
            return value;
        });
    }

    private static void sortScoreRabbits() {
        // 1. 현재 서있는 행 번호 + 열 번호가 큰 토끼
        // 2. 행 번호가 큰 토끼
        // 3. 열 번호가 큰 토끼
        // 4. 고유번호가 큰 토끼
        // K번의 턴 동안 한번이라도 뽑혔던 적이 있던 토끼 중 가장 우선순위가 높은 토끼를 골라야만 함
        Collections.sort(rabbits, (o1, o2) -> {
            int value = (o2.x + o2.y) - (o1.x + o1.y);
            if (value == 0) {
                value = o2.x - o1.x;
                if (value == 0) {
                    value = o2.y - o1.y;
                    if (value == 0) {
                        value = o2.no - o1.no;
                    }
                }
            }
            return value;
        });
    }

    private static void changeDist() {
        int no = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());
        rabbitMap.get(no).dist *= l;
    }

    private static void selectWinner() {
        Collections.sort(rabbits, (o1, o2) -> o2.score - o1.score);
        System.out.println(rabbits.get(0).score);
    }
}


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Fireball {
    int r, c, m, s, d;

    public Fireball(int r, int c, int m, int s, int d) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.s = s;
        this.d = d;
    }
}

class Simulation {
    private int N, M, K;
    private int[][] directions = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private List<Fireball>[][] map;

    public Simulation(int N, int M, int K) {
        this.N = N;
        this.M = M;
        this.K = K;
        map = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                map[i][j] = new ArrayList<>();
            }
        }
    }

    public void addFireball(int r, int c, int m, int s, int d) {
        map[r][c].add(new Fireball(r, c, m, s, d));
    }

    public void simulate() {
        for (int i = 0; i < K; i++) {
            moveFireballs();
            combineFireballs();
        }
    }

    private void moveFireballs() {
        List<Fireball>[][] newMap = new ArrayList[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                newMap[i][j] = new ArrayList<>();
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Fireball fireball : map[i][j]) {
                    int nr = (fireball.r + directions[fireball.d][0] * fireball.s % N + N) % N;
                    int nc = (fireball.c + directions[fireball.d][1] * fireball.s % N + N) % N;
                    if (nr == 0) nr = N;
                    if (nc == 0) nc = N;
                    newMap[nr][nc].add(new Fireball(nr, nc, fireball.m, fireball.s, fireball.d));
                }
            }
        }

        map = newMap;
    }

    private void combineFireballs() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j].size() > 1) {
                    int totalMass = 0, totalSpeed = 0, even = 0, odd = 0;
                    for (Fireball fireball : map[i][j]) {
                        totalMass += fireball.m;
                        totalSpeed += fireball.s;
                        if (fireball.d % 2 == 0) even++;
                        else odd++;
                    }
                    int mass = totalMass / 5;
                    int speed = totalSpeed / map[i][j].size();
                    map[i][j].clear();
                    if (mass > 0) {
                        for (int d = 0; d < 8; d += 2) {
                            if (even == 0 || odd == 0) map[i][j].add(new Fireball(i, j, mass, speed, d));
                            else map[i][j].add(new Fireball(i, j, mass, speed, d + 1));
                        }
                    }
                }
            }
        }
    }

    public int getTotalMass() {
        int totalMass = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (Fireball fireball : map[i][j]) {
                    totalMass += fireball.m;
                }
            }
        }
        return totalMass;
    }
}

public class BOJ_20056 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        int M = scanner.nextInt();
        int K = scanner.nextInt();
        
        Simulation simulation = new Simulation(N, M, K);
        
        for (int i = 0; i < M; i++) {
            int r = scanner.nextInt();
            int c = scanner.nextInt();
            int m = scanner.nextInt();
            int s = scanner.nextInt();
            int d = scanner.nextInt();
            simulation.addFireball(r, c, m, s, d);
        }
        
        simulation.simulate();
        
        System.out.println(simulation.getTotalMass());

        scanner.close();
    }
}

import java.util.*;

class Solution {
    static int N, K, L;
    static int[][] map;
    static int[][] keys;

    public boolean solution(int[][] key, int[][] lock) {
        keys = key;
        L = lock.length;
        K = key.length;
        N = lock.length + key.length * 2 - 2;

        map = new int[N][N];
        for (int i = K - 1; i < K - 1 + L; i++) {
            for (int j = K - 1; j < K - 1 + L; j++) {
                map[i][j] = lock[i - (K - 1)][j - (K - 1)];
            }
        }

        for (int i = 0; i < 4; i++) {
            if (check()) {
                return true;
            }
            rotate();
        }
        return false;
    }

    public void rotate() {
        int[][] copy = new int[K][K];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                copy[i][j] = keys[i][j];
            }
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                keys[i][j] = copy[K - j - 1][i];
            }
        }
    }

    public boolean check() {
        for (int i = 0; i <= N - K; i++) {
            for (int j = 0; j <= N - K; j++) {
                if (match(i, j)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean match(int x, int y) {
        int[][] copy = new int[N][N];
        for (int i = 0; i < N; i++) {
            copy[i] = map[i].clone();
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < K; j++) {
                copy[i + x][j + y] += keys[i][j];
            }
        }

        for (int i = K - 1; i < K - 1 + L; i++) {
            for (int j = K - 1; j < K - 1 + L; j++) {
                if (copy[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }
}

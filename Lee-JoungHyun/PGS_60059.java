import java.util.*;

public class Solution {
    public static boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;

        int[][] bigLock = new int[N + (M - 1) * 2][N + (M - 1) * 2];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                bigLock[i + M - 1][j + M - 1] = lock[i][j];
            }
        }

        for (int rotate = 0; rotate < 4; rotate++) {
            key = rotateKey(key);
            for (int x = 0; x < N + M - 1; x++) {
                for (int y = 0; y < N + M - 1; y++) {
                    if (check(key, bigLock, x, y, M, N)) return true;
                }
            }
        }

        return false;
    }

    private static int[][] rotateKey(int[][] key) {
        int N = key.length;
        int[][] rotatedKey = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                rotatedKey[j][N - 1 - i] = key[i][j];
            }
        }
        return rotatedKey;
    }

    private static boolean check(int[][] key, int[][] bigLock, int x, int y, int M, int N) {
        int[][] tempLock = new int[bigLock.length][bigLock.length];
        for (int i = 0; i < bigLock.length; i++) {
            System.arraycopy(bigLock[i], 0, tempLock[i], 0, bigLock.length);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                tempLock[x + i][y + j] += key[i][j];
            }
        }

        for (int i = M - 1; i < N + M - 1; i++) {
            for (int j = M - 1; j < N + M - 1; j++) {
                if (tempLock[i][j] != 1) return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[][] key = {{0, 0, 0}, {1, 0, 0}, {0, 1, 1}};
        int[][] lock = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        boolean result = solution(key, lock);
        System.out.println(result);
    }
}

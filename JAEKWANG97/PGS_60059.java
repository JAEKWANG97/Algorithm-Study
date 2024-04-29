public class Solution {
    public boolean solution(int[][] key, int[][] lock) {
        int N = lock.length;
        int M = key.length;

        int[][] extendedLock = new int[N + 2 * M][N + 2 * M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                extendedLock[i + M][j + M] = lock[i][j];
            }
        }

        for (int rotation = 0; rotation < 4; rotation++) {
            key = rotate(key);
            for (int x = 0; x < N + M; x++) {
                for (int y = 0; y < N + M; y++) {
                    if (tryUnlock(extendedLock, key, x, y, M, N)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean tryUnlock(int[][] lock, int[][] key, int startX, int startY, int M, int N) {
        int[][] tempLock = new int[lock.length][lock.length];

        // 확장된 자물쇠 복사
        for (int i = 0; i < lock.length; i++) {
            System.arraycopy(lock[i], 0, tempLock[i], 0, lock.length);
        }

        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                tempLock[startX + i][startY + j] ^= key[i][j];
            }
        }

        for (int i = M; i < M + N; i++) {
            for (int j = M; j < M + N; j++) {
                if (tempLock[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] rotate(int[][] key) {
        int M = key.length;
        int[][] rotated = new int[M][M];
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < M; j++) {
                rotated[j][M - 1 - i] = key[i][j];
            }
        }
        return rotated;
    }
}

class PGS_60059 {
    static int n, m;

    static boolean matchingKeyToLock(int[][] key, int[][] lock) {
        for (int i = 0; i < m + n - 1; i++) {
            for (int j = 0; j < m + n - 1; j++) {
                boolean flag = true;
                out:
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {
                        if (lock[y][x] == key[i + y][j + x]) {
                            flag = false;
                            break out;
                        }
                    }
                }
                if (flag) {
                    return true;
                }
            }
        }
        return false;
    }

    static int[][] extendKeyToLock(int[][] src) {
        int[][] extendKey = new int[m + 2 * (n - 1)][m + 2 * (n - 1)];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                extendKey[i + n - 1][j + n - 1] = src[i][j];
            }
        }
        return extendKey;
    }

    static int[][] turnKey(int[][] src) {
        int[][] trunKey = new int[m][m];
        int y = m - 1;
        for (int i = 0; i < m; i++) { // y
            for (int j = 0; j < m; j++) { // x
                trunKey[j][y] = src[i][j];
            }
            y--;
        }
        return trunKey;
    }

    static int[][] copyKey(int[][] src) {
        int[][] copy = new int[m][m];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                copy[i][j] = src[i][j];
            }
        }
        return copy;
    }

    public boolean solution(int[][] key, int[][] lock) {
        n = lock.length;
        m = key.length;
        int t = 0;
        int[][] temp = copyKey(key);
        while (t < 4) {
            int[][] exKey = extendKeyToLock(temp);
            boolean result = matchingKeyToLock(exKey, lock);
            if (result) {
                return true;
            }
            temp = turnKey(temp);
            t++;
        }
        return false;
    }
}
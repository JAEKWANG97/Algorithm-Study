class PGS_12978 {
    private static final int INF = 100_000_000;

    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[][] map = new int[N][N];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (i == j) {
                    continue;
                }
                map[i][j] = INF;
            }
        }

        for (int[] row : road) {
            if (map[row[0] - 1][row[1] - 1] < row[2]) {
                continue;
            }
            map[row[0] - 1][row[1] - 1] = row[2];
            map[row[1] - 1][row[0] - 1] = row[2];
        }

        for (int k = 0; k < map.length; k++) {
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for (int val : map[0]) {
            if (val <= K) {
                answer++;
            }
        }

        return answer;
    }
}

import java.util.*;

class Solution {
    static class Location {
        int x;
        int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int solution(int[][] land) {
        int answer = 0;
        int n = land.length;
        int m = land[0].length;
        boolean[][] visited = new boolean[n][m];
        int[] totalVolume = new int[m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (land[i][j] == 1 && !visited[i][j]) {
                    bfs(i, j, visited, totalVolume, n, m, land);
                }
            }
        }
        answer = Arrays.stream(totalVolume).max().getAsInt();

        return answer;
    }

    private static void bfs(int i, int j, boolean[][] visited, int[] totalVolume, int n, int m, int[][] land) {
        Queue<Location> que = new ArrayDeque<>();
        que.add(new Location(i, j));
        visited[i][j] = true;
        Set<Integer> adjCol = new HashSet<>();
        int volume = 0;
        // 1이면 석유가 있는 땅을 의미
        while (!que.isEmpty()) {
            Location cur = que.poll();
            adjCol.add(cur.y);
            volume++;
            int[] dx = { 0, 1, 0, -1 };
            int[] dy = { -1, 0, 1, 0 };

            for (int d = 0; d < 4; d++) {
                int nx = cur.x + dx[d];
                int ny = cur.y + dy[d];

                if (nx >= n || nx < 0 || ny >= m || ny < 0 || visited[nx][ny] || land[nx][ny]==0)
                    continue;

                que.add(new Location(nx, ny));
                visited[nx][ny] = true;
            }
        }

        for (int col : adjCol) {
            totalVolume[col] += volume;
        }
    }
}

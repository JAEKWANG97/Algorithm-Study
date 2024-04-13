import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_21609 {

    private static class Poz {
        int y, x, color;

        public Poz(int y, int x, int color) {
            this.y = y;
            this.x = x;
            this.color = color;
        }
    }

    private static class Group implements Comparable<Group>{
        ArrayList<Poz> members;
        int rCnt;
        int maxY;
        int maxX;

        public Group() {
            members = new ArrayList<>();
            rCnt = 0;
            maxY = Integer.MAX_VALUE;
            maxX = Integer.MAX_VALUE;
        }

        public void add(Poz nxt) {
            members.add(nxt);
            if (nxt.color == 0)
                rCnt++;
            else {
                if (maxY > nxt.y || (maxY == nxt.y && maxX > nxt.x)) {
                    maxY = nxt.y;
                    maxX = nxt.x;
                }
            }
        }

        @Override
        public int compareTo(Group o) {
            if (this.members.size() != o.members.size())
                return o.members.size() - this.members.size();
            if (rCnt != o.rCnt)
                return o.rCnt - this.rCnt;
            if (maxY != o.maxY)
                return o.maxY - this.maxY;
            return o.maxX - this.maxX;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];

        for (int y = 0; y < N; y ++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }

        long answer = 0;
        while (true) {
            // 그룹 찾아 지우기
            Group group = findGroup(map);
            if (group == null) break;
            answer += Math.pow(group.members.size(), 2);
            for (Poz del : group.members) {
                map[del.y][del.x] = -3;
            }
            // 떨구기
            drop(map);
            // 돌리기
            turn(map);
            // 떨구기
            drop(map);
        }
        System.out.println(answer);

    }

    private static Group findGroup(int[][] map) {
        int N = map.length;
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Group> groups = new PriorityQueue<>();

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                if (!visited[y][x] && map[y][x] != -3 && map[y][x] != -1 && map[y][x] != 0) {
                    groups.add(BFS(y, x, visited, map));
                }
            }
        }
        while (!groups.isEmpty()) {
            Group group = groups.poll();

            if (group.members.size() - group.rCnt > 0 && group.members.size() >= 2) {
                return group;
            }
        }
        return null;
    }

    private static Group BFS(int y, int x, boolean[][] visited, int[][] map) {
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};
        int N = map.length;

        Group group = new Group();
        Queue<Poz> queue = new LinkedList<>();
        List<Poz> m = new LinkedList<>();
        visited[y][x] = true;
        Poz start = new Poz(y, x, map[y][x]);
        group.add(start);
        queue.add(start);
        int color = map[y][x];

        while (!queue.isEmpty()) {
            Poz nxt = queue.poll();
            for (int d = 0; d < 4; d++) {
                int ny = nxt.y + dy[d];
                int nx = nxt.x + dx[d];
                if (0 <= ny && ny < N && 0 <= nx && nx < N && !visited[ny][nx] && (map[ny][nx] == 0 || map[ny][nx] == color)) {
                    if (map[ny][nx] == 0)
                        m.add(new Poz(ny, nx, 0));
                    visited[ny][nx] = true;
                    group.add(new Poz(ny, nx, map[ny][nx]));
                    queue.add(new Poz(ny, nx, map[ny][nx]));
                }
            }
        }
        for (Poz mu : m) {
            visited[mu.y][mu.x] = false;
        }

        return group;

    }

    private static void drop(int[][] map) {
        int N = map.length;
        for (int x = 0; x < N; x++) {
            int bottomIdx = N;
            for (int y = N - 1; y > -1; y--) {
                if (map[y][x] == -3) {
                    if (bottomIdx == N)
                        bottomIdx = y;
                }
                else if (map[y][x] == -1) {
                    bottomIdx = y - 1;
                }
                else if (bottomIdx != N){
                    if (bottomIdx == y) bottomIdx--;
                    else {
                        map[bottomIdx--][x] = map[y][x];
                        map[y][x] = -3;
                    }
                }
            }
        }
    }

    private static void turn(int[][] map) {
        int N = map.length;
        int[][] next = new int[N][N];
        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                next[N - 1 - x][y] = map[y][x];
            }
        }
        //map = next;
        for (int y = 0; y < N; y++)
            map[y] = next[y];
    }

}
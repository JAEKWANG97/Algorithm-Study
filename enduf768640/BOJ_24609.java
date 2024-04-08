import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Stream;

public class BOJ_24609 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N, M;
    private static int[][] map;


    private static int[] dy = {-1, 0, 1, 0};
    private static int[] dx = {0, 1, 0, -1};

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        while (true) {
            try {
                removeGroup(findBiggestGroup());
            } catch (NullPointerException e) {
                break;
            }

            dropBlocks();
            rotateMap();
            dropBlocks();
        }

        System.out.println(answer);
    }

    private static Group findBiggestGroup() {
        PriorityQueue<Group> blockGroups = new PriorityQueue<>((g1, g2) -> {
            if (g1.size() != g2.size()) {
                return g2.size() - g1.size();
            }

            if (g1.getRainbowCount() != g2.getRainbowCount()) {
                return g2.getRainbowCount() - g1.getRainbowCount();
            }

            if (g1.getStandardY() != g2.getStandardY()) {
                return g2.getStandardY() - g1.getStandardY();
            }

            return g2.getStandardX() - g1.getStandardX();
        });

        Queue<Location> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[M + 1][N][N];

        for (int color = 1; color <= M; color++) {
            for (int y = 0; y < N; y++) {
                for (int x = 0; x < N; x++) {
                    if (visited[color][y][x] || map[y][x] != color) {
                        continue;
                    }

                    queue.offer(new Location(y, x));
                    visited[color][y][x] = true;

                    Group group = new Group(new Location(y, x), color);

                    while (!queue.isEmpty()) {
                        Location location = queue.poll();

                        for (int i = 0; i < 4; i++) {
                            int Y = location.getY() + dy[i];
                            int X = location.getX() + dx[i];

                            if (Y < 0 || Y >= N || X < 0 || X >= N || visited[color][Y][X] || map[Y][X] == Integer.MAX_VALUE) {
                                continue;
                            }

                            if (map[Y][X] == color) {
                                queue.offer(new Location(Y, X));
                                group.add(new Location(Y, X));
                                visited[color][Y][X] = true;
                            }

                            if (map[Y][X] == 0) {
                                queue.offer(new Location(Y, X));
                                group.add(new Location(Y, X));
                                group.addRainbowCount();
                                visited[color][Y][X] = true;
                            }
                        }
                    }

                    if (group.size() == 1) {
                        continue;
                    }

                    blockGroups.add(group);
                }
            }
        }

        return blockGroups.peek();
    }

    private static void removeGroup(Group group) {
        group.stream().forEach(block -> map[block.getY()][block.getX()] = Integer.MAX_VALUE);
        answer += group.size() * group.size();
    }

    private static void dropBlocks() {
        for (int x = 0; x < N; x++) {
            for (int y = N - 1; y >= 0; y--) {
                int block = map[y][x];

                if (block == Integer.MAX_VALUE || block == -1) {
                    continue;
                }

                int Y = y;

                while (true) {
                    if (Y >= N - 1) {
                        break;
                    }

                    if (map[Y + 1][x] != Integer.MAX_VALUE) {
                        break;
                    }

                    Y++;
                }

                map[y][x] = Integer.MAX_VALUE;
                map[Y][x] = block;
            }
        }
    }

    private static void rotateMap() {
        int[][] newMap = new int[N][N];

        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                newMap[(N - 1) - x][y] = map[y][x];
            }
        }

        map = newMap;
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int y = 0; y < N; y++) {
            st = new StringTokenizer(br.readLine());
            for (int x = 0; x < N; x++) {
                map[y][x] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static class Group {

        private Location standard;

        private int color;
        private Set<Location> blocks;

        private int rainbowCount;

        public Group(Location standard, int color) {
            this.standard = standard;
            this.color = color;

            this.blocks = new HashSet<>();
            this.blocks.add(standard);
        }

        public void add(Location block) {
            blocks.add(block);
        }

        public int size() {
            return blocks.size();
        }

        public void addRainbowCount() {
            rainbowCount++;
        }

        public int getStandardY() {
            return standard.getY();
        }

        public int getStandardX() {
            return standard.getX();
        }

        public int getRainbowCount() {
            return rainbowCount;
        }

        public Stream<Location> stream() {
            return blocks.stream();
        }
    }

    private static class Location {

        private int y;
        private int x;

        public Location(int y, int x) {
            this.y = y;
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public int getX() {
            return x;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return y == location.y && x == location.x;
        }

        @Override
        public int hashCode() {
            return Objects.hash(y, x);
        }
    }
}

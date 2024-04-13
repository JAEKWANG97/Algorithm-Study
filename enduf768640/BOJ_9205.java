import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_9205 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int T;
    private static int N;

    private static Location house;
    private static Location festival;
    private static List<Location> locations;

    private static boolean[] visited;

    public static void main(String[] args) throws IOException {
        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            init();
            bfs();
            printAnswer();
        }
    }

    private static void bfs() {
        Queue<Location> queue = new LinkedList<>();
        queue.offer(house);

        while (!queue.isEmpty()) {
            Location previous = queue.poll();

            for (int i = 0; i < locations.size(); i++) {
                Location location = locations.get(i);

                if (getDistance(previous, location) > 1000 || visited[i]) {
                    continue;
                }

                queue.offer(location);
                visited[i] = true;
            }
        }
    }

    private static int getDistance(Location l1, Location l2) {
        return Math.abs(l1.getY() - l2.getY()) + Math.abs(l1.getX() - l2.getX());
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        house = new Location(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );

        locations = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            locations.add(
                    new Location(
                            Integer.parseInt(st.nextToken()),
                            Integer.parseInt(st.nextToken())
                    )
            );
        }

        st = new StringTokenizer(br.readLine());
        festival = new Location(
                Integer.parseInt(st.nextToken()),
                Integer.parseInt(st.nextToken())
        );
        locations.add(festival);

        visited = new boolean[N + 1];
    }

    private static void printAnswer() {
        if (visited[N]) {
            System.out.println("happy");
        } else {
            System.out.println("sad");
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
    }
}

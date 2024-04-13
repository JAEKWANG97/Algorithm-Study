import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BOJ_8983 {

    private static int M, N, L;

    private static Location[] spots;
    private static Location[] animals;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        checkAnimals();

        printAnswer();
    }

    private static void checkAnimals() {
        for (int i = 0; i < N; i++) {
            binarySearch(animals[i]);
        }
    }

    private static void binarySearch(Location animal) {
        int start = 0;
        int end = M - 1;

        while (start <= end) {
            int mid = (start + end) / 2;

            int distance = getDistance(animal, spots[mid]);

            if (distance > L) {
                if (animal.getX() < spots[mid].getX()) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                answer++;
                return;
            }
        }
    }

    private static int getDistance(Location animal, Location spot) {
        return Math.abs(spot.getX() - animal.getX()) + animal.getY();
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        spots = new Location[M];
        for (int i = 0; i < M; i++) {
            spots[i] = new Location(Integer.parseInt(st.nextToken()), 0);
        }

        Arrays.sort(spots, Comparator.comparingInt(Location::getX));

        animals = new Location[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            animals[i] = new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Location {

        private int x;
        private int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }
    }
}

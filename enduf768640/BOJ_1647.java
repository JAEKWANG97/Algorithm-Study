import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ_1647 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int N;
    private static int M;

    private static House[] houses;

    private static PriorityQueue<Road> roads;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();
        makeMinimumSpanningTreeAndGetAnswer();
        printAnswer();
    }

    private static void makeMinimumSpanningTreeAndGetAnswer() {
        List<Integer> weights = new ArrayList<>();

        while (true) {
            Road road = roads.poll();

            House house1 = houses[road.getStart()];
            House house2 = houses[road.getEnd()];

            if (findSet(house1) == findSet(house2)) {
                continue;
            }

            union(house1, house2);
            weights.add(road.getWeight());

            if (weights.size() == N - 1) {
                break;
            }
        }

        answer = weights.stream()
                .sorted()
                .limit(weights.size() - 1)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static House findSet(House house) {
        if (house.getParent() == house) {
            return house;
        }

        House root = findSet(house.getParent());
        house.setParent(root);

        return root;
    }

    private static void union(House house1, House house2) {
        findSet(house2).setParent(findSet(house1));
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        makeSet();

        roads = new PriorityQueue<>(Comparator.comparingInt(Road::getWeight));
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            roads.offer(new Road(start, end, weight));
        }
    }

    private static void makeSet() {
        houses = new House[N + 1];

        for (int i = 1; i <= N; i++) {
            houses[i] = new House();
        }
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class House {

        private House parent;

        public House() {
            this.parent = this;
        }

        public House getParent() {
            return parent;
        }

        public void setParent(House parent) {
            this.parent = parent;
        }
    }

    private static class Road {

        private int start;
        private int end;
        private int weight;

        public Road(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public int getWeight() {
            return weight;
        }
    }
}

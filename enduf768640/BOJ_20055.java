import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class BOJ_20055 {

    private static int N, K;

    private static Belt belt;

    private static int answer;

    public static void main(String[] args) throws IOException {
        init();

        countStage();

        printAnswer();
    }

    private static void countStage() {
        while (belt.canUse(K)) {
            answer++;

            belt.rotate();
            belt.removeRobot();
            belt.moveRobots();
            belt.removeRobot();
            belt.addRobot(new Robot());
        }
    }

    private static void init() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        belt = new Belt(
                Arrays.stream(br.readLine().split("\\s"))
                        .mapToInt(Integer::parseInt)
                        .toArray()
        );
    }

    private static void printAnswer() {
        System.out.println(answer);
    }

    private static class Belt {

        private LinkedList<Square> belt;

        public Belt(int[] durabilities) {
            belt = Arrays.stream(durabilities)
                    .mapToObj(Square::new)
                    .collect(Collectors.toCollection(LinkedList::new));
        }

        public void rotate() {
            belt.offerFirst(belt.pollLast());
        }

        public void addRobot(Robot robot) {
            if (!belt.getFirst().canUse()) {
                return;
            }

            belt.getFirst().addRobot(robot);
        }

        public void removeRobot() {
            belt.get((belt.size() - 1) / 2).removeRobot();
        }

        public void moveRobots() {
            for (int num = belt.size() / 2; num >= 0; num--) {
                if (belt.get(num).isEmpty()) {
                    continue;
                }

                if (!belt.get(num + 1).isEmpty() || !belt.get(num + 1).canUse()) {
                    continue;
                }

                Robot robot = belt.get(num).removeRobot();
                belt.get(num + 1).addRobot(robot);
            }
        }

        public boolean canUse(int limit) {
            int count = 0;

            for (Square square : belt) {
                if (!square.canUse()) {
                    count++;
                }
            }

            return count < limit;
        }
    }

    private static class Square {

        private Robot robot;
        private int durability;

        public Square(int durability) {
            this.robot = null;
            this.durability = durability;
        }

        public void addRobot(Robot robot) {
            this.robot = robot;
            this.durability--;
        }

        public Robot removeRobot() {
            Robot remove = this.robot;
            this.robot = null;

            return remove;
        }

        public boolean isEmpty() {
            return this.robot == null;
        }

        public boolean canUse() {
            return this.durability != 0;
        }
    }

    private static class Robot {
    }
}

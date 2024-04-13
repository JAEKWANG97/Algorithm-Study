import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class BOJ_2251 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static StringTokenizer st;

    private static int maxA, maxB, maxC;
    private static List<Integer> answer;

    public static void main(String[] args) throws IOException {
        init();
        bfs();
        printAnswer();
    }

    private static void bfs() {
        Queue<State> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[maxA + 1][maxB + 1][maxC + 1];

        queue.add(new State(0, 0, maxC));// 초기 상태
        while (!queue.isEmpty()) {
            State current = queue.poll();
            if (visited[current.getA()][current.getB()][current.getC()])
                continue;

            if (current.getA() == 0) {
                answer.add(current.getC());
            }

            visited[current.getA()][current.getB()][current.getC()] = true;

            if (current.getA() + current.getB() <= maxA) {
                queue.add(new State(current.getA() + current.getB(), 0, current.getC()));
            } else {
                queue.add(new State(maxA, current.getA() + current.getB() - maxA, current.getC()));
            }

            if (current.getA() + current.getC() <= maxA) {
                queue.add(new State(current.getA() + current.getC(), current.getB(), 0));
            } else {
                queue.add(new State(maxA, current.getB(), current.getA() + current.getC() - maxA));
            }

            if (current.getA() + current.getB() <= maxB) {
                queue.add(new State(0, current.getA() + current.getB(), current.getC()));
            } else {
                queue.add(new State(current.getA() + current.getB() - maxB, maxB, current.getC()));
            }

            if (current.getB() + current.getC() <= maxB) {
                queue.add(new State(current.getA(), current.getB() + current.getC(), 0));
            } else {
                queue.add(new State(current.getA(), maxB, current.getB() + current.getC() - maxB));
            }

            if (current.getA() + current.getC() <= maxC) {
                queue.add(new State(0, current.getB(), current.getA() + current.getC()));
            } else {
                queue.add(new State(current.getA() + current.getC() - maxC, current.getB(), maxC));
            }

            if (current.getB() + current.getC() <= maxC) {
                queue.add(new State(current.getA(), 0, current.getB() + current.getC()));
            } else {
                queue.add(new State(current.getA(), current.getB() + current.getC() - maxC, maxC));
            }
        }
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        maxA = Integer.parseInt(st.nextToken());
        maxB = Integer.parseInt(st.nextToken());
        maxC = Integer.parseInt(st.nextToken());

        answer = new ArrayList<>();
    }

    private static void printAnswer() {
        String answerString = answer.stream()
                .sorted().map(String::valueOf)
                .collect(Collectors.joining(" "));

        System.out.println(answerString);
    }

    private static class State {

        private int a;
        private int b;
        private int c;

        public State(int a, int b, int c) {
            this.a = a;
            this.b = b;
            this.c = c;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }

        public int getC() {
            return c;
        }
    }
}

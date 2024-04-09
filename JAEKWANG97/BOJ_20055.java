import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); 
        int K = sc.nextInt(); 
        int[] durability = new int[2 * N]; 
        boolean[] robots = new boolean[N]; 
        for (int i = 0; i < 2 * N; i++) {
            durability[i] = sc.nextInt(); 
        }

        int step = 0;
        int zeroDurabilityCount = 0;

        while (zeroDurabilityCount < K) {
            step++; // 단계 증가

            // 1. 벨트와 로봇 회전
            rotateBeltAndRobots(N, durability, robots);

            // 2. 로봇 이동
            zeroDurabilityCount += moveRobots(N, durability, robots);

            // 3. 로봇 올리기
            if (durability[0] > 0) {
                robots[0] = true;
                durability[0]--;
                if (durability[0] == 0) {
                    zeroDurabilityCount++;
                }
            }
        }

        System.out.println(step);
    }

    private static void rotateBeltAndRobots(int N, int[] durability, boolean[] robots) {
        int lastDurability = durability[durability.length - 1];
        System.arraycopy(durability, 0, durability, 1, durability.length - 1);
        durability[0] = lastDurability;

        for (int i = N - 1; i > 0; i--) {
            robots[i] = robots[i - 1];
        }
        robots[0] = false;
        robots[N - 1] = false; // 내리는 위치에 도달한 로봇 내리기
    }

    private static int moveRobots(int N, int[] durability, boolean[] robots) {
        int zeroDurabilityCount = 0;
        for (int i = N - 2; i >= 0; i--) {
            if (robots[i] && !robots[i + 1] && durability[i + 1] > 0) {
                robots[i] = false;
                robots[i + 1] = true;
                durability[i + 1]--;
                if (durability[i + 1] == 0) {
                    zeroDurabilityCount++;
                }
            }
        }
        return zeroDurabilityCount;
    }
}

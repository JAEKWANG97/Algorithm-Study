import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        solve(n);
        sc.close();
    }

    private static void solve(int n) {
        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 2; i < 10; i++) {
            if (isPrimeNum(i)) {
                que.add(i);
            }

        }

        while (!que.isEmpty()) {
            int cur = que.poll();

            if (String.valueOf(cur).length() == n) {
                System.out.println(cur);
                continue;
            }

            for (int i = 0; i < 10; i++) {
                int next = cur * 10 + i;
                if (isPrimeNum(next)) {
                    que.add(next);
                }
            }
        }
    }

    private static boolean isPrimeNum(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

}

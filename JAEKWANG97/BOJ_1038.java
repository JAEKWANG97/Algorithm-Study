import java.util.Scanner;
import java.util.LinkedList;
import java.util.Collections;
import java.util.List;

public class BOJ_1038 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long N = sc.nextLong();
        sc.close();
        
        if (N <= 10) {
            System.out.println(N);
            return;
        }

        LinkedList<Long> queue = new LinkedList<>();
        for (int i = 1; i < 10; i++) {
            queue.add((long)i);
        }

        int count = 9; // 0부터 9까지 이미 10개의 감소하는 수가 있음
        long number = 0;

        while (!queue.isEmpty()) {
            number = queue.poll();
            long lastDigit = number % 10;

            for (int i = 0; i < lastDigit; i++) {
                long newNumber = number * 10 + i;
                queue.add(newNumber);
                count++;
                
                if (count == N) {
                    System.out.println(newNumber);
                    return;
                }
            }
        }

        // N번째 감소하는 수가 존재하지 않는 경우
        System.out.println(-1);
    }
}

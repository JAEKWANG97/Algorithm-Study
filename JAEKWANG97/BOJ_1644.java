package backjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BOJ_1644 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        List<Integer> primeNumberList = new ArrayList<>();
        primeNumberSieve(number, new int[number + 1], primeNumberList);

        int count = 0;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (true) {
            if (sum >= number) {
                sum -= primeNumberList.get(start++);
            } else if (end == primeNumberList.size()) {
                break;
            } else {
                sum += primeNumberList.get(end++);
            }
            if (sum == number) {
                count++;
            }
        }

        System.out.println(count);
    }

    static void primeNumberSieve(int number, int[] a, List<Integer> primeNumberList) {
        for (int i = 2; i <= number; i++) {
            a[i] = i;
        }

        for (int i = 2; i <= number; i++) {
            if (a[i] == 0) {
                continue;
            }
            for (int j = 2 * i; j <= number; j += i) {
                a[j] = 0;
            }
        }

        for (int i = 2; i <= number; i++) {
            if (a[i] != 0) {
                primeNumberList.add(a[i]);
            }
        }
    }
}

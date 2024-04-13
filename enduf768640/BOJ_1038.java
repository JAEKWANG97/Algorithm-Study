import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BOJ_1038 {

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    private static int N;

    private static List<Long> list;

    public static void main(String[] args) throws IOException {
        init();
        makeNumberList();
        printAnswer();
    }

    private static void makeNumberList() {
        list = new ArrayList<>();

        for (int i = 1; i <= 10; i++) {
            combination(new int[i], 9, 0, i);
        }

        list.sort(Long::compare);
    }

    private static void combination(int[] comb, int start, int count, int length) {
        if (count == length) {
            String num = Arrays.stream(comb)
                    .mapToObj(String::valueOf)
                    .collect(Collectors.joining());

            list.add(Long.parseLong(num));
            return;
        }

        for (int i = start; i >= 0; i--) {
            comb[count] = i;
            combination(comb, i - 1, count + 1, length);
        }
    }

    private static void init() throws IOException {
        N = Integer.parseInt(br.readLine());
    }

    private static void printAnswer() {
        if (N >= list.size()) {
            System.out.println(-1);
            return;
        }

        System.out.println(list.get(N));
    }
}

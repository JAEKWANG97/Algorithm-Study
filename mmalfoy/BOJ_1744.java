import java.io.*;
import java.util.*;

public class BOJ_1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> positives = new ArrayList<>();
        ArrayList<Integer> negatives = new ArrayList<>();
        int sum = 0, zeroCount = 0;

        for (int n = 0; n < N; n++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                positives.add(num);
            } else if (num == 1) {
                sum += 1;  // 1은 바로 더하기
            } else if (num == 0) {
                zeroCount++;
            } else {
                negatives.add(num);
            }
        }

        Collections.sort(positives, Collections.reverseOrder());
        Collections.sort(negatives);

        // 양수 처리
        for (int i = 0; i < positives.size() - 1; i += 2) {
            sum += positives.get(i) * positives.get(i + 1);
        }
        if (positives.size() % 2 != 0) {
            sum += positives.get(positives.size() - 1);
        }

        // 음수 처리
        for (int i = 0; i < negatives.size() - 1; i += 2) {
            sum += negatives.get(i) * negatives.get(i + 1);
        }
        if (negatives.size() % 2 != 0 && zeroCount == 0) {
            sum += negatives.get(negatives.size() - 1);  // 남은 음수가 있는 경우, 0이 없으면 더하기
        }

        System.out.println(sum);
    }
}

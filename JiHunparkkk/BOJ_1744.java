import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    private static class Wrap {
        int idx, num;

        public Wrap(int idx, int num) {
            this.idx = idx;
            this.num = num;
        }
    }

    private static int n, max, idx1, idx2;
    private static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        //2개를 골라서 곱했을 때 그 중 최대값 찾고,
        //곱하지 않았을 때 총합과 곱했을 때 총합을 비교
        Arrays.sort(arr);   //작은 값부터 처리
        boolean[] wrapped = new boolean[n];
        int nowSum = Arrays.stream(arr).sum();
        while (true) {
            max = Integer.MIN_VALUE;
            solution(new Wrap[2], 0, 0, wrapped);

            int newSum = nowSum - arr[idx1] - arr[idx2] + max;
            if (nowSum < newSum) {
                nowSum = newSum;
            }
            if (max == Integer.MIN_VALUE) {
                break;
            }
            wrapped[idx1] = true;
            wrapped[idx2] = true;
        }

        System.out.println(nowSum);
    }

    private static void solution(Wrap[] nums, int depth, int start, boolean[] wrapped) {
        if (depth == 2) {
            if (max < nums[0].num * nums[1].num) {
                idx1 = nums[0].idx;
                idx2 = nums[1].idx;
                max = nums[0].num * nums[1].num;
            }
            return;
        }

        for (int i = start; i < n; i++) {
            if (wrapped[i]) {
                continue;
            }
            nums[depth] = new Wrap(i, arr[i]);
            solution(nums, depth + 1, i + 1, wrapped);
        }
    }
}
/**
 * 도움되었던 반례
 * 7
 * 3
 * 2
 * 1
 * 1
 * 0
 * 0
 * -3
 */

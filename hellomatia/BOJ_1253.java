package hellomatia;

import java.io.*;
import java.util.*;

public class BOJ_1253 {

    private BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
    private BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    private int N;
    private int[] nums;

    public static void main(String[] args) throws IOException {
        new BOJ_1253().solution();
    }

    private void solution() throws IOException {
        init();
        printAns(calcAns());
    }

    private void init() throws IOException {
        N = Integer.parseInt(bf.readLine());

        nums = new int[N];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);
    }

    private int calcAns() {
        int ans = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            while (true) {

                if (i == left) left++;
                else if (right == i) right--;

                if (left >= right) break;

                if (nums[left] + nums[right] > nums[i]) right--;
                else if (nums[left] + nums[right] < nums[i]) left++;
                else {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    private void printAns(int ans) throws IOException {
        bw.write(ans + "\n");
        bw.flush();
    }
}

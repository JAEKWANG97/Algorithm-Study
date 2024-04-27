import java.util.*;

public class Main {
    public static int solution(int n, int[] weak, int[] dist) {
        int answer = Integer.MAX_VALUE;
        int len = weak.length;
        int[] weakPoints = new int[len * 2];
        for (int i = 0; i < len; i++) {
            weakPoints[i] = weak[i];
            weakPoints[i + len] = weak[i] + n;
        }

        for (int start = 0; start < len; start++) {
            Arrays.sort(dist);
            do {
                int cnt = 1;
                int position = weakPoints[start] + dist[dist.length - 1];
                for (int i = start; i < start + len; i++) {
                    if (position < weakPoints[i]) {
                        cnt++;
                        if (cnt > dist.length) break;
                        position = weakPoints[i] + dist[dist.length - cnt];
                    }
                }
                answer = Math.min(answer, cnt);
            } while (nextPermutation(dist));
        }

        return answer > dist.length ? -1 : answer;
    }

    private static boolean nextPermutation(int[] arr) {
        int i = arr.length - 1;
        while (i > 0 && arr[i - 1] >= arr[i]) {
            i--;
        }
        if (i <= 0) return false;

        int j = arr.length - 1;
        while (arr[j] <= arr[i - 1]) {
            j--;
        }

        int temp = arr[i - 1];
        arr[i - 1] = arr[j];
        arr[j] = temp;

        j = arr.length - 1;
        while (i < j) {
            temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 12;
        int[] weak = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};
        System.out.println(solution(n, weak, dist)); // Output: 2
    }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    private static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());

        int left = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            left = Math.max(left, arr[i]);
        }

        //블루레이 크기가 너무 크면, 한 번에 다 들어감
        //블루레이 크기가 너무 작으면, 갯수가 많아짐
        System.out.println(solution(arr, left));
    }

    private static int solution(int[] arr, int left) {
        int right = 1_000_000_000;
        while (left <= right) {
            int mid = (left + right) / 2;

            int cnt = calCnt(mid, arr);

            if (cnt <= M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static int calCnt(int limit, int[] arr) {
        int sum = 0;
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            if (sum + arr[i] > limit) {
                sum = 0;
                cnt++;
            }
            sum += arr[i];
        }
        if (sum != 0) {
            cnt++;
        }

        return cnt;
    }
}

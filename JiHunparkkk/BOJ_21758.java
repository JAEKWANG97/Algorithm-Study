import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {

    private static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        //총 3가지 케이스로 나눠
        //1. 벌이 왼쪽 끝에 몰려있을 경우 -> 꿀통도 끝에
        //2. 벌이 오른쪽 끝에 몰려있을 경우
        //3. 벌이 양쪽 끝에 있을 경우
        leftBeeRightHoney(arr, n);
        rightBeeLeftHoney(arr, n);
        middleHoney(arr, n);

        System.out.println(answer);
    }

    //1. 벌이 왼쪽 끝에 몰려있을 경우 -> 꿀통도 끝에
    private static void leftBeeRightHoney(int[] arr, int n) {
        int right = Arrays.stream(arr, 2, n).sum();
        int left = right;

        answer = Math.max(answer, left + right);
        for (int i = 2; i < n - 1; i++) {
            left += arr[i - 1] - arr[i];
            right -= arr[i];
            answer = Math.max(answer, left + right);
        }
    }

    //2. 벌이 오른쪽 끝에 몰려있을 경우
    private static void rightBeeLeftHoney(int[] arr, int n) {
        int left = Arrays.stream(arr, 0, n - 2).sum();
        int right = left;

        answer = Math.max(answer, left + right);
        for (int i = n - 3; i > 0; i--) {
            right += arr[i + 1] - arr[i];
            left -= arr[i];
            answer = Math.max(answer, left + right);
        }
    }

    //3. 벌이 양쪽 끝에 있을 경우
    private static void middleHoney(int[] arr, int n) {
        int left = arr[1];
        int right = Arrays.stream(arr, 1, n - 1).sum();

        answer = Math.max(answer, left + right);
        //꿀통 이동
        for (int i = 2; i < n - 1; i++) {
            left += arr[i];
            right -= arr[i - 1];
            answer = Math.max(answer, left + right);
        }
    }
}

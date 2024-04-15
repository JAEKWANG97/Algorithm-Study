import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2343 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int N, M;
    static int arr[];

    static int sum;
    static int max;

    public static void main(String[] args) throws IOException {
        init();
        search();
    }

    private static void init() throws IOException {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        arr = new int[N];
        sum = 0;
        max = 0;
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }
    }

    private static void search() {

        int low = max;
        int high = sum;

        int answer = high;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (canPartition(mid)) {
                answer = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);

    }

    private static boolean canPartition(int mid) {
        int count = 1;
        int temp = 0;
        for (int i = 0; i < N; i++) {
            if (temp + arr[i] > mid) {
                count += 1;
                temp = 0;
            }
            temp += arr[i];
        }
        return count <= M;
    }
}
//블루레이에는 총 N개의 강의
// i와 j 사이의 모든 강의도 같은 블루레이에 녹화
// 블루레이의 개수를 가급적 줄이기로함
// M개의 블루레이에 모든 기타 강의 동영상을 녹화
// M개의 블루레이는 모두 같은 크기

// 9 3 //  N , M
// 1 2 3 4 5 6 7 8 9

// 첫째 줄에 가능한 블루레이 크기중 최소를 출력한다. 17

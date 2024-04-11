import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int[] minDiceFace;
    static int N;
    static int[] arr;
    static int answer;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    public static void main(String[] args) throws IOException {
        init();
        selectMinDiceFace();
        solve();
    }

    private static void init() throws IOException {
        arr = new int[6];
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        minDiceFace = new int[3];
    }

    private static void selectMinDiceFace() {
        minDiceFace[0] = Math.min(arr[0], arr[5]);
        minDiceFace[1] = Math.min(arr[1], arr[4]);
        minDiceFace[2] = Math.min(arr[2], arr[3]);
        Arrays.sort(minDiceFace);
    }

    private static void solve() {
        if (N == 1) {
            Arrays.sort(arr);
            System.out.println(Arrays.stream(arr).sum() - arr[5]);
            return;
        }

        BigInteger result = new BigInteger("0");
        int sum = Arrays.stream(minDiceFace).sum();
        result = result.add(BigInteger.valueOf(sum * 4));
        result = result.add(BigInteger.valueOf((sum - minDiceFace[2]) * (8 * N - 12)));
        BigInteger temp = new BigInteger("1");
        temp = temp.multiply(BigInteger.valueOf(minDiceFace[0])
                .multiply(BigInteger.valueOf(5 * N - 6).multiply(BigInteger.valueOf(N - 2))));
        result = result.add(temp);

        System.out.println(result);
    }
}

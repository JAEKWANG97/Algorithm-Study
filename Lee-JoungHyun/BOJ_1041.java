import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1041 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        long N = Long.parseLong(br.readLine());
        int[] arr = new int[6];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }


        int first = Arrays.stream(arr).min().getAsInt();
        int second = Integer.MAX_VALUE;
        int third = Integer.MAX_VALUE;
        // 2가지 작은거 찾기
        for (int i = 0; i < 5; i++) {
            for (int j = i + 1; j < 6; j++) {
                List<Integer> temp = new LinkedList<>();
                temp.add(i);
                temp.add(j);
                if (second > arr[i] + arr[j] && canSet(temp)) {
                    second = arr[i] + arr[j];
                }
            }
        }
        // 3가지 작은것 찾기
        for (int i = 0; i < 4; i++) {
            for (int j = i + 1; j < 5; j++) {
                for (int k = j + 1; k < 6; k++) {
                    List<Integer> temp = new LinkedList<>();
                    temp.add(i);
                    temp.add(j);
                    temp.add(k);
                    if (third > arr[i] + arr[j] + arr[k] && canSet(temp)) {
                        third = arr[i] + arr[j] + arr[k];
                    }
                }
            }
        }

        // 한면만 보이는 주사위
        long oneSide = (N - 2) * (N - 2) + (N - 1) * (N - 2) * 4;
        // 두면
        long twoSide = (N - 2) * 4 + (N - 1) * 4;
        // 세면
        long threeSide = 4;

        long ans = 0L;

        if (N == 1) {
            ans = Arrays.stream(arr).sum() - Arrays.stream(arr).max().getAsInt();
        }
        else {
            //System.out.println(oneSide + " " + twoSide + " " + threeSide);
            ans += oneSide * first;
            ans += twoSide * second;
            ans += threeSide * third;
        }

        System.out.println(ans);
    }

    static boolean canSet(List<Integer> arr) {
        if (arr.contains(0) && arr.contains(5)) return false;
        if (arr.contains(2) && arr.contains(3)) return false;
        if (arr.contains(1) && arr.contains(4)) return false;

        return true;
    }
}

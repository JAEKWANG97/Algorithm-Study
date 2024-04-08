import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n, k;
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);   //센서 오름차순 정렬

        Integer[] diff = new Integer[n - 1];
        for (int i = 0; i < n - 1; i++) {
            diff[i] = arr[i + 1] - arr[i];  //센서 거리 차이 저장
        }

        Arrays.sort(diff, Collections.reverseOrder());  //센서 거리 내림차순 정렬

        int answer = 0;
        for (int i = k - 1; i < diff.length; i++) { //(센서가 갯수 - 1)번 만큼 해당 거리를 무시할 수 있음
            answer += diff[i];
        }
        System.out.println(answer);
    }
}



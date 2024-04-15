import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_Main_21758 {
  public static void main(String[] args) throws Exception {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int N = Integer.parseInt(st.nextToken());
    int[] arr = new int[N];


    st = new StringTokenizer(br.readLine());
    for (int i = 0; i < N; ++i) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int[] sum1 = new int[N];
    int[] sum2 = new int[N];
    int k = 0;
    
    sum1[0] = arr[0];
    sum2[N - 1] = arr[N - 1];
    for (int i = 1; i < N; i++) {
      sum1[i] = sum1[i-1] + arr[i];
      sum2[N-1-i] = sum2[N-i] + arr[N-1-i];
    }

    for (int i = 1; i < N-1; i++) {
      k = Math.max(k, sum1[i] + sum2[i] - sum1[0] - sum2[N-1]);
      k = Math.max(k, sum1[N-1] + sum1[N-1] - sum1[0] - arr[i] - sum1[i]);
      k = Math.max(k, sum2[0] + sum2[0] - sum2[N-1] - arr[N-1-i] - sum2[N-1-i]);
    }

    System.out.println(k);
  }
}
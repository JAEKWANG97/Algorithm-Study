import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BOJ_1041 {
    static long N;
    static int[] arr;
    static HashMap<Integer, Integer> crossDice;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Long.parseLong(br.readLine());
        arr = new int[6];
        st = new StringTokenizer(br.readLine());
        long min = 50;
        int sum = 0;
        int max = 0;
        for(int i=0; i<6; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum+=arr[i];
            if(min > arr[i]) min = arr[i];
            if(max < arr[i]) max = arr[i];
        }

        if(N==1) {
            System.out.printf("%d", sum-max);
            System.exit(0);
        }

        crossDice = new HashMap<>();
        crossDice.put(0, 5);
        crossDice.put(1, 4);
        crossDice.put(2, 3);
        crossDice.put(3, 2);
        crossDice.put(4, 1);
        crossDice.put(5, 0);

        // 한 면을 미리 답에 넣어준다.
        long oneMin = (5*(N-2)*(N-2) + 4*(N-2))*min;
        long twoMin = (8*(N-2)+4)*findMinTwo();
        long threeMin = 4*findMinThree();
        System.out.println(oneMin+twoMin+threeMin);
    }

    public static long findMinTwo() {
        long result = Long.MAX_VALUE;

        for(int i=0; i<6; i++) {
            for(int j=0; j<6; j++) {
                if(i==j || crossDice.get(i) == j) continue;
                if(result > arr[i] + arr[j]) {
                    result = arr[i]+arr[j];
                }
            }
        }
        return result;
    }

    public static long findMinThree() {
        long result = Long.MAX_VALUE;

        for(int i=0; i<6; i++) {
            for(int j=0; j<6; j++) {
                if(i==j || crossDice.get(i) == j) continue;
                for(int k=0; k<6; k++) {
                    if(k==i || k==j || crossDice.get(i) == k || crossDice.get(j) == k) continue;
                    if(result > arr[i] + arr[j] + arr[k]) {
                        result = arr[i]+arr[j]+arr[k];
                    }
                }
            }
        }
        return result;
    }
}

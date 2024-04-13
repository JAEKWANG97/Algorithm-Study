import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1107 {

    static boolean[] numFlag = {true, true, true, true, true, true, true, true, true, true};

    static int N, nums[], num[], digit;
    static int answer;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        String a = br.readLine();
        N = Integer.parseInt(a);
        digit = a.length();
        int cnt = Integer.parseInt(br.readLine());
        nums = new int[10 - cnt];
        num = new int[digit];
        if (cnt != 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < cnt; i++) {
                numFlag[Integer.parseInt(st.nextToken())] = false;
            }
        }///////// 입력 끝

        int idx = 0;
        for (int i = 0; i < 10; i++) {
            if (numFlag[i]) {
                nums[idx++] = i;
            }
        }
        //---------- 100에서 바로
        answer = Math.abs(N - 100);
        if (cnt == 10) {
            System.out.println(answer);
            return;
        }

        int tmp = 0;
        Arrays.sort(nums);
        //---------- 본인 자릿수 -1 인 것의 Max
        for (int i = 0; i < digit - 1; i++) {
            tmp += nums[nums.length - 1] * (int) Math.pow(10, i);
        }
        if (digit > 1)
            answer = Math.min(answer, Math.abs(tmp - N) + digit - 1);

        //---------- 본인 자릿수 +1 인 것의 Min
        tmp = 0;
        if (nums[0] != 0 || nums.length == 1) {
            for (int i = 0; i < digit + 1; i++) {
                tmp += nums[0] * (int) Math.pow(10, i);
            }
        } else {
            for (int i = 0; i < digit; i++) {
                tmp += nums[0] * (int) Math.pow(10, i);
            }
            tmp += nums[1] * (int) Math.pow(10, digit);
        }
        answer = Math.min(answer, Math.abs(tmp - N) + digit + 1);

        //---------- 본인 자릿수와 같은 수 중복순열로 뽑아 갱신
        makeDupleP(0);
        System.out.println(answer);
    }
    static void makeDupleP(int n) {
        if (n == digit) {
            int tmp = 0;
            for (int i = 0; i < digit; i++) {
                tmp += (num[i] * (int)Math.pow(10, i));
            }
            answer = Math.min(answer, Math.abs(tmp - N)+digit);
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            num[n] = nums[i];
            makeDupleP(n+1);
        }
    }

}

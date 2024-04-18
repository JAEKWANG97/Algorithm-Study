import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int[] bit = new int[1 << 20];

        while (st.hasMoreTokens()) {
            int num = Integer.parseInt(st.nextToken());
            int x = num / 32;
            int y = num % 32;
            // 해당 몫과 나머지가 기존에 있었는지 체크
            if ((bit[x] & (1 << y)) != (1 << y)) {
                // 해당 몫 위치에 나머지 원소 저장
                bit[x] |= (1 << y);
                sb.append(num + " ");
            }
        }

        System.out.println(sb.toString());
    }
}

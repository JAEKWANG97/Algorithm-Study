package algo_sil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;


public class BOJ_1946 {
    static class Grade implements Comparable<Grade> {
        int a;
        int b;
        Grade(int a, int b) {
            this.a = a;
            this.b = b;
        }
        @Override
        public int compareTo(Grade o) {
            if (this.a > o.a) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            ArrayList<Grade> arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr.add(new Grade(a, b));
            }
            Collections.sort(arr);
            int ans = 1;
            int min = arr.get(0).b;
            for (int i = 1; i < N; i++) {
                if (arr.get(i).b < min) {
                    ans++;
                    min = arr.get(i).b;
                }
            }
            System.out.println(ans);
        }
    }
}
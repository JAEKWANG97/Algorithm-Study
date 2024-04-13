import java.util.*;
import java.io.*;

class BOJ_1946 {
    static int T, N;
    static Node[] arr;

    static class Node implements Comparable<Node> {
        int a, b;

        Node(int a, int b){
            this.a = a;
            this.b = b;
        }

        public int compareTo(Node n) {
            return this.a - n.a;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            N = Integer.parseInt(br.readLine());

            arr = new Node[N];

            for(int i=0; i<N; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken()), b = Integer.parseInt(st.nextToken());
                arr[i] = new Node(a, b);
            }

            Arrays.sort(arr);

            int ans = 1;
            int min_val = arr[0].b;
            for(int i=1; i<N; ++i) {
                if(min_val < arr[i].b) continue;

                min_val = arr[i].b;
                ans++;
            }

            System.out.println(ans);
        }
    }
}

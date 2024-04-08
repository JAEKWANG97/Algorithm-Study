package ex0306;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_1976 {
    private static int n,m;
    private static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        for(int i = 1; i <= n; i++){
            arr[i] = i;
        }

        for(int i = 1; i <= n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <=n; j++) {
                int x = Integer.parseInt(st.nextToken());
                if(x == 1) {
                    union(i, j);
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        boolean check = true;
        for(int i = 1; i < m; i++){
            int num = Integer.parseInt(st.nextToken());
            if(find(start) == find(num)) continue;
            check = false;
        }

        if(!check){
            System.out.println("NO");
        }else{
            System.out.println("YES");
        }
    }

    private static int find(int a){
        if(arr[a] == a){
            return a;
        }
        return arr[a] = find(arr[a]);
    }

    private static void union(int from,int to){
        int fromArr = find(from);
        int toArr = find(to);

        if(fromArr == toArr) return;
        arr[fromArr] = arr[toArr];
        return;
    }
}

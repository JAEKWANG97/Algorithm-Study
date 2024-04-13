import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1976 {
    static int[] parents;
    static int[][] city;
    public static void main(String[] args) throws  IOException {
        String answer = "YES";
        ArrayList<Integer> trip = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        city = new int[n+1][n+1];

        parents = new int[n+1];

        for(int i = 1; i <= n; i++) {
            parents[i] = i;
        }

        for(int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                if(Integer.parseInt(st.nextToken()) == 1) union(i,j);
            }
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        while(st.hasMoreTokens()) {
            trip.add(Integer.parseInt(st.nextToken()));
        }
        int check = find(trip.get(0));
        for(int i = 1; i < trip.size(); i++) {
            if(find(trip.get(i)) != check) {
                answer = "NO";
                break;
            }
        }
        System.out.println(answer);
    }

    public static int find(int x) {
        if(parents[x] == x) return x;
        int y= find(parents[x]);
        parents[x] = y;
        return y;
    }
    public static void union(int x, int y) {
        x = find(x);
        y = find(y);
        if(x == y) return;
        parents[y] = x;
    }
}

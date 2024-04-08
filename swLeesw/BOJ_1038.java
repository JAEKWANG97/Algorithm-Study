import java.util.*;
import java.io.*;

public class BOJ_1038 {
    
    static void dfs(int r, int c) {
        
        if (c == last) {
            long sol = 0;
            for (int i = 0; i < last; i++) {
                sol *= 10;
                sol += arr[i];
            }
            
            list.add(sol);
            
            return;
        }
        
        for (int i = r; i >= 0; i--) {
            arr[c] = i;
            dfs(i - 1, c + 1);
        }
    }
    
    static int n, cnt, last, arr[];
    static ArrayList<Long> list = new ArrayList<>();
    
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		
        for (int i = 1; i <= 10; i++) {
            last = i;
            arr = new int[last];
            dfs(9, 0);
        }
        
        Collections.sort(list);
        
        if (n >= list.size()) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(n));
        }
        
	}
}

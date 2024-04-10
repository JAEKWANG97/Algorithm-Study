import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BOJ_2156 { 백준 2156 포도주 시식 - 60분

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> arr = new ArrayList<>();
		ArrayList<Integer> dp = new ArrayList<>();
		int max = 0;
		for(int i=0; i<n; i++) {
			arr.add(Integer.parseInt(br.readLine()));
		}
		dp.add(0);
		dp.add(arr.get(0));
		if(n>=2)
		dp.add(arr.get(0)+arr.get(1));
		for(int i=3; i<n+1; i++) {
			dp.add(Math.max(dp.get(i-1), Math.max(arr.get(i-1)+arr.get(i-2)+dp.get(i-3), dp.get(i-2)+arr.get(i-1))));
		}
		
		for(int i=0; i<=n; i++) {
			if(max<dp.get(i)) {
				max = dp.get(i);
			}
		}
		System.out.println(String.valueOf(max));
	}
	
}

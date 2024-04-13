import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
	static int n;
	static String[] tel;
	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(br.readLine());
		for(int tc = 1 ; tc<=t ; tc++) {
			n = Integer.parseInt(br.readLine());
			tel = new String[n];
			for(int i = 0 ; i<n ; i++) {
				tel[i] = br.readLine();
				
			}
			Arrays.sort(tel);
			if(!check()) System.out.println("NO");
			else System.out.println("YES");
			
		}

	}
	public static boolean check() {
		for(int i = 1 ; i<n; i++) {
			if(tel[i].startsWith(tel[i-1])) return false;

		}
		return true;
		
	}

}

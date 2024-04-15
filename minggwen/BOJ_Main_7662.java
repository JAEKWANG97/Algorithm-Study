import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_Main_7662 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			TreeMap<Integer, Integer> map=new TreeMap<>();
			int N= Integer.parseInt(br.readLine());
			for(int n=0;n<N;n++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				char c = st.nextToken().charAt(0);
				int num = Integer.parseInt(st.nextToken());
				if(c=='I') {
					map.put(num, map.getOrDefault(num, 0)+1);
				}else {
					if(map.isEmpty())continue;
					int k=0;
					if(num==-1) {
						k = map.firstKey();
					}else if(num==1) {
						k=map.lastKey();
					}
					if(map.put(k, map.get(k)-1)==1) {
						map.remove(k);
					}
				}
			}
			if(map.isEmpty())sb.append("EMPTY").append("\n");
			else {
				sb.append(map.lastKey()+" "+map.firstKey()).append("\n");
			}
		}
		System.out.println(sb.toString());
	}

}

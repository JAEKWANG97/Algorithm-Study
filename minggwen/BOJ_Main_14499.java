import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_Main_14499 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		int[][] map = new int[N][M];
		for(int n=0;n<N;n++) {
			st = new StringTokenizer(br.readLine());
			for(int m=0;m<M;m++) {
				map[n][m] = Integer.parseInt(st.nextToken());
			}
		}
		int[] orders = new int[K];
		st = new StringTokenizer(br.readLine());
		for(int k=0;k<K;k++) {
			orders[k] = Integer.parseInt(st.nextToken());
		}
		int a =1, b=5, c=6, d=2, e=4, f=3;
		int[] arr=new int[7];
		for(int order:orders) {
			int nx = x;
			int ny = y;
			if(order==1) {
				ny++;
				a=arr[5];
				c=arr[6];
				e=arr[3];
				f=arr[1];
				b=arr[2];
				d=arr[4];
			}else if(order==2) {
				ny--;
				a=arr[6];
				c=arr[5];
				e=arr[1];
				f=arr[3];
				b=arr[2];
				d=arr[4];
			}else if(order==3) {
				nx--;
				a=arr[2];
				b=arr[3];
				c=arr[4];
				d=arr[1];
				e=arr[5];
				f=arr[6];
				
			}else if(order==4) {
				nx++;
				a=arr[4];
				b=arr[1];
				c=arr[2];
				d=arr[3];
				e=arr[5];
				f=arr[6];
			}
			
			if(0>nx||nx>=N||0>ny||ny>=M)continue;
			arr[1]=a;
			arr[2]=b;
			arr[3]=c;
			arr[4]=d;
			arr[5]=e;
			arr[6]=f;
			x=nx;
			y=ny;
			if(map[x][y]==0)map[x][y]=arr[3];
			else{
				arr[3]=map[x][y];
				map[x][y]=0;
			}
			System.out.println(arr[1]);
		}
	}
}

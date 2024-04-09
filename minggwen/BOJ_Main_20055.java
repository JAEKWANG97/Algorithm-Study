import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_Main_20055 {

	static int N,K;
	static int up = 0;
	static int down;
	static int belt[];
	static List<Integer> robots;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		robots = new ArrayList<>();
		down = N-1;
		belt = new int[N*2];
		st = new StringTokenizer(br.readLine());
		for(int n=0;n<2*N;n++) {
			belt[n] = Integer.parseInt(st.nextToken());
		}
		int cnt = 1;
		
		while(true) {
			beltRotate();
			moveRobot();
			upRobot();
			if(checkZero()>=K)break;
			cnt++;
		}
		System.out.println(cnt);
		
	}
	private static void beltRotate() {
		int tmp = belt[2*N-1];
		for(int idx=2*N-1;idx>0;idx--) {
			belt[idx] = belt[idx-1];
		}
		belt[0] = tmp;
		for(int idx = 0; idx<robots.size();idx++) {
			int robot = robots.get(idx);
			robots.set(idx,robot+1);
			if(robots.get(idx)>=down) {
				robots.remove(idx);
				idx--;
			}
		}
	}
	private static void moveRobot() {
		for(int idx=0;idx<robots.size();idx++) {
			int robot = robots.get(idx);
			if(belt[robot+1]>0&&!robots.contains(robot+1)) {
				belt[robot+1]--;
				robots.set(idx,robot+1);
				if(robots.get(idx)>=down) {
					robots.remove(idx);
					idx--;
				}
			}
		}
	}
	private static void upRobot() {
		if(belt[up]>0) {
			robots.add(up);
			belt[up]--;
		}
	}
	private static int checkZero() {
		int cnt=0;
		for(int tmp:belt) {
			if(tmp==0)cnt++;
		}
		return cnt;
	}

}

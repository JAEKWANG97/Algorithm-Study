package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Main {
	static int[][] delta = {{-1,0},{1,0},{0,-1},{0,1}}; //상하좌우
	static class node{
		int x;
		int y;
		int p;
		public node(int x , int y , int p) {
			this.x = x;
			this.y = y;
			this.p = p;
		}
	}


	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int L = Integer.parseInt(st.nextToken());
		int R = Integer.parseInt(st.nextToken());
		int[][] people = new int[N][N];
		for(int i = 0 ; i<N ; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0 ; j<N ; j++) {
				people[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean check = true;
		int day = 0;
		while(check) {
			check = false;
			ArrayList<node>[][] bridge = new ArrayList[N][N];
			for(int i = 0 ; i <N ; i++) {
				for(int j = 0 ; j<N ; j++) {
					bridge[i][j] = new ArrayList<node>();
				}
			}
			for(int i = 0 ; i<N ; i++) {
				for(int j = 0 ;j<N ; j++) {
					for(int d = 0 ; d<4 ; d++) {
						int nx = i+delta[d][0];
						int ny = j+delta[d][1];
						if(nx>=0 && nx<N && ny>=0 && ny<N && Math.abs(people[i][j]-people[nx][ny])>=L && Math.abs(people[i][j]-people[nx][ny])<=R) {
							bridge[i][j].add(new node(nx,ny,people[nx][ny]));
							check = true;
						}
					}
				}
			}
			if(!check) continue;
			day++;
			boolean[][] visit = new boolean[N][N];
			//연합시작
			for(int i = 0 ; i<N ; i++) {
				for(int j = 0 ; j<N ; j++) {
					if(!visit[i][j] && bridge[i][j].size()!=0) {
						ArrayList<int[]> list = new ArrayList<>();
						Queue<node> q = new LinkedList<>();
						q.add(new node(i,j,people[i][j]));
						visit[i][j] = true;
						int peoplesum = 0;
						while(!q.isEmpty()) {
							node cur = q.poll();
							peoplesum+=cur.p;
							list.add(new int[] {cur.x,cur.y});
							for(node n : bridge[cur.x][cur.y]) {
								if(!visit[n.x][n.y]) {
									visit[n.x][n.y] = true;
									q.add(n);
								}
							}
						}
						for(int[] cur : list) {
							people[cur[0]][cur[1]] = peoplesum/list.size();
						}
					}
	
				}
			}
		}
		System.out.println(day);
		
	}

}

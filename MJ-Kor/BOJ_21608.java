package algorithm_study.day_03_20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_21608 {

	static class Student{
		int num;
		List<Integer> fs;
		public Student(int num, List<Integer> fs) {
			super();
			this.num = num;
			this.fs = fs;
		}

		@Override
		public String toString() {
			return "Student [num=" + num + ", fs=" + fs + "]";
		}
	}
	
	static class Point{
		int r, c;
		public Point(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}

		@Override
		public String toString() {
			return "Point [r=" + r + ", c=" + c + "]";
		}
	}
	private static int N;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	private static Student[] students;
	private static Student[] studentsList;
	private static int[][] seats;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		seats = new int[N + 1][N + 1];
		students = new Student[N*N];
		studentsList = new Student[N*N + 1];
		
		for (int i = 0; i < N*N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			List<Integer> fs = new ArrayList<>();
			for (int j = 0; j < 4; j++) {
				fs.add(Integer.parseInt(st.nextToken()));
			}
			students[i] = new Student(num, fs);
			studentsList[num] = new Student(num, fs);
		}
		
		for (int i = 0; i < N*N; i++) {
			List<Point> points = new ArrayList<>(); 
			points = firstRule(students[i]);
			if(points.size() == 1) {
				seats[points.get(0).r][points.get(0).c] = students[i].num;
				continue;
			}
			points = secondRule(points);
			if(points.size() == 1) {
				seats[points.get(0).r][points.get(0).c] = students[i].num;
				continue;
			}
			points = thirdRule(points);
			if(points.size() == 1) {
				seats[points.get(0).r][points.get(0).c] = students[i].num;
				continue;
			}
			points = fourthRule(points);
			seats[points.get(0).r][points.get(0).c] = students[i].num;
		}
		
		System.out.println(getScore());
	}

	private static int getScore() {
		int total = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				int cnt = 0;
				for (int j = 0; j < 4; j++) {					
					int nr = r + dr[j];
					int nc = c + dc[j];
					if(nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
						if(studentsList[seats[r][c]].fs.contains(seats[nr][nc])) {
							cnt++;
						}
					}
				}
				if(cnt == 0) {
					total += 0;
				} else if(cnt == 1) {
					total += 1;
				} else if(cnt == 2) {
					total += 10;
				} else if(cnt == 3) {
					total += 100;
				} else if(cnt == 4) {
					total += 1000;
				}
			}
		}
		return total;
	}

	private static List<Point> fourthRule(List<Point> points) {
		List<Point> npoints = new ArrayList<>();
		int minCol = Integer.MAX_VALUE;
		for(Point p : points) {
			minCol = Math.min(minCol, p.r);
		}
		for(Point p : points) {
			if(p.r == minCol) {
				npoints.add(p);
			}
		}
		return npoints;
	}

	private static List<Point> thirdRule(List<Point> points) {
		List<Point> npoints = new ArrayList<>();
		int minRow = Integer.MAX_VALUE;
		for(Point p : points) {
			minRow = Math.min(minRow, p.r);
		}
		for(Point p : points) {
			if(p.r == minRow) {
				npoints.add(p);
			}
		}
		return npoints;
	}

	private static List<Point> secondRule(List<Point> points) {
		int[][] check = new int[N + 1][N + 1];
		List<Point> npoints = new ArrayList<>();
		int max = 0;
		for(Point p : points) {
			int cnt = 0;
			for (int i = 0; i < 4; i++) {
				int nr = p.r + dr[i];
				int nc = p.c + dc[i];
				if(nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
					if(seats[nr][nc] == 0) {
						cnt++;
					}
				}
			}
			check[p.r][p.c] = cnt;
			max = Math.max(max, cnt);
		}
		for(Point p : points) {
			if(check[p.r][p.c] == max) {
				npoints.add(p);
			}
		}
		return npoints;
	}

	private static List<Point> firstRule(Student student) {
		List<Point> points = new ArrayList<>();
		int[][] check = new int[N + 1][N + 1];
		int max = 0;
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(seats[r][c] != 0) {
					check[r][c] = -1;
					continue;
				}
				int cnt = 0;
				for (int i = 0; i < 4; i++) {
					int nr = r + dr[i];
					int nc = c + dc[i];
					if(nr >= 1 && nr <= N && nc >= 1 && nc <= N) {
						if(student.fs.contains(seats[nr][nc])) {
							cnt++;
						}
					}
				}
				check[r][c] = cnt;
				max = Math.max(max, cnt);
			}
		}
		for (int r = 1; r <= N; r++) {
			for (int c = 1; c <= N; c++) {
				if(check[r][c] == max) {
					points.add(new Point(r, c));
				}
			}
		}
		return points;
	}
}

package algorithm_study.day_04_01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

// 일반 블록은 M가지 색상이 존재
// 색은 M이하의 자연수로 표현
// 검은색 블록은 -1, 무지개 블록은 0

// 블록 그룹
// 		- 일반 블록이 적어도 하나
// 		- 일반 블록의 색은 모두 같아야 함
// 		- 검은색 블록은 포함되면 안됨
// 		- 무지개 블록은 얼마나 들어있든 상관 없음
// 		- 블록의 개수는 2보다 크거나 같아야 함
// 		- 임의의 한 블록에서 그룹에 속한 인접한 칸으로 이동해서 그룹에 속한 다른 모든 칸으로 이동할 수 있어야 함
// 		- 기준 블록은 무지개 블록이 아닌 블록중에서 행의 번호가 가장 작은 블록, 그런 블록이 여러개면 열의 번호가 가장 작은 블록이다.

// 오토 플레이 (블록 그룹이 존재하는 동안 계속해서 반복되어야 함
// 1. 크기가 가장 큰 블록 그룹을 찾는다. 그러한 그룹이 여러 개라면
// 					a. 포함된 무지개 블록수가 가장 많은 블록그룹
//					b. 기준 블록의 행이 가장 큰 것
//					c. 열이 가장 큰 것
// 2. 1에서 찾은 블록 그룹의 모든 블록을 제거한다. 블록 그룹의 블록의 수를 B라고 했을 때 B^2점을 획득한다.
// 3. 격자에 중력이 작용한다.
// 4. 격자가 90도 반시계 방향으로 회전한다.
// 5. 다시 격자에 중력이 작용한다.

// 중력 : 검은색 블록을 제외한 모든 블록이 행의 번호가 큰 칸으로 이동한다. 이동은 다른 블록이나 격자의 경계를 만나기 전까지 계속 된다.
// -> 구슬치기 처럼 블럭이 맨 밑으로 내려오는 것
public class BOJ_21609 {
	
	private static class BiggestGroupComparator implements Comparator<Group>{

		@Override
		public int compare(Group o1, Group o2) {
			if(o1.blocks.size() > o2.blocks.size()) {
				return -1;
			} else if(o1.blocks.size() < o2.blocks.size()) {
				return 1;
			} else {
				if(o1.rainbowNum > o2.rainbowNum) {
					return -1;
				} else if(o1.rainbowNum < o2.rainbowNum) {
					return 1;
				} else {
					if(o1.standardBlock.r > o2.standardBlock.r) {
						return -1;
					} else if(o1.standardBlock.r > o2.standardBlock.r) {
						return 1;
					} else {
						if(o1.standardBlock.c > o2.standardBlock.c) {
							return -1;
						} else if(o1.standardBlock.c < o2.standardBlock.c) {
							return 1;
						} else {
							return 0;
						}
					}
				}
			}
		}
	}
	
	private static class RowColComparator implements Comparator<Block>{

		@Override
		public int compare(Block o1, Block o2) {
			if(o1.r > o2.r) {
				return 1;
			} else if(o1.r < o2.r) {
				return -1;
			} else {
				if(o1.c > o2.c) {
					return 1;
				} else if(o1.c < o2.c){
					return -1;
				} else {
					return 0;
				}
			}
		}
		
	}
	
	private static class Block{
		int r, c, value;

		public Block(int r, int c, int value) {
			super();
			this.r = r;
			this.c = c;
			this.value = value;
		}

		@Override
		public String toString() {
			return "Block [r=" + r + ", c=" + c + ", value=" + value + "]";
		}
	}
	
	private static class Group {
		int groupValue;
		int rainbowNum = 0;
		Block standardBlock;
		
		List<Block> blocks = new ArrayList<>();
		
		public Group(int groupValue) {
			super();
			this.groupValue = groupValue;
		}
		
		public void findStandard() {
			Collections.sort(this.blocks, rcc);
			for(Block b : this.blocks) {
				if(b.value == 0) continue;
				this.standardBlock = b;
				break;
			}
		}
		
		public void setRainbowNum() {
			for(Block b : this.blocks) {
				if(b.value == 0) {
					rainbowNum++;
				}
			}
		}

		@Override
		public String toString() {
			return "Group [groupValue=" + groupValue + ", rainbowNum=" + rainbowNum + ", standardBlock=" + standardBlock
					+ ", blocks=" + blocks + "]\n";
		}
		

	}

	private static int groupNum = 0;
	private static int N, M;
	private static int[] dr = {-1, 0, 1, 0};
	private static int[] dc = {0, 1, 0, -1};
	private static int[][] map;	
	private static List<Group> groups = new ArrayList<>();
	private static Queue<Block> queue = new ArrayDeque<>();
	private static RowColComparator rcc = new RowColComparator();
	private static BiggestGroupComparator bgc = new BiggestGroupComparator();
	
	public static void main(String[] args) throws IOException {
		
		init();
		int result = autoplay();
		System.out.println(result);
	}

	private static void init() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
			}
		}
	}

	private static int autoplay() {
		int score = 0;
		while(findBlockGroup()) {
			Group BiggestGroup = groups.get(0);
			score += deleteBlockGroup(BiggestGroup);
			gravity();
			map = rotateCCW90();
			gravity();
		}
		return score;
	}

	private static int[][] rotateCCW90() {
		int[][] rotatedMap = new int[N][N];
		int idx = 0;
		for (int c = N - 1; c >= 0; c--) {
			int[] copiedRow = new int[N];
			for (int r = 0; r < N; r++) {
				copiedRow[r] = map[r][c];
			}
			rotatedMap[idx] = copiedRow;
			idx++;
		}
		return rotatedMap;
	}

	private static void gravity() {
		for (int r = N - 2; r >= 0; r--) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] >= 0) {
					int l = 1;
					int nr = r + l;
					while(idxValid(nr, c)) {
						if(map[nr][c] != -2) break;
						
						++l;
						nr = r + l;
					}
					
					if(l != 1) {						
						map[nr - 1][c] = map[r][c];
						map[r][c] = -2;
					}
				}
			}
		}
	}

	private static int deleteBlockGroup(Group group) {
		int score = 0;
		score = group.blocks.size() * group.blocks.size();
		for(Block b : group.blocks) {
			map[b.r][b.c] = -2; 
		}
		return score;
	}

	private static boolean findBlockGroup() {
		groups.clear();
		boolean[][] visited = new boolean[N][N];
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < N; c++) {
				if(map[r][c] >= 1 && !visited[r][c]) bfs(r, c, visited);
			}
		}
		
		Collections.sort(groups, bgc);
		if(isBlockGroup()) return true;
		return false;
	}

	private static boolean isBlockGroup() {
		if(groups.size() != 0) return true;
		return false;
	}

	private static void bfs(int r, int c, boolean[][] visited) {
		int groupValue = map[r][c];
		Group group = new Group(groupValue);
		List<Block> rainbows = new ArrayList<>();
		
		visited[r][c] = true;
		queue.offer(new Block(r, c, map[r][c]));
		while(!queue.isEmpty()) {
			Block curr = queue.poll();
			group.blocks.add(curr);
			for (int i = 0; i < 4; i++) {
				int nr = curr.r + dr[i];
				int nc = curr.c + dc[i];
				if(idxValid(nr, nc)  && !visited[nr][nc]) {
					if(map[nr][nc] == groupValue || map[nr][nc] == 0) {						
						visited[nr][nc] = true;
						Block next = new Block(nr, nc, map[nr][nc]);
						queue.offer(next);
						if(map[nr][nc] == 0) {
							rainbows.add(next);
						}
					}
				}
			}
		}
		
		for(Block b : rainbows) {
			visited[b.r][b.c] = false; 
		}
		
		rainbows.clear();
		if(group.blocks.size() >= 2) {			
			group.setRainbowNum();
			group.findStandard();
			groups.add(group);
			groupNum++;
		}
	}

	private static boolean idxValid(int nr, int nc) {
		if(nr >= 0 && nr < N && nc >= 0 && nc < N) return true;
		return false;
	}
	
	private static void printArr() {
		System.out.println();
		for (int r = 0; r < N; r++) {
			System.out.println(Arrays.toString(map[r]));
		}
	}
	
}

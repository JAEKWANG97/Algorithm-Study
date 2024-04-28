import java.util.*;

class Solution {
	int[][] posX, posY;
	Set<Character> set = new TreeSet();
	char[][] map;
	final int SIZE = 'Z' - 'A' + 1;

	public String solution(int m, int n, String[] board) {
		StringBuilder sb = new StringBuilder();
		map = new char[m][n];
		for (int i = 0; i < m; ++i)
			map[i] = board[i].toCharArray();

		posX = new int[SIZE][2];
		posY = new int[SIZE][2];

		for (int i = 0; i < SIZE; ++i) {
			posX[i][0] = -1;
			posX[i][1] = -1;
			posY[i][0] = -1;
			posY[i][1] = -1;
		}

		for (int i = 0; i < m; ++i) {
			for (int j = 0; j < n; ++j) {
				char c = board[i].charAt(j);
				if ('A' <= c && c <= 'Z') {
					set.add(board[i].charAt(j));
					if (posX[c - 'A'][0] == -1)
						posX[c - 'A'][0] = i;
					else
						posX[c - 'A'][1] = i;

					if (posY[c - 'A'][0] == -1)
						posY[c - 'A'][0] = j;
					else
						posY[c - 'A'][1] = j;
				}
			}
		}

		while (!set.isEmpty()) {
			int size = set.size();
			for (char c : set) {
				if (check(c)) {
					sb.append(c);
					set.remove(c);
					break;
				}
			}

			if (size == set.size())
				break;
		}

		if (set.size() != 0)
			return "IMPOSSIBLE";
		return sb.toString();
	}

	public boolean check(char c) {
		int x1 = posX[c - 'A'][0];
		int x2 = posX[c - 'A'][1];
		int y1 = posY[c - 'A'][0];
		int y2 = posY[c - 'A'][1];

		// 두 개 지점의 x,y가 모두 다르다면, 꺾이는 지점으로 갈 수 있어야 한다.
		if (x1 != x2 && y1 != y2) {
			if (canGoCheckX(y2, x1, x2, c) && canGoCheckY(x1, y1, y2, c)) {
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
			if (canGoCheckX(y1, x1, x2, c) && canGoCheckY(x2, y1, y2, c)){
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
		}

		// 둘 중 하나의 좌표값이 같다면, 일직선으로 연결이 되는지 확인한다.
		// x좌표가 동일한 경우, y축을 따라 검사
		if (x1 == x2 && y1 != y2) {
			int x = posX[c - 'A'][0];
			if (canGoCheckY(x, y1, y2, c)) {
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
		}

		// y좌표가 동일한 경우, x축을 따라 검사
		if (x1 != x2 && y1 == y2) {
			int y = posY[c - 'A'][0];
			if (canGoCheckX(y, x1, x2, c)) {
				map[x1][y1] = '.';
				map[x2][y2] = '.';
				return true;
			}
		}

		return false;
	}

	private boolean canGoCheckY(int baseX, int y1, int y2, char c) {
		int bigger, smaller;
		if (y1 > y2) {
			bigger = y1;
			smaller = y2;
		} else {
			bigger = y2;
			smaller = y1;
		}
		for (int i = smaller; i <= bigger; ++i) {
			if (map[baseX][i] != '.' && map[baseX][i] != c) {
				return false;
			}
		}
		return true;
	}

	private boolean canGoCheckX(int baseY, int x1, int x2, char c) {
		int bigger, smaller;
		if (x1 > x2) {
			bigger = x1;
			smaller = x2;
		} else {
			bigger = x2;
			smaller = x1;
		}
		for (int i = smaller; i <= bigger; ++i) {
			if (map[i][baseY] != '.' && map[i][baseY] != c) {
				return false;
			}
		}
		return true;
	}
}

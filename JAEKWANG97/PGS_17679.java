import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Solution {
    static class Location {
        int r, c;

        public Location(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    private static List<Location> removeList = new ArrayList<>();

    public int solution(int m, int n, String[] board) {
        String[][] board2 = new String[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board2[i][j] = String.valueOf(board[i].charAt(j));
            }
        }

        solve(board2, m, n);
        return count(board2, m, n);
    }

    private static void solve(String[][] board, int m, int n) {
        boolean changeMade;
        do {
            changeMade = false;
            removeList.clear();
            for (int r = 0; r < m - 1; r++) {
                for (int c = 0; c < n - 1; c++) {
                    searchRemove(board, r, c);
                }
            }

            if (!removeList.isEmpty()) {
                changeMade = true;
                for (Location location : removeList) {
                    board[location.r][location.c] = "";
                }
                setMap(board, m, n);
            }
        } while (changeMade);
    }

    private static int count(String[][] board, int m, int n) {
        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (board[r][c].equals("")) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void searchRemove(String[][] board, int r, int c) {
        String val = board[r][c];
        if (val.equals("")) return; // Skip empty tiles
        if (val.equals(board[r][c + 1]) && val.equals(board[r + 1][c]) && val.equals(board[r + 1][c + 1])) {
            removeList.add(new Location(r, c));
            removeList.add(new Location(r, c + 1));
            removeList.add(new Location(r + 1, c));
            removeList.add(new Location(r + 1, c + 1));
        }
    }

    private static void setMap(String[][] board, int m, int n) {
        for (int c = 0; c < n; c++) {
            Queue<String> que = new LinkedList<>();
            for (int r = m - 1; r >= 0; r--) {
                if (!board[r][c].equals("")) {
                    que.offer(board[r][c]);
                }
            }
            int r = m - 1;
            while (!que.isEmpty()) {
                board[r--][c] = que.poll();
            }
            while (r >= 0) {
                board[r--][c] = "";
            }
        }
    }
}

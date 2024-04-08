import java.util.*;
public class PGS_67295 {
    static class Move{
        int row,col;
        boolean uD;

		public Move(int row, int col,boolean uD) {
			super();
			this.row = row;
			this.col = col;
            this.uD = uD;
		}
        @Override
		public String toString() {
			return "Move [row=" + row + ", col=" + col +"\n";
		}
    }
    
    static int MIN = Integer.MAX_VALUE;
    static int[][] upDown = {{1,0},{-1,0}};
    static int[][] leftRight = {{0,1},{0,-1}};
    static int[][] map;
    public int solution(int[][] board) {
        int answer = 0;
        map = board;
        bfs();
        return bfs();
    }
    private static int bfs(){
        Queue<Move> que = new ArrayDeque<>();
        int visited[][][] = new int[map.length][map[0].length][2];
        for(int[][] tmp1 : visited){
            for(int[] tmp2 : tmp1){
                Arrays.fill(tmp2,Integer.MAX_VALUE);
            }
        }
        que.add(new Move(0,0,true));
        que.add(new Move(0,0,false));
        visited[0][0][0] = 0;
        visited[0][0][1] = 0;
        while(!que.isEmpty()){
            Move m = que.poll();
            int row = m.row;
            int col = m.col;
            int ud = m.uD ? 0:1;
            if(row==map.length-1&&col == map[0].length-1){
                MIN = Math.min(MIN,visited[map.length-1][map[0].length-1][ud]);
            };
            if(m.uD){
                for(int d=0;d<upDown.length;d++){
                    int nr = row+upDown[d][0];
                    int nc = col+upDown[d][1];
                    if(isIn(nr,nc)&&visited[row][col][0]+100<visited[nr][nc][0]&&map[nr][nc]==0)                          {
                        que.add(new Move(nr,nc,true));
                        visited[nr][nc][0] = visited[row][col][0]+100;
                    }
                }
                for(int d=0;d<leftRight.length;d++){
                    int nr = row+leftRight[d][0];
                    int nc = col+leftRight[d][1];
                    if(isIn(nr,nc)&&visited[row][col][0]+600<visited[nr][nc][1]&&map[nr][nc]==0)                      {   
                         que.add(new Move(nr,nc,false));
                        visited[nr][nc][1] = visited[row][col][0]+600;
                    }
                }
            }else{
                for(int d=0;d<leftRight.length;d++){
                    int nr = row+leftRight[d][0];
                    int nc = col+leftRight[d][1];
                    if(isIn(nr,nc)&&visited[row][col][1]+100<visited[nr][nc][1]&&map[nr][nc]==0)                      {
                        que.add(new Move(nr,nc,false));
                        visited[nr][nc][1] = visited[row][col][1]+100;
                    }
                }
                for(int d=0;d<upDown.length;d++){
                    int nr = row+upDown[d][0];
                    int nc = col+upDown[d][1];
                    if(isIn(nr,nc)&&visited[row][col][1]+600<visited[nr][nc][0]&&map[nr][nc]==0)                      {
                        que.add(new Move(nr,nc,true));
                        visited[nr][nc][0] = visited[row][col][1]+600;
                    }
                }
            }
        }
        return MIN;
    }
    private static boolean isIn(int row,int col){
        return 0<=row&&row<map.length&&0<=col&&col<map[0].length;
    }
}
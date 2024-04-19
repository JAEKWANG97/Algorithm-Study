import java.util.*;
class PGS_17679 {
    static class Block{
        int r, c;
        Block(int r, int c){
            this.r = r;
            this.c = c;
        }
        @Override
        public String toString(){
            return "r: "+r+" c: "+c;
        }
    }
    static int M,N;
    static char[][] map;
    static int delta[][] = {{1,0},{0,1},{1,1}};
    public int solution(int MM, int NN, String[] board) {
        int answer = 0;
        M=MM;
        N=NN;
        map = new char[M][N];
        for(int m=0;m<M;m++){
            for(int n=0;n<N;n++){
                map[m][n] = board[m].charAt(n);
            }
        }
        while(true){
            boolean flag = bfs();
            if(!flag)break;
            down();
        }
       
        for(int m=0;m<M;m++){
            for(int n=0;n<N;n++){
                if(map[m][n]=='0')answer++;
            }
        }
        return answer;
    }
    public boolean bfs(){
        List<Block> arr = new ArrayList<>();
        for(int r=0;r<M;r++){
            for(int c=0;c<N;c++){
                if(map[r][c]=='0')continue;
                if(checkBlock(r,c)){
                   arr.add(new Block(r,c));
                    for(int d=0;d<delta.length;d++){
                        int nr = r+(delta[d][0]);
                        int nc = c+(delta[d][1]);
                        arr.add(new Block(nr,nc));
                    }
                }
            }
        }

        for(Block b:arr){
            map[b.r][b.c]='0';
        }
        return arr.isEmpty()?false:true;
    }
    private static boolean checkBlock(int row,int col){
        boolean flag = true;
        for(int d=0;d<delta.length;d++){
            int nr = row+(delta[d][0]);
            int nc = col+(delta[d][1]);      
            if(!isIn(nr,nc)||(isIn(nr,nc)&&map[row][col]!=map[nr][nc])){
                flag=false;
                break;
            }
        }
        return flag;
        
    }
    private static void down(){
        Stack<Character> s= new Stack<>();
        for(int n=0;n<N;n++){
            for(int m=M-1;m>=0;m--){
                if(map[m][n]!='0'){
                    s.push(map[m][n]);
                    map[m][n]='0';
                }
            }
            int size = s.size();
            for(int m=M-size;m<M;m++){
                map[m][n]=s.pop();
            }
        }
    }
    private static boolean isIn(int row,int col){
        return 0<=row&&row<M&&0<=col&&col<N?true:false;
    }
}

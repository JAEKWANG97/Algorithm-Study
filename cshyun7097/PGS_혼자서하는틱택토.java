public class PGS_혼자서하는틱택토 {
    public int solution(String[] board) {
        int answer = -1;
        char[][] map = new char[3][3];
        int oCnt = 0;
        int xCnt = 0;
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                map[i][j] = board[i].charAt(j);
                if(map[i][j] == 'O'){
                    oCnt++;
                }else if(map[i][j] == 'X'){
                    xCnt++;
                }
            }
        }

        //잘못둔게 있는지 확인
        if (xCnt > oCnt || oCnt - xCnt > 1) return 0;

        boolean oIsvalid = isValid(map, 'O');
        boolean xIsvalid = isValid(map, 'X');

        //O가 이겼는데 둘의 차이가 2 이상일 때 -> O가 이겼을땐 1개차이로 이겼어야함
        if (oIsvalid && oCnt - xCnt != 1)
            return 0;

        //X가 이겼는데 둘의 차이가 다를 때 -> O가 많으면 게임에서 이겼는데 더 놓은것 or O가 적으면 X가 하나 더 놓아짐
        if (xIsvalid && xCnt != oCnt)
            return 0;

        return 1;
    }

    //틱택토 이겼는지 판단
    private static boolean isValid(char[][] map, char compare){
        //가로
        for (int i = 0; i < 3; i++){
            if (map[i][0] == compare && map[i][1] == compare && map[i][2] == compare) return true;
        }

        //세로
        for (int i = 0; i < 3; i++){
            if (map[0][i] == compare && map[1][i] == compare && map[2][i] == compare) return true;
        }

        // 좌상 -> 우하 대각선
        if (map[0][0] == compare && map[1][1] == compare && map[2][2] == compare) return true;

        // 좌하 -> 우상 대각선
        if (map[2][0] == compare && map[1][1] == compare && map[0][2] == compare) return true;

        //다 안맞으면 false
        return false;
    }
}
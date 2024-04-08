import java.util.*;

public class PGS_12980 { //프로그래머스 12980 점프와 순간이동 - 10분
    public int solution(int n) {
        int jump = 0;

        while (n>0) {
            if (n%2==0) {
                n = n/2;
            } else {
                n--;
                jump++;
            }
        }

        return jump;
    }//solution
}//class

//순간이동 : 건전지 소모 x, 현재까지 거리 *2만큼 이동
//k칸 점프 : 건전지 k만큼 소모
//점프횟수를 최소로 n까지 이동
//n = 5이면 5/2 -> 이전칸

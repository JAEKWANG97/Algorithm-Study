
import java.util.*;


public class PGS_12980{
    public int solution(int n) {

        return getNum(n);
    }
    private static int getNum(int n){
        if(n<=2) return 1;
        return getNum(n/2)+n%2;
    }
}
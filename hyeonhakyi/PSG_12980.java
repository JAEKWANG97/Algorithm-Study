package ex0309;

public class PSG_12980 {
    public int solution(int n){
        int current = 0;
        int beterry = 0;

        while(current < n){
            if((n-current)%2 == 0){
                current += (n-current)/2;
            }else {
                current += 1;
                beterry++;
            }
        }
        return beterry;
    }
}

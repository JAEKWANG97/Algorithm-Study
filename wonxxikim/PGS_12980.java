import java.util.*;

public class Solution {
    public int solution(int n) {
        int ans = 0;
        while(true){
            if(n== 0) break;
            else if(n%2==1){
                ans++;
                n--;
            }
            else if(n%2 ==0){
                n = n/2;
            }
        }

        return ans;
    }
}

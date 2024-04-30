import java.util.*;
import java.io.*;

class Solution {
    
    public ArrayList<Long> checkNum = new ArrayList<>();
    public int answer = 0;
    
    public int solution(int n, int k) {
        
        parsingK(n,k);
        checkPrime();
        
        return answer;
    }
    
    public void checkPrime(){
        for(long v: checkNum){
            if(prime(v)) answer++;
        }
    }
    
    // 소수판별
    public boolean prime(long v){
        if(v < 2) return false;
        
        if(v == 2) return true;
        
        for(long i = 2; i <= Math.sqrt(v); i++){
            if(v%i == 0){
                return false;
            }
        }
        
        return true;
    }
    
    public void parsingK(int n, int k){
        String str = "";
        String temp = "";
        while(n > 0){
            str = String.valueOf(n%k) + str;
            n/= k;
        }
        for(int i = 0; i < str.length(); i++){
            char c = str.charAt(i);
            if(c == '0'){
                if(!temp.isEmpty()){
                    checkNum.add(Long.parseLong(temp));
                    temp = "";
                }
            }else{
                temp += c +"";
            }
            
            if(i == str.length()-1){
                if(!temp.isEmpty()){
                    System.out.println(temp);
                    checkNum.add(Long.parseLong(temp));
                }
            }
        }
    }
}

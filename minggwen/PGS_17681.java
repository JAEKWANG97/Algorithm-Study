import java.util.*;
class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];
        String[] binary = new String[arr1.length];
        for(int idx=0;idx<arr1.length;idx++){
            binary[idx] = Integer.toBinaryString(arr1[idx]|arr2[idx]);
            if((arr1[idx]|arr2[idx])<=Math.pow(2,n-1)){
                String tmp = "";
                for(int k=0; k<n-binary[idx].length();k++) tmp+=" ";
                binary[idx] = tmp+binary[idx];
            }
        }
        for(int idx=0; idx<arr1.length;idx++){
            String tmp = "";
            for(int c = 0; c<binary[idx].length();c++){
                if(binary[idx].charAt(c)=='1')tmp+="#";
                else tmp+=" ";
            }
            answer[idx] = tmp;
        }
        return answer;
    }
}

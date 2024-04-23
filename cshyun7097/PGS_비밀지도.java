import java.util.*;
import java.io.*;

class Solution {
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[n];
        for(int i = 0; i < n; i++){
            int a = arr1[i];
            int b = arr2[i];
            String tmp = "";
            for(int j = n - 1; j >= 0; j--){
                if ((((1 << j) & a) | ((1 << j) & b)) != 0){
                    tmp += "#";
                }else{
                    tmp += " ";
                }
            }
            answer[i] = tmp;
        }
        return answer;
    }
}

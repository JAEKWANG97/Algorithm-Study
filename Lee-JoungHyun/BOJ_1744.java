import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BOJ_1744 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> posNum = new ArrayList<>(); // 양수 저장
        ArrayList<Integer> negNum = new ArrayList<>(); // 음수 저장
        int ones = 0; // 1의 수
        int zeros = 0; // 0의 수
        int posIdx = 0;
        int negIdx = 0;
        
        int sum = 0;
        
        for(int i=0; i<N; i++) {
        	int n = Integer.parseInt(br.readLine());
        	if(n<0) {
            	negNum.add(n);
        	}
        	else if(n == 0) {
        		zeros++;
        	}
        	else if(n == 1) {
        		ones++;
        	}
        	else {
        		posNum.add(n);
        	}
        }
        
        //양수 계산
        Collections.sort(posNum, Comparator.reverseOrder());
        while(posIdx+1 <= posNum.size()-1) {
        	int n1 = posNum.get(posIdx);
        	int n2 = posNum.get(posIdx+1);
        	sum += n1*n2;
        	posIdx += 2;
        }
        if(posIdx == posNum.size()-1) {
        	sum += posNum.get(posIdx);
        }
        
        //음수 계산
        Collections.sort(negNum);
        while(negIdx+1 <= negNum.size()-1) {
        	int n1 = negNum.get(negIdx);
        	int n2 = negNum.get(negIdx+1);
        	sum += n1*n2;
        	negIdx += 2;
        }
        if(zeros == 0) {
        	if(negIdx == negNum.size()-1) {
        		sum += negNum.get(negIdx);
        	}
        }
        
        //1 계산
        sum += ones;
        
        System.out.print(sum);
    }
}
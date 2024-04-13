import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_1038 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        ArrayList<String> answer = new ArrayList<>();

        for (int i = 0; i < 10; i++){
            answer.add(String.valueOf(i));
        }

        ArrayList<String> arr = answer;
        for (int i = 0; i < 10; i++){
            ArrayList<String> newArr = new ArrayList<>();
            for (char j = '1'; j <= '9'; j++){
                for (String a: arr){
                    if (a.charAt(0) < j){
                        newArr.add(String.valueOf(j).concat(a));
                    }
                }
            }
            answer.addAll(newArr);
            arr = newArr;
        }

        if (answer.size() > N){
            System.out.println(answer.get(N));
        }else{
            System.out.println(-1);
        }

    }
}
